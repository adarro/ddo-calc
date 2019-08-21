package org.aos.ddo.etl.affix

import com.typesafe.scalalogging.LazyLogging
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.element
import org.aos.ddo.etl._
import org.aos.ddo.etl.affix.Categories.Cat

/**
  * Created by adarr on 5/8/2017.
  */
object RandomLootAffixRules extends LazyLogging {

  type CategoryMap = Map[String, String]
  /**
    * Maps a single affix part to multiple affixes.
    * This is used
    * $ - to separate a general group shortcut, such as listing spell lore as Dire(spell power) and expand to Dire Combustive..
    * $ - to overwrite extraneous text.
    */
  type AffixMap = Map[String, List[String]]
  /**
    * Listed Skill prefixes
    *
    * @note
    * errata: Import source currently lists Spellcraft / Spellsight, but is not listed in the
    * category as a prefix.
    * Jump: excluded due to unknown type
    * @return
    */
  val SkillsPrefix: Iterable[RandomLootNamingRule] = {
    val cAffix: Option[AffixMap] = Some(
      Map("Spellcraft" -> List("Spellsight"))
    )
    val cMap = Some(Map("Visionary's" -> "Spellsight"))
    val excluded = List("Visionary's", "Jump", "Spot", "Swim")
    parseTable(skillPrefixTable, customCategory = cMap, customAffix = cAffix, exclusions = excluded)
  }

  def parseList(
                 e: Element,
                 parentCategory: Option[String] = None,
                 customCategory: Option[CategoryMap] = None,
                 customAffix: Option[AffixMap] = None,
                 exclusions: List[String] = Nil): Iterable[RandomLootNamingRule] = {
    if (exclusions.nonEmpty) {
      val sep = " - "
      logger.warn(
        s"The following item(s) are being explicitly excluded from processing: ${
          exclusions
            .mkString(sep)
        }")
    }
    val items =
      excludeLine(values = e.select("li").map(_.text.trim), exclusions)
    parse(items, parentCategory, customCategory, customAffix)
  }

  /**
    * Reads an HTML Table to extract affix values from wiki document.
    *
    * @param e              HTML fragment containing the table
    * @param parentCategory parent category for family grouping
    * @param customCategory custom category naming mostly for errata
    * @param customAffix    fixes for consistencies
    * @param exclusions     items to exclude that were included in the table but not known to be in use.
    *                       (i.e. the swim skill is listed in the prefixes but there is no information verifying this as a prefix.)
    * @return A collection of naming rules based on the table.
    */
  def parseTable(
                  e: Element,
                  parentCategory: Option[String] = None,
                  customCategory: Option[CategoryMap] = None,
                  customAffix: Option[AffixMap] = None,
                  exclusions: List[String] = Nil): Iterable[RandomLootNamingRule] = {
    if (exclusions.nonEmpty) {
      val sep = " - "
      logger.warn(
        s"The following item(s) are being explicitly excluded from processing: ${
          exclusions
            .mkString(sep)
        }")
    }

    val lines = e
      .select("tr:has(td)")
      .map(x => {
        val y = x.select("td").toList
        s"${y.head.text.trim} - ${y(1).text.trim}"
      })
    val items = excludeLine(lines, exclusions)
    parse(items, parentCategory, customCategory, customAffix)

  }

  /**
    * Excludes inbound text if it contains certain keywords.
    *
    * @note This is needed as an arbitrary filter to process / exclude invalid data.
    *       For instance, Deadly is listed as a valid prefix in the source, but does not appear under any prefix category.
    * @param values     input source line. Generally text from a list or table
    * @param exclusions keywords used to flag exclusion
    * @return filtered list of values.
    */
  def excludeLine(values: Iterable[String],
                  exclusions: List[String]): Iterable[String] = {
    values.filterNot { v =>
      val r = for {
        x <- exclusions
        if v.contains(x)
      } yield v
      r.nonEmpty
    }
  }

