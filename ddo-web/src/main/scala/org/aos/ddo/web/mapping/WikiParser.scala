package org.aos.ddo.web.mapping

import java.text.NumberFormat

import scala.collection.JavaConversions.{ asScalaBuffer, seqAsJavaList }
import scala.language.{ existentials, postfixOps, reflectiveCalls }
import scala.util.Try

import org.aos.ddo.{
  Attributes,
  BindingFlags,
  CriticalProfile,
  DamageInfo,
  Material,
  MonetaryValue,
  PhysicalDamageType,
  UpgradeInfo
}
import org.aos.ddo.support.StringUtils.{ Comma, EmptyString, ForwardSlash, Space, StringImprovements }
import org.aos.ddo.weapon.{
  CriticalThreatRange,
  Handedness,
  MeleeDamage,
  ProficiencyClass,
  RangeDamage,
  SpecialDamage,
  ThrownDamage,
  WeaponCategory,
  WeaponType
}
import org.aos.ddo.web.HtmlTag
import org.jsoup.nodes.Element

import com.typesafe.scalalogging.slf4j.LazyLogging

/** Contains functions used to scrape DDOWiki when needed.
  */
object WikiParser extends LazyLogging {
  lazy val MsgNoData = "No Data available to extract"
  lazy val MsgRawStringData = "returning raw string data"
  /** parser that extracts the text portion from a possibly HTML wrapped string.
    * @param textOrElement fragment of HTML or Text
    * @param tagSelector tag to extract. defaults to 'td'
    * @return extracted text or None if tag was not found or input was not of expected type.
    */
  def simpleExtractor(textOrElement: Option[Any], tagSelector: String = HtmlTag.TableData): Option[String] = {
    textOrElement match {
      case Some(data: String) ⇒
        logger.info(MsgRawStringData)
        Some(data)
      case Some(data: Element) ⇒
        val rslt = data.select(tagSelector).first().text()
        lazy val MsgExtraction = s"returning element extraction ${rslt}"
        logger.info(MsgExtraction)
        Some(rslt)
      case Some(data) ⇒ {
        logger.warn(s"Data was not of expected type (text or Element) - found ${data.getClass().toString()}")
        None
      }
      case _ ⇒ logger.warn(MsgNoData); None
    }

  }

  /** Helper object to hold extended damage information
    * @param wMod Weapon Modifier information
    * @param dice D &amp; D Dice
    * @param extra Extra damage beyond dice
    * @param damageType List of Damage types
    */
  case class damageExtractor(wMod: Int, dice: String, extra: Int, damageType: List[String])

