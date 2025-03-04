/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WikiParser.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.web.mapping

import java.text.NumberFormat
import com.typesafe.scalalogging.LazyLogging
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.model.Element
import io.truthencode.ddo._
import io.truthencode.ddo.enumeration.EnumExtensions.EnumCompanionOps
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.item.weapon._
import io.truthencode.ddo.model.meta.PhysicalDamageType
import io.truthencode.ddo.model.misc.Material
import io.truthencode.ddo.support.StringUtils.{
  Comma,
  EmptyString,
  ForwardSlash,
  Space,
  StringImprovements
}
import io.truthencode.ddo.support.dice.{DamageInfo, Dice}
import io.truthencode.ddo.support.matching.{WordMatchStrategies, WordMatchStrategy}
import io.truthencode.ddo.web.mapping.ElementSupport.ElementToElementOps
import io.truthencode.ddo.web.mapping.Extractor._
import scala.jdk.CollectionConverters._
//import scala.collection.JavaConverters._
import scala.language.{existentials, postfixOps, reflectiveCalls}
import scala.util.Try

/**
 * Contains functions used to scrape DDOWiki when needed.
 */
//noinspection ScalaStyle
object WikiParser extends LazyLogging {

  /**
   * Extracts the proficiency such as Martial Weapon
   *
   * @param source
   *   Text or HTML fragment containing the information
   * @return
   *   Proficiency Class or None if invalid or missing
   */
  def proficiency(source: Map[String, Any]): Option[ProficiencyClass] = {
    source.get(Field.ProficiencyClass) match {
      //  Simple Weapon Proficiency
      case Some(name: String) =>
        ProficiencyClass.withName(name, true) match {
          case Some(pc) => Some(pc.asInstanceOf[ProficiencyClass])
          case _ => logger.error(s"Invalid Proficiency Class $name"); None
        }
      case Some(x: Element) =>
        ProficiencyClass.withName(x.text, true) match {
          case Some(pc) => Some(pc.asInstanceOf[ProficiencyClass])
          case _ => logger.error(s"Invalid Proficiency Class $x"); None
        }
      case _ => None
    }
  }

  /**
   * Extracts damage information from text or HTML Fragment
   *
   * @param source
   *   Text or HTML fragment containing the information
   * @return
   *   Damage information or None if missing or invalid data.
   */
  def damage(source: Map[String, Any]): Option[DamageInfo] = {
    simpleExtractor(source.get(Field.DamageAndType)) match {
      case Some(data) =>
        logger.info(s"extracting damageInfo from $data")
        extractDamageInfo(data)
      case _ => None
    }
  }

  /**
   * Extracts the Critical Threat information
   *
   * @param source
   *   Text or HTML fragment containing source information
   * @return
   *   Critical Information or None if missing or invalid data
   */
  def criticalThreat(source: Map[String, Any]): Option[CriticalThreatRange] = {
    simpleExtractor(source.get(Field.CriticalThreatRange)) match {
      // "Critical threat range"//  19-20/x2
      case Some(x: String) =>
        extractCriticalProfile(x) match {
          case Some(profile) =>
            Some(CriticalThreatRange(profile.min to profile.max, profile.multiplier))
          case _ => None
        }
      case _ => None
    }
  }

  /**
   * Extracts Weapon Category information
   *
   * @param source
   *   Text or HTML fragment containing desired information
   * @return
   *   Weapon Category information or None if missing / invalid data
   */
  def weaponCategoryInfo(source: Map[String, Any]): Option[WeaponCategory] = {
    simpleExtractor(source.get(Field.WeaponTypeAndDamageType)) match {
      case Some(x: String) =>
        val s = x.split(ForwardSlash)

        WeaponCategory.withName(s(0).replace(Space, EmptyString), true) match {
          case Some(category) => Some(category.asInstanceOf[WeaponCategory])
          case _ => logger.error(s"Unknown Weapon Category $s"); None
        }
      case _ =>
        logger.error(s"Failed to parse ${Field.WeaponTypeAndDamageType} (No result)")
        None
    }
  }

  /**
   * Extracts Race Requirements, if any
   *
   * @param source
   *   Text or HTML fragment containing desired information
   * @return
   *   Collection of Race ID's or empty list if none found
   */
  def requiredRace(source: Map[String, Any]): List[String] = {
    // TODO: Should we return an Option vs empty list?
    simpleExtractor(source.get(Field.RaceAbsRequired)) match {
      case Some(x: String) =>
        x.split(Comma).toList
      case Some(x) =>
        logger.error(s"Failed to parse ${Field.RaceAbsRequired} from $x"); Nil
      case _ =>
        logger.error(s"Failed to parse ${Field.RaceAbsRequired} (No result)")
        Nil
    }
  }