  def parse(
             lines: Iterable[String],
             parentCategory: Option[String] = None,
             customCategory: Option[Map[String, String]] = None,
             customAffix: Option[AffixMap] = None): Iterable[RandomLootNamingRule] = {
    val r = for {
      item <- lines
      (cat, id) <- extractCategory(item, customAffix)
    } yield RandomLootNamingRule(makeCategory(cat, parentCategory), id)
    customCategory match {
      case Some(x) => customCategoryMap(r, x)
      case _ => r
    }
  }

  def makeCategory(category: String, parent: Option[String]): Cat = {
    if (parent.isDefined) {
      Cat(
        name = parent.get,
        subCategory = Some(category)
      )
    } else {
      Cat(category)
    }
  }

  /**
    * extracts the affix from the category.
    *
    * @note some affixes are hidden in the name although they do appear on the item.
    *       i.e. Boots with a Feather fall suffix and a Speed prefix will be displayed as 'Boots of Feather Fall'
    * @param s Text containing the affix / category.  The expected format is "Affixes (/ separated) - Category"
    *          If no delimiters are found, it is assumed the effect has no named affix.
    */
  def extractCategory(
                       s: String,
                       m: Option[AffixMap] = None): List[(String, List[String])] = {

    if (s.contains(" - ")) {
      val data = s.split(" - ")
      val cat = data(1).trim
      val affixes = for {x <- mapAffixes(data(0), m)} yield
        x.split("/").map(_.trim).toList
      List((cat, affixes.flatten))
    } else {
      mapAffixes(s, m).map((_, Nil))
    }
  }

  implicit class catOpts(source: Iterable[RandomLootNamingRule]) {
    def effectiveCategories: Iterable[String] = {
      source.map(_.category.effectiveName)
    }
  }

  def mapAffixes(affix: String, m: Option[AffixMap] = None): List[String] = {
    m match {
      case Some(x) if x.contains(affix) => x(affix)
      case _ => List(affix)
    }
  }

  def customCategoryMap(rules: Iterable[RandomLootNamingRule],
                        m: CategoryMap): Iterable[RandomLootNamingRule] = {
    rules.map { x =>
      m.find(_._1 == x.affixes.head) match {
        case Some(z) =>
          logger.info(s"Category Map ${z._1} - ${z._2}")
          x.copy(
            category = Cat(name = m(z._1),
              subCategory = Some(x.category.effectiveName)))
        case _ => x
      }
    }
  }

  def MappedPrefixEntries: List[Iterable[RandomLootNamingRule]] =
    List(
      RandomLootAffixRules.ElementalAbsorptionPrefix,
      RandomLootAffixRules.ElementalResistancePrefix,
      RandomLootAffixRules.BaseAbilityPrefix,
      SavesPrefix,
      DefensivePrefix,
      OffensivePrefix,
      TacticsPrefix,
      ThreatPrefix,
      SpellPowerPrefix,
      SpellFocusPrefix,
      WeaponDamagePrefix,
      SkillsPrefix,
      UnnamedPrefix
    )

  // Base abilities list (Stat i.e. Charisma, Strength etc.
  def baseAbilityTable = doc >> element("#mw-content-text > ul:nth-child(30)")

  def BaseAbilityPrefix: Iterable[RandomLootNamingRule] =
    parseList(baseAbilityTable)

  def elementalResistanceTable =
    doc >> element("#mw-content-text > ul:nth-child(32)")

  def ElementalResistancePrefix: Iterable[RandomLootNamingRule] =
    parseList(elementalResistanceTable, Some("Elemental Resistances"))

  def absorptionTable = doc >> element("#mw-content-text > ul:nth-child(34)")

  def ElementalAbsorptionPrefix: Iterable[RandomLootNamingRule] =
    parseList(absorptionTable, Some("Elemental Absorptions"))

  def savesTable = doc >> element("#mw-content-text > ul:nth-child(38)")