  /** Wraps text into an extractor object as an intermeadiate object to extract additional
    * damage information.
    * @param infoText structured text read from the Wiki HTML
    * @return wrapped damageExtractor object with parsed properties.
    */
  def extractDamageInfo(infoText: String): Option[damageExtractor] = {
    val damageInfo = """(?<wDamage>\d(?:\.\d+)?)?\s*(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    // val damageInfo = """(?<wDamage>\d+.\d+)?(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    infoText match {
      case damageInfo(wDamage, dice, extra, damageType) ⇒

        logger.trace(s"${infoText} matched")
        val msgDiceMultiplier = s"Dice Multiplier: ${wDamage}\nDice: ${dice}\nExtra: ${extra}\nDamageType: ${damageType}"
        logger.info(msgDiceMultiplier)

        Some(damageExtractor(
          Option(wDamage) match {
            case Some(x) ⇒ wDamage.toInt
            case _       ⇒ 0
          },
          dice,
          Option(extra) match {
            case Some(x) ⇒ extra.replaceAll(Space, EmptyString).toInt
            case _       ⇒ 0
          },
          damageType.split(Comma) toList))
      case _ ⇒ {
        logger.warn(s"Could not match infostring to damage extractor ${infoText}")
        None
      }
    }
  }

  /** encapsulates the critical damage profile
    *
    * @example a typical weapon may have a damage range of 1-20 with a bonus for
    * certain high rolls.  The scimatar for example may have 1-20, with 18-20 receiving
    * a bonus damage multiplier of double damage.  This is written in terms of 18-20 x3.
    *
    * programatically, this would be represented as
    * {{{
    * val cp = critProfile(min = 18, max=20, multiplier = 2)
    * }}}
    * @param min minimum damage roll
    * @param max maximum damage roll
    * @multiplier amount to multiply when a given roll is within the min / max range.
    *
    */
  case class critProfile(min: Int, max: Int, multiplier: Int)

  /** Extracts the critical profile information from a string representation.
    * @param infoText text containing a min - max x: Multiplier, i.e. 19-20 x3
    * @return a [[critProfile]] or None if there was no parsable value found.
    */
  def extractCriticalProfile(infoText: String): Some[critProfile] = {

    /** Regular expression used to translate D &amp; D text to object notation.
      */
    val regCritical = """(?<min>\d+)?-?(?<max>\d+)\s*/\s*x(?<multiplier>\d+)""".r
    infoText match {
      case regCritical(min, max, multiplier) ⇒ {
        logger.info(s"critical profile: ${infoText}")
        Some(critProfile(
          if (min == null) max.toInt else min.toInt, // scalastyle:off null
          max toInt,
          multiplier toInt))
      }
      case _ ⇒ logger.error(s"argument could not be parsed: ${infoText}"); throw new IllegalArgumentException
    }
  }

  // Implicit to safely attempt String to Int conversion
  private def tryToInt(s: String) = Try(s.toInt).toOption

  /** Encapsulates Weapon Category and Damage Information
    *
    * @param category The category such as Club or Bastard Sword
    * @param physicalDamageType The type of damage such as Bludgeon or Pierce
    *
    */
  case class WeaponClassInfo(category: WeaponCategory, physicalDamageType: PhysicalDamageType)

  /** Extracts the proficiency such as Martial Weapon
    * @param source Text or HTML fragment containing the information
    * @return Proficiency Class or None if invalid or missing
    */
  def proficiency(source: Map[String, Any]): Option[ProficiencyClass] = {
    source.get(Field.ProficiencyClass) match { //  Simple Weapon Proficiency
      case Some(name: String) ⇒ ProficiencyClass.withName(name, true) match {
        case Some(pc) ⇒ Some(pc.asInstanceOf[ProficiencyClass])
        case _        ⇒ logger.error(s"Invalid Proficiency Class ${name}"); None
      }
      case Some(x: Element) ⇒ ProficiencyClass.withName(x.text, true) match {
        case Some(pc) ⇒ Some(pc.asInstanceOf[ProficiencyClass])
        case _        ⇒ logger.error(s"Invalid Proficiency Class ${x}"); None
      }
      case _ ⇒ None
    }
  }

  /** Extracts damage information from text or HTML Fragment
    * @param source Text or HTML fragment containing the information
    * @return Damage information or None if missing or invalid data.
    */
  def damage(source: Map[String, Any]): Option[damageExtractor] = {
    simpleExtractor(source.get(Field.DamageAndType)) match {
      case Some(data) ⇒ {
        logger.info(s"extracting damageInfo from ${data}")
        extractDamageInfo(data)
      }
      case _ ⇒ None
    }
  }

  /** Extracts the Critical Threat information
    * @param source Text or HTML fragment containing source information
    * @return Critical Information or None if missing or invalid data
    */
  def criticalThreat(source: Map[String, Any]): Option[CriticalThreatRange] = {
    simpleExtractor(source.get(Field.CriticalThreatRange)) match { // "Critical threat range"//  19-20/x2
      case Some(x: String) ⇒ {
        extractCriticalProfile(x) match {
          case Some(x) ⇒ {
            Some(new CriticalThreatRange(
              x.min to x.max,
              x.multiplier))
          }
          case _ ⇒ None
        }
      }
      case _ ⇒ None
    }
  }

  /** Extracts Weapon Category information
    * @param source Text or HTML fragment containing desired information
    * @return Weapon Category information or None if missing / invalid data
    */
  def weaponCategoryInfo(source: Map[String, Any]): Option[WeaponCategory] = {
    simpleExtractor(source.get(Field.WeaponTypeAndDamageType)) match {
      case Some(x: String) ⇒ {
        //   auditSuccess += WeaponTypeAndDamageType
        val s = x.split(ForwardSlash)
        WeaponCategory.withName(s(0).replace(Space, EmptyString), true) match {
          case Some(weap) ⇒ Some(weap.asInstanceOf[WeaponCategory])
          case _          ⇒ logger.error(s"Unknown Weapon Category ${s}"); None
        }
      }
      case _ ⇒ logger.error(s"Failed to parse ${Field.WeaponTypeAndDamageType} (No result)"); None
    }
  }

  /** Extracts Race Requirements, if any
    * @param source Text or HTML fragment containing desired information
    * @return Collection of Race ID's or empty list if none found
    */
  def requiredRace(source: Map[String, Any]): List[String] = {
    // TODO: Should we return an Option vs empty list?
    simpleExtractor(source.get(Field.RaceAbsRequired)) match {
      case Some(x: String) ⇒
        x.split(Comma).toList
      case Some(x) ⇒
        logger.error(s"Failed to parse ${Field.RaceAbsRequired} from ${x}"); Nil
      case _ ⇒ logger.error(s"Failed to parse ${Field.RaceAbsRequired} (No result)"); Nil
    }
  }

  /** Extracts Minimum level information
    * @param source Text or HTML fragment containing desired information
    * @return Int value of minimum level.
    */
  def minimumLevel(source: Map[String, Any]): Int = {
    // TODO: Should we return Option vs default zero assumption?
    simpleExtractor(source.get(Field.ML)) match {
      case Some(x) ⇒ {
        //    import org.aos.ddo.web.StringUtils._
        x.toIntOpt match {
          case Some(x: Int) ⇒ x
          case _            ⇒ logger.error(s"Failed to parse ${Field.ML} with ${x}"); 0
        }
      }
      case _ ⇒ logger.info("No ML value specified for object, assuming zero"); 0
    }
  }

  /** Extracts Required traits, if any
    * @param source Text or HTML fragment with desired information
    * @return Collection of Trait IDs or empty list if none found
    *
    * @note  Currently treating this as a list, I believe there is only one value at most, but
    * taking the performance hit over code breaking.
    * One potential use case is a aligned race restricted item.
    * Will try to update and streamline this oncce we can locate better source data
    */
  def requiredTrait(source: Map[String, Any]): List[String] = {
    // TODO: Return Option instead of empty list
    source.get(Field.RequiredTrait) match {
      case Some(x: String) if x != "None" ⇒ {
        x.split(Comma).toList
      }
      case Some(x: String) if x == "None" ⇒
        logger.info(s"No required traits assigned, value was ${x}"); Nil
      case _ ⇒ logger.info("No required traits discovered"); Nil
    }
  }

  /** Extracts Use Magical Device information, if any
    * @param source Text or HTML fragment with desired information
    * @return Int or UMD check, defaulting to zero if none found.
    */
  def umdDc(source: Map[String, Any]): Int = {
    // TODO: Return Option if none listed
    source.get(Field.UMD) match {
      case Some(x: Int) ⇒
        x
      case _ ⇒ logger.info("No UMD specified, assuming zero"); 0
    }
  }

  /** Extracts handedness (Off-hand, main-hand, two-handed etc) from source
    * @param source Text or HTML fragment
    * @return list of valid equip locations.
    */
  def handedness(source: Map[String, Any]): List[Handedness] = {
    // TODO: Handedness should throw an error or option, likely error
    source.get(Field.Handedness) match {
      case Some(x: String) ⇒ {
        logger.info(s"Handedness located!!! ${x}")
        x.split(Comma).filter { name ⇒ Handedness.withName(name, true) != None }
          .map { name ⇒
            Handedness.withName(name, true)
              .asInstanceOf[Handedness]
          }.toList
      }
      case _ ⇒ logger.error("Failed to retrieve Handedness"); Nil
    }
  }

  /** Extracts Attack Modifier Attributes
    * @param source Text or HTML fragment with desired information
    * @return List of Attributes or empty list if none found.
    */
  def attackModifier(source: Map[String, Any]): List[Attributes] = {
    simpleExtractor(source.get(Field.AttackMod)) match {
      case Some(x: String) ⇒ {
        logger.debug(s"AttackMod returned ${x}")
        x.split(",").toList.filter { name ⇒ Attributes.withNameOption(name) != None }.map { name ⇒ Attributes.withName(name) }
      }
      case _ ⇒ logger.error(s"No match found for ${Field.AttackMod}"); Nil
    }
  }

  /** Extracts Attributes used to determine Damage Modifier
    * @param source Text or HTML fragment with desired information
    * @return List of Attributes or empty list if none found.
    */
  def damageModifier(source: Map[String, Any]): List[Attributes] = {
    simpleExtractor(source.get(Field.DamageMod)) match {
      case Some(x: String) ⇒ {
        logger.debug(s"DamageMod returned ${x}")
        x.split(",").toList.filter { name ⇒ Attributes.withNameOption(name) != None }.map { name ⇒ Attributes.withName(name) }
      }
      case _ ⇒ logger.error(s"No match found for ${Field.DamageMod}"); Nil
    }
  }

  /** Extracts binding information (i.e. bound to Character etc)
    * @param source Text or HTML fragment with desired information
    * @return Binding status or None if missing / invalid data
    */
  def binding(source: Map[String, Any]): Option[BindingFlags] = {
    simpleExtractor(source.get(Field.Binding), "a") match {
      case Some(x: String) ⇒
        BindingFlags.fromWords(x)
      case _ ⇒ logger.debug(s"Failed to retrieve ${Field.Binding}"); None // TODO: Do we return default binding if none specified?
    }
  }

  /** Extracts durability for item.
    * @param source /**
    * @param source
    * @return
    * */
    * Text or HTML fragment with desired information
    * @return Int value of durability, defaults to zero if none found.
    */
  def durability(source: Map[String, Any]): Int = {
    simpleExtractor(source.get(Field.Durability)) match {
      case Some(x: String) ⇒ {
        tryToInt(x.replace(" ", "")) match {
          case Some(x: Int) ⇒
            x
          case _ ⇒
            logger.warn(s"None numeric value found for ${Field.Durability} (${x}), defaulting to 0"); 0
        }
      }
      case _ ⇒ logger.warn(s"No value found for ${Field.Durability}, defaulting to 0"); 0
    }
  }

  /** Extracts material information
    * @param source Text or HTML fragment with desired information
    * @return Material or None if missing / invalid data
    */
  def madeFrom(source: Map[String, Any]): Option[Material] = {
    // TODO: may need to throw error for unknown or unspecified materials
    simpleExtractor(source.get(Field.Material), "a") match {
      case Some(x: String) ⇒ {
        Material.withNameOption(x) match {
          case Some(name) ⇒ {
            Some(name)
          }
          case _ ⇒ {
            logger.warn(s"No material found by name of ${x}, if this is a new material, it needs to be added to the enumeration")
            None
          }
        }
      }
      case _ ⇒ {
        logger.warn(s"No value supplied for ${Field.Material}")
        None
      }
    }
  }

  /** Extracts Hardness information
    * @param source Text or HTML fragment with desired information
    * @return Int value for hardness , defaulting to zero if missing / invalid data
    */
  def hardness(source: Map[String, Any]): Int = {
    simpleExtractor(source.get(Field.Hardness)) match {
      case Some(x: String) ⇒ {
        tryToInt(x.replace(" ", "")) match {
          case Some(x: Int) ⇒
            x
          case _ ⇒
            logger.warn(s"None numeric value found for ${Field.Hardness} (${x}), defaulting to 0"); 0
        }
      }
      case _ ⇒ logger.warn(s"No value found for ${Field.Hardness}, defaulting to 0"); 0
    }
  }

  /** Extracts the base monetary value
    * @param source Text or HTML fragment
    * @return Base value in platinum or None if missing / invalid data
    */
  def baseValue(source: Map[String, Any]): Option[Int] = {
    // TODO: BaseValue - Need a sensible default for base value or make option if none supplied
    val rex = """(?<num>\d+,?\d+)""".r
    source.get(Field.BaseValue) match {
      case Some(x: Element) ⇒ {

        val ele = x.select("td").first().textNodes()
        val filtered = ele.map { x ⇒
          x.text() match {
            case rex(num) ⇒ Some(NumberFormat.getInstance(java.util.Locale.US).parse(num))
            case _        ⇒ None
          }
        }.filter { x ⇒ x.isDefined }.map { x ⇒ x.get }.toList
        filtered.size match {
          case 1 ⇒ Some(filtered.get(0).intValue())
          case 0 ⇒
            logger.info(s"could not extract number from ${x}"); None
          case _ ⇒ {
            logger.warn(s"Multiple values returned for basevalue ${x}, returning the first")
            Some(filtered.get(0).intValue())
          }
        }
      }
      case _ ⇒ logger.info(s"${Field.BaseValue} was not supplied"); None
    }
  }

  /** Wraps an Int value into a Coin object
    * @param baseValue amount of platinum pieces
    * @return wrapped object using given value.
    */
  def monetaryValue(baseValue: Option[Int]): Option[MonetaryValue.Coins] = {
    baseValue match {
      case Some(plat) ⇒ Some(MonetaryValue.Coins(plat = plat))
      case _          ⇒ None
    }
  }

  /** Item weight
    * @param source Text or HTML fragment
    * @return Weight in pounds, if supplied
    */
  def weight(source: Map[String, Any]): Option[Int] = {
    simpleExtractor(source.get(Field.Weight)) match {
      case Some(x: String) ⇒
        val rex = """(?<num>\d+,?\d*) lbs""".r
        x match {
          case rex(num) ⇒ Some(NumberFormat.getInstance(java.util.Locale.US).parse(num).intValue())
          case _        ⇒ None
        }
      case _ ⇒ logger.info(s"${Field.Weight} not supplied"); None
    }
  }

  /** Item source location
    * @param source Text or HTML fragment
    * @return location text, if found
    */
  def location(source: Map[String, Any]): Option[String] = {
    // TODO: Location - Need a sensible default for base value or make option if none supplied
    simpleExtractor(source.get(Field.Location)) match {
      case Some(x: String) ⇒
        Some(x)
      case _ ⇒ logger.info(s"${Field.Location} was not supplied"); None
    }
  }

  /** item effects
    * @param source Source Text or HTML fragment
    * @return collection of enchantments
    */
  def enchantments(source: Map[String, Any]): Option[Any] = {
    // TODO: Do we want to alter this from Any to Either[String,Element]?
    source.get(Field.Enchantments)
  }

  /** Extracts any upgrade information text
    * @param source Text or HTML fragment
    * @return Upgrade info with any information text found
    */
  def upgradable(source: Map[String, Any]): UpgradeInfo = {
    simpleExtractor(source.get(Field.Upgradeable)) match {
      case Some(x: String) ⇒
        new UpgradeInfo {
          val text = Some(x)
        }
      case _ ⇒
        logger.warn(s"Failed to retrieve ${Field.Upgradeable}")
        new UpgradeInfo {
          val text = None
        } // TODO: Message should only warn if no upgrade value was specified
    }
  }

  /** Extracts any descriptive text
    * @param source Text or HTML fragment
    * @return Descriptive text if found.
    */
  def description(source: Map[String, Any]): Option[String] = {
    simpleExtractor(source.get(Field.Description)) match {
      case Some(x: String) ⇒
        Some(x)
      case _ ⇒ logger.warn(s"Failed to retrieve ${Field.Description}"); None // TODO: Message should only warn or info? if no description was specified
    }
  }

  /** Extracts Weapon type (Melee, Ranged, thrown etc)
    * @param wc Wrapper generally retrieved through [[WeaponCategoryInfo]] information
    * @return
    */
  def weaponType(wc: Option[WeaponCategory]): Option[WeaponType] = {
    wc match {
      case Some(x: MeleeDamage)   ⇒ Some(WeaponType.Melee)
      case Some(x: RangeDamage)   ⇒ Some(WeaponType.Ranged)
      case Some(x: ThrownDamage)  ⇒ Some(WeaponType.Thrown)
      case Some(x: SpecialDamage) ⇒ Some(WeaponType.Special)
      case _                      ⇒ None
    }
  }

  /** Wraps Threat information into a Profile object
    * @param ctr Threat Information
    * @return
    */
  def criticalProfile(ctr: Option[CriticalThreatRange]): Option[CriticalProfile] = {
    ctr match {
      case Some(profile) ⇒ Some(new CriticalProfile {
        val min = profile.range.min
        val max = profile.range.max
        val multiplier = profile.multiplier
      })
      case _ ⇒ None
    }
  }

  /** Extracts damage dice
    * @param dts Damage extraction helper object
    * @return Damage dice as string
    */
  def damageDie(dts: Option[damageExtractor]): Option[String] = {
    dts match {
      case Some(x) ⇒ Some(x.dice)
      case _       ⇒ None
    }
  }

  /** Extracts the Damage type from the extraction helper object
    * @param dts utility helper object
    * @return List of damage types
    */
  def damageAppliedType(dts: Option[damageExtractor]): List[String] = {
    dts match {
      case Some(x) ⇒ x.damageType
      case _       ⇒ Nil
    }
  }

  /** Extracts the weapon modifier value from the utility helper object
    * @param dts utility helper object
    * @return Int value of the modifier, defaulting to 0
    */
  def weaponModifier(dts: Option[damageExtractor]): Int = {
    dts match {
      // TODO: Use optInt for weaponModifier?
      case Some(x) ⇒ x.wMod.toInt
      case _       ⇒ 0
    }
  }

  /** Populates a DamageInfo object from a helper object
    * @param dts helper object
    * @return populated [[DamageInfo]] object
    */
  def damageValue(dts: Option[damageExtractor]): Option[DamageInfo] = {
    dts match {
      case Some(dmg) ⇒ Some(new org.aos.ddo.DamageInfo {
        val weaponModifier = dmg.wMod
        val dice = dmg.dice
        val extra = dmg.extra
        val damageType = dmg.damageType
      })
      case _ ⇒ None
    }
  }
}