  /**
   * Extracts Minimum level information
   *
   * @param source
   *   Text or HTML fragment containing desired information
   * @return
   *   Int value of minimum level.
   */
  def minimumLevel(source: Map[String, Any]): Int = {
    // TODO: Should we return Option vs default zero assumption?
    simpleExtractor(source.get(Field.ML)) match {
      case Some(x) =>
        //    import io.truthencode.ddo.web.StringUtils._
        x.toIntOpt match {
          case Some(x: Int) => x
          case _ => logger.error(s"Failed to parse ${Field.ML} with $x"); 0
        }
      case _ =>
        logger.info("No ML value specified for object, assuming zero"); 0
    }
  }

  /**
   * Extracts Required traits, if any
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Collection of Trait IDs or empty list if none found
   * @note
   *   Currently treating this as a list, I believe there is only one value at most, but taking the
   *   performance hit over code breaking. MustContainAtLeastOne potential use case is a aligned
   *   race restricted item. Will try to update and streamline this once we can locate better source
   *   data
   *
   * Also, the value 'None' may be included in the text and is filtered out.
   */
  def requiredTrait(source: Map[String, Any]): List[String] = {
    // TODO: Return Option instead of empty list
    source.get(Field.RequiredTrait) match {
      case Some(x: String) if x != "None" =>
        x.split(Comma).toList
      case Some(x: String) =>
        logger.info(s"No required traits assigned, excluded value was $x"); Nil
      case _ => logger.info("No required traits discovered"); Nil
    }
  }

  /**
   * Extracts Use Magical Device information, if any
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Int or UMD check, defaulting to zero if none found.
   */
  def umdDc(source: Map[String, Any]): Int = {
    // TODO: Return Option if none listed
    source.get(Field.UMD) match {
      case Some(x: Int) =>
        x
      case _ => logger.info("No UMD specified, assuming zero"); 0
    }
  }

  /**
   * Extracts handedness (Off-hand, main-hand, two-handed etc) from source
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   list of valid equip locations.
   */
  def handedness(source: Map[String, Any]): List[Handedness] = {
    // TODO: Handedness should throw an error or option, likely error
    source.get(Field.Handedness) match {
      case Some(x: String) =>
        logger.info(s"Handedness located!!! $x")
        x.split(Comma)
          .filter { name =>
            Handedness.withName(name, true).isDefined
          }
          .map { name =>
            Handedness
              .withName(name, true)
              .asInstanceOf[Handedness]
          }
          .toList
      case _ => logger.error("Failed to retrieve Handedness"); Nil
    }
  }

  /**
   * Extracts Attack Modifier Attribute
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   List of Attribute or empty list if none found.
   */
  def attackModifier(source: Map[String, Any]): List[Attribute] = {
    for
      e <- List(simpleExtractor(source.get(Field.AttackMod))).flatten
      x <- e.split(Comma)
      y <- Attribute.values.find(a => a.toString == x)
    yield y
  }

  /**
   * Extracts Attribute used to determine Damage Modifier
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   List of Attribute or empty list if none found.
   */
  def damageModifier(source: Map[String, Any]): List[Attribute] = {
    for
      e <- List(simpleExtractor(source.get(Field.DamageMod))).flatten
      x <- e.split(Comma)
      y <- Attribute.values.find(a => a.toString == x)
    yield y
  }

  /**
   * Extracts binding information (i.e. bound to Character etc)
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Binding status or None if missing / invalid data
   */
  def binding(source: Map[String, Any])(implicit
    strategy: WordMatchStrategy): Option[BindingFlags] = {
    simpleExtractor(source.get(Field.Binding), "a") match {
      case Some(x: String) =>
        BindingFlags.fromWords(x)
      case _ =>
        logger.debug(s"Failed to retrieve ${Field.Binding}");
        None // TODO: Do we return default binding if none specified?
    }
  }