  /**
    * @note This list from update 29 contains some data inconsistencies and is using some exception handling
    * @return
    */
  def SavesPrefix: Iterable[RandomLootNamingRule] = {
    val resists = savesTable.filter(_.text.contains("Resist"))
    // val spell = savesTable diff resists
    val initial = parseList(savesTable, Some("Saves"))

    def isSpellSchoolSave(rule: RandomLootNamingRule): Boolean = {
      // Should this read the spell school enum from ddo-core? Only two schools are currently listed
      val schools = List("Enchantment", "Illusion")
      schools.contains(rule.category.effectiveName)
    }

    val spellSaves = (for {
      i <- initial
      if isSpellSchoolSave(i)
    } yield i).map { x =>
      x.copy(category = Cat(name = x.category.effectiveName))
    }
    val otherSaves = (for {
      i <- initial
      if !isSpellSchoolSave(i)
    } yield i).map { x =>
      x.copy(category = Cat("Individual Resist Saves", x.category.subCategory))
    }
    spellSaves ++ otherSaves
  }

  def defensivePrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(40)")

  /**
    * Hard-codes abound here.
    */
  def DefensivePrefix: Iterable[RandomLootNamingRule] = {
    val cMap = Some(
      Map("Evasive" -> "Dodge Bonus",
        "Ricocheting" -> "Protection",
        "Defensive" -> "Sheltering",
        "Reinforced" -> "Fortification",
        "Requiting" -> "Parrying"))
    parseList(defensivePrefixTable, customCategory = cMap)
  }

  def offensivePrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(42)")

  def OffensivePrefix: Iterable[RandomLootNamingRule] = {
    val cMap = Some(
      Map("Deft" -> "Seeker",
        "Hasty" -> "Doublestrike", ///Doubleshot
        "Murderous" -> "Assassinate")) // DC (ML30: +6))
    // Deadly doesn't appear to be a valid prefix (suffix / extra only)
    val exclusions: List[String] = List("Deadly")
    parseList(offensivePrefixTable,
      customCategory = cMap,
      exclusions = exclusions)
  }

  def tacticsPrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(44)")

  def TacticsPrefix: Iterable[RandomLootNamingRule] =
    parseList(tacticsPrefixTable)

  def threatPrefixTable = doc >> element("#mw-content-text > ul:nth-child(46)")

  def ThreatPrefix: Iterable[RandomLootNamingRule] = {
    val cMap = Some(Map("Enraging" -> "Incite", "Serene" -> "Diversion"))
    parseList(threatPrefixTable, customCategory = cMap)
  }

  def spellPowerPrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(48)")

  /**
    * @note Including Dire* properties
    * @return
    */
  def SpellPowerPrefix: Iterable[RandomLootNamingRule] = {
    val pl = parseList(spellPowerPrefixTable, Some("Spell Powers"))
    val plDire = pl.map(p =>
      p.copy(affixes = p.affixes.map { a =>
        s"Dire $a"
      }))
    pl ++ plDire
  }

  def spellFocusPrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(50)")

  def SpellFocusPrefix: Iterable[RandomLootNamingRule] =
    parseList(spellFocusPrefixTable, Some("Spell Focuses"))

  def weaponDamagePrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(54)")

  def WeaponDamagePrefix: Iterable[RandomLootNamingRule] =
    parseList(weaponDamagePrefixTable, Some("Elemental Damages"))

  def skillPrefixTable =
    doc >> element("#mw-content-text > table.wikitable.sortable > tbody")

  def doc: Document = loadLocalSourceHtml(local.toString)

  def unnamedPrefixTable =
    doc >> element("#mw-content-text > ul:nth-child(56)")

  def UnnamedPrefix: Iterable[RandomLootNamingRule] = {
    val cAffix = Some(
      Map(
        "Armor-Piercing" -> List("Armor Piercing"),
        "Alignment damage (Xd6 Chaotic, Evil, Good, presumably Lawful)" -> List(
          "Chaotic",
          "Evil",
          "Good",
          "Lawful").map(x => s"$x Damaging Effect")
      ))
    val cMap = Some(Map(
      "Alignment damage (Xd6 Chaotic, Evil, Good, presumably Lawful)" -> "Alignment Damages"))
    parseList(unnamedPrefixTable, customAffix = cAffix)
  }

}