  /**
   * Extracts durability for item.
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Int value of durability, defaults to zero if none found.
   */
  def durability(source: Map[String, Any]): Int = {
    simpleExtractor(source.get(Field.Durability)) match {
      case Some(x: String) =>
        tryToInt(x.replace(" ", "")) match {
          case Some(x: Int) =>
            x
          case _ =>
            logger.warn(s"None numeric value found for ${Field.Durability} ($x), defaulting to 0")
            0
        }
      case _ =>
        logger.warn(s"No value found for ${Field.Durability}, defaulting to 0")
        0
    }
  }

  // Implicit to safely attempt String to Int conversion
  private def tryToInt(s: String) = Try(s.toInt).toOption

  /**
   * Extracts material information
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Material or None if missing / invalid data
   */
  def madeFrom(source: Map[String, Any]): Option[Material] = {
    // TODO: may need to throw error for unknown or unspecified materials
    simpleExtractor(source.get(Field.Material), "a") match {
      case Some(x: String) =>
        Material.withNameOption(x) match {
          case Some(name) =>
            Some(name)
          case _ =>
            logger.warn(
              s"No material found by name of $x, if this is a new material, it needs to be added to the enumeration")
            None
        }
      case _ =>
        logger.warn(s"No value supplied for ${Field.Material}")
        None
    }
  }

  /**
   * Extracts Hardness information
   *
   * @param source
   *   Text or HTML fragment with desired information
   * @return
   *   Int value for hardness , defaulting to zero if missing / invalid data
   */
  def hardness(source: Map[String, Any]): Int = {
    simpleExtractor(source.get(Field.Hardness)) match {
      case Some(x: String) =>
        tryToInt(x.replace(" ", "")) match {
          case Some(x: Int) =>
            x
          case _ =>
            logger.warn(s"None numeric value found for ${Field.Hardness} ($x), defaulting to 0")
            0
        }
      case _ =>
        logger.warn(s"No value found for ${Field.Hardness}, defaulting to 0")
        0
    }
  }

  /**
   * Extracts the base monetary value
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   Base value in platinum or None if missing / invalid data
   */
  def baseValue(source: Map[String, Any]): Option[Int] = {
    // TODO: BaseValue - Need a sensible default for base value or make option if none supplied
    val rex =
      """(?<num>\d+,?\d+)""".r
    source.get(Field.BaseValue) match {
      case Some(x: Element) =>
        val ele = x >> element("td:first-child")
        val filtered = ele.textNodes.asScala.map { x =>
          x.text match {
            case rex(num) =>
              Some(NumberFormat.getInstance(java.util.Locale.US).parse(num))
            case _ => None
          }
        }.filter { x =>
          x.isDefined
        }.map { x =>
          x.get
        }.toList
        filtered.size match {
          case 1 => Some(filtered.head.intValue())
          case 0 =>
            logger.info(s"could not extract number from $x"); None
          case _ =>
            logger.warn(s"Multiple values returned for basevalue $x, returning the first")
            Some(filtered.head.intValue())
        }
      case _ => logger.info(s"${Field.BaseValue} was not supplied"); None
    }
  }

  /**
   * Wraps an Int value into a Coin object
   *
   * @param baseValue
   *   amount of platinum pieces
   * @return
   *   wrapped object using given value.
   */
  def monetaryValue(baseValue: Option[Int]): Option[MonetaryValue.Coins] = {
    baseValue match {
      case Some(plat) => Some(MonetaryValue.Coins(plat = plat))
      case _ => None
    }
  }

  /**
   * Item weight
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   Weight in pounds, if supplied
   */
  def weight(source: Map[String, Any]): Option[Int] = {
    simpleExtractor(source.get(Field.Weight)) match {
      case Some(x: String) =>
        val rex = """(?<num>\d+,?\d*) lbs""".r
        x match {
          case rex(num) =>
            Some(
              NumberFormat
                .getInstance(java.util.Locale.US)
                .parse(num)
                .intValue())
          case _ => None
        }
      case _ => logger.info(s"${Field.Weight} not supplied"); None
    }
  }

  /**
   * Item source location
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   location text, if found
   */
  def location(source: Map[String, Any]): Option[String] = {
    // TODO: Location - Need a sensible default for base value or make option if none supplied
    simpleExtractor(source.get(Field.Location)) match {
      case Some(x: String) =>
        Some(x)
      case _ => logger.info(s"${Field.Location} was not supplied"); None
    }
  }

  /**
   * item effects
   *
   * @param source
   *   Source Text or HTML fragment
   * @return
   *   collection of enchantments
   */
  def enchantments(source: Map[String, Any]): Option[Seq[String]] = {
    // TODO: Do we want to alter this from Any to Either[String,Element]?
    val eSource = source
      .filter(e => e._2.isInstanceOf[Element])
      .map(f => f._1 -> f._2.asInstanceOf[Element])
    val leaves = enchantmentExtractor(eSource).map { x =>
      x.text
    }
    if leaves.isEmpty then None
    else Some(leaves)
  }

  /**
   * Extracts any upgrade information text
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   Upgrade info with any information text found
   */
  def upgradable(source: Map[String, Any]): UpgradeInfo = {
    simpleExtractor(source.get(Field.Upgradeable)) match {
      case Some(x: String) =>
        new UpgradeInfo {
          val text = Some(x)
        }
      case _ =>
        logger.warn(s"Failed to retrieve ${Field.Upgradeable}")
        new UpgradeInfo {
          val text: Option[String] = None
        } // TODO: Message should only warn if no upgrade value was specified
    }
  }

  /**
   * Extracts any descriptive text
   *
   * @param source
   *   Text or HTML fragment
   * @return
   *   Descriptive text if found.
   */
  def description(source: Map[String, Any]): Option[String] = {
    simpleExtractor(source.get(Field.Description)) match {
      case Some(x: String) =>
        Some(x)
      case _ =>
        logger.warn(s"Failed to retrieve ${Field.Description}");
        None // TODO: Message should only warn or info? if no description was specified
    }
  }

  /**
   * Extracts Weapon type (Melee, Ranged, thrown etc)
   *
   * @param wc
   *   Wrapper generally retrieved through [[WeaponCategory]] information
   * @return
   */
  def weaponType(wc: Option[WeaponCategory]): Option[DeliveryType] = {
    wc match {
      case Some(_: MeleeDamage) => Some(DeliveryType.Melee)
      case Some(_: RangeDamage) => Some(DeliveryType.Ranged)
      case Some(_: ThrownDamage) => Some(DeliveryType.Thrown)
      case Some(_: SpecialDamage) => Some(DeliveryType.Special)
      case _ => None
    }
  }

  /**
   * Wraps Threat information into a Profile object
   *
   * @param ctr
   *   Threat Information
   * @return
   */
  def criticalProfile(ctr: Option[CriticalThreatRange]): Option[CriticalProfile] = {
    ctr match {
      case Some(profile) =>
        Some(new CriticalProfile {
          val min: Int = profile.range.min
          val max: Int = profile.range.max
          val multiplier: Int = profile.multiplier
        })
      case _ => None
    }
  }

  /**
   * Extracts damage dice
   *
   * @param dts
   *   Damage extraction helper object
   * @return
   *   Damage dice as string
   */
  def damageDie(dts: Option[damageExtractor]): Option[String] = {
    dts match {
      case Some(x) => Some(x.dice)
      case _ => None
    }
  }

  /**
   * Extracts the Damage type from the extraction helper object
   *
   * @param dts
   *   utility helper object
   * @return
   *   List of damage types
   */
  def damageAppliedType(dts: Option[damageExtractor]): List[String] = {
    dts match {
      case Some(x) => x.damageType
      case _ => Nil
    }
  }

  /**
   * Extracts the weapon modifier value from the utility helper object
   *
   * @param dts
   *   utility helper object
   * @return
   *   Int value of the modifier, defaulting to 0
   */
  def weaponModifier(dts: Option[damageExtractor]): Int = {
    dts match {
      // TODO: Use optInt for weaponModifier?
      case Some(x) => x.wMod
      case _ => 0
    }
  }

  /**
   * Populates a DamageInfo object from a helper object
   *
   * @param dts
   *   helper object
   * @return
   *   populated [[DamageInfo]] object
   */
  def damageValue(dts: Option[damageExtractor]): Option[DamageInfo] = {
    dts match {
      case Some(dmg) =>
        val w = dmg.wMod
//    val d = Dice(dmg.dice)
        val e = ExtraInfo(dmg.extra)
        val dt = dmg.damageType.flatMap(PhysicalDamageType.withNameInsensitiveOption)
        Some(
          DamageInfo(
            w = dmg.wMod,
            d = Dice(1, 1), // TODO: Replace with actual implementation
            e = ExtraInfo(dmg.extra),
            dt = dmg.damageType.flatMap(PhysicalDamageType.withNameInsensitiveOption)
          ))
      case _ => None
    }
  }

}
