/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package org.aos.ddo.model.feat

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.item.weapon._
import org.aos.ddo.model.schools.School
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.SearchPrefix
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay, PostText, Prefix}
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Feats Feats]] are special abilities that give your character a new capability, or improves one he or she already has.
  */
sealed trait Feat extends EnumEntry with DisplayName with FriendlyDisplay with SubFeatInformation {
  self: FeatType with Requisite with Inclusion =>

  override protected def nameSource: String = entryName.splitByCase.toPascalCase
}


/**
  * @todo need to Sneak Attack to Class Feat
  * @todo move Half elf Dilettante feats to Racial Feat
  */
//noinspection ScalaStyle
object Feat extends Enum[Feat] with SearchPrefix {


  case object Attack extends Feat with Attack

  case object DefensiveFighting extends Feat with DefensiveFighting

  case object DismissCharm extends Feat with DismissCharm

  case object Sneak extends Feat with Sneak

  case object Sunder extends Feat with Sunder

  case object Trip extends Feat with Trip

  case object HeroicDurability extends Feat with HeroicDurability

  case object ImprovedHeroicDurability extends Feat with ImprovedHeroicDurability

  case object MagicalTraining extends Feat with MagicalTraining

  case object Cleave extends Feat with Cleave

  case object GreatCleave extends Feat with GreatCleave

  case object Hamstring extends Feat with Hamstring

  case object SneakAttack extends Feat with SneakAttack

  case object ImprovedFeint extends Feat with ImprovedFeint

  case object ImprovedSunder extends Feat with ImprovedSunder

  case object ImprovedTrip extends Feat with ImprovedTrip

  case object Sap extends Feat with Sap

  case object SlicingBlow extends Feat with SlicingBlow

  case object StunningBlow extends Feat with StunningBlow

  case object WhirlwindAttack extends Feat with WhirlwindAttack

  // Combat Stances
  case object CombatExpertise extends Feat with CombatExpertise

  case object Manyshot extends Feat with Manyshot

  case object TenThousandStars extends Feat with TenThousandStars

  case object PowerAttack extends Feat with PowerAttack

  case object ImprovedPreciseShot extends Feat with ImprovedPreciseShot

  case object Precision extends Feat with Precision

  case object Resilience extends Feat with Resilience

  // Passive Feats
  // General passive feats
  case object Diehard extends Feat with Diehard

  case object Dodge extends Feat with Dodge

  case object Mobility extends Feat with Mobility

  case object SpringAttack extends Feat with SpringAttack

  case object ImprovedCritical extends Feat with ImprovedCritical

  case object PowerCritical extends Feat with PowerCritical

  case object Toughness extends Feat with Toughness

  case object WeaponFinesse extends Feat with WeaponFinesse

  case object ShieldMastery extends Feat with ShieldMastery

  case object ImprovedShieldMastery extends Feat with ImprovedShieldMastery

  case object ImprovedShieldBash extends Feat with ImprovedShieldBash

  case object ShieldDeflection extends Feat with ShieldDeflection

  // Single Weapon Fighting Passive Feats

  case object SingleWeaponFighting extends Feat with SingleWeaponFighting

  case object ImprovedSingleWeaponFighting extends Feat with ImprovedSingleWeaponFighting

  case object GreaterSingleWeaponFighting extends Feat with GreaterSingleWeaponFighting

  case object TwoHandedFighting extends Feat with TwoHandedFighting

  case object ImprovedTwoHandedFighting extends Feat with ImprovedTwoHandedFighting

  case object GreaterTwoHandedFighting extends Feat with GreaterTwoHandedFighting

  // Two Weapon Passive Feats
  case object TwoWeaponFighting extends Feat with TwoWeaponFighting

  case object ImprovedTwoWeaponFighting extends Feat with ImprovedTwoWeaponFighting

  case object GreaterTwoWeaponFighting extends Feat with GreaterTwoWeaponFighting

  case object OversizedTwoWeaponFighting extends Feat with OversizedTwoWeaponFighting

  case object TwoWeaponDefense extends Feat with TwoWeaponDefense

  case object TwoWeaponBlocking extends Feat with TwoWeaponBlocking

  // Weapon Focus Passive Feats
  case object WeaponFocus extends Feat with WeaponFocusBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = weaponFocusAny
  }

  /**
    * Convenience function to group all Weapon Focus Feats
    *
    * @return
    */
  def weaponFocusAny: Seq[WeaponFocus] = {
    for {wc <- WeaponClass.values} yield WeaponFocus(wc)
  }

  // Seq(Feat.WeaponFocusBludgeon, Feat.WeaponFocusPiercing, Feat.WeaponFocusSlashing, Feat.WeaponFocusRanged, Feat.WeaponFocusThrown)

  case class WeaponFocus(weaponClass: WeaponClass) extends Feat with WeaponFocusBase with SubFeat with Prefix with FriendlyDisplay {
    override def prefix: Option[String] = Some("Weapon Focus")

    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText
  }

  case object GreaterWeaponFocus extends Feat with GreaterWeaponFocusBase with ParentFeat with RequiresAllOfFeat {
    override val subFeats: Seq[Feat with SubFeat] = greaterWeaponFocusAny

    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocus)
  }

  case class GreaterWeaponFocus(weaponClass: WeaponClass) extends Feat with GreaterWeaponFocusBase with RequiresAllOfFeat with SubFeat with Prefix {
    override def prefix: Option[String] = Some("GreaterWeaponFocus".splitByCase)

    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText

    override def allOfFeats: Seq[Feat] = weaponFocusAny.filter { x => x.weaponClass.eq(weaponClass) }
  }

  def greaterWeaponFocusAny: Seq[GreaterWeaponFocus] = {
    for {wc <- WeaponClass.values} yield GreaterWeaponFocus(wc)
  }

  case object SuperiorWeaponFocus extends Feat with SuperiorWeaponFocusBase with ParentFeat with RequiresAllOfFeat {
    override val subFeats: Seq[Feat with SubFeat] = superiorWeaponFocusAny

    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocus)
  }

  case class SuperiorWeaponFocus(weaponClass: WeaponClass) extends Feat with SuperiorWeaponFocusBase with RequiresAllOfFeat with SubFeat with Prefix with FriendlyDisplay {
    override def prefix: Option[String] = Some("SuperiorWeaponFocus".splitByCase)

    override protected def nameSource: String = weaponClass.displayText

    override protected val prefixSeparator: String = ": "

    override def allOfFeats: Seq[Feat] = greaterWeaponFocusAny.filter { x => x.weaponClass.eq(weaponClass) }
  }

  def superiorWeaponFocusAny: Seq[SuperiorWeaponFocus] = {
    for {wc <- WeaponClass.values} yield SuperiorWeaponFocus(wc)
  }


  case object WeaponSpecialization extends Feat with WeaponSpecializationBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = weaponSpecializationAny
  }

  case class WeaponSpecialization(weaponClass: WeaponClass) extends Feat with WeaponSpecializationBase with RequiresAllOfFeat with SubFeat with Prefix with FriendlyDisplay {
    override def prefix: Option[String] = Some("Weapon Specialization".splitByCase)

    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText

    override def allOfFeats: Seq[Feat] = weaponFocusAny.filter { x => x.weaponClass.eq(weaponClass) }
  }

  def weaponSpecializationAny: Seq[WeaponSpecialization] = {
    for {wc <- WeaponClass.values} yield WeaponSpecialization(wc)
  }

  case object GreaterWeaponSpecialization extends Feat with GreaterWeaponSpecializationBase with RequiresAllOfFeat with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = greaterWeaponSpecializationAny

    override def allOfFeats: Seq[Feat] = List(Feat.WeaponSpecialization)
  }

  case class GreaterWeaponSpecialization(weaponClass: WeaponClass) extends Feat with GreaterWeaponSpecializationBase with RequiresAllOfFeat with SubFeat with Prefix with FriendlyDisplay {
    override def prefix: Option[String] = Some("GreaterWeaponSpecialization".splitByCase)

    override protected val prefixSeparator: String = ": "

    override def allOfFeats: Seq[Feat] = greaterWeaponFocusAny.filter { x => x.weaponClass.eq(weaponClass) } ++ weaponSpecializationAny.filter { x => x.weaponClass.eq(weaponClass) }

    override protected def nameSource: String = weaponClass.displayText
  }

  def greaterWeaponSpecializationAny: Seq[GreaterWeaponSpecialization] = {
    for {wc <- WeaponClass.values} yield GreaterWeaponSpecialization(wc)
  }


  //Tactical Passive Feats
  case object TacticalTraining extends Feat with TacticalTraining

  case object TacticalCombatant extends Feat with TacticalCombatant

  case object TacticalMastery extends Feat with TacticalMastery

  case object TacticalSupremacy extends Feat with TacticalSupremacy

  //Heavy armor passive feats
  case object HeavyArmorTraining extends Feat with HeavyArmorTraining

  case object HeavyArmorCombatant extends Feat with HeavyArmorCombatant

  case object HeavyArmorMaster extends Feat with HeavyArmorMastery

  case object HeavyArmorChampion extends Feat with HeavyArmorChampion

  // Ranged Combat passive feats
  case object BowStrength extends Feat with BowStrength

  case object BrutalThrow extends Feat with BrutalThrow

  case object PointBlankShot extends Feat with PointBlankShot

  case object PreciseShot extends Feat with PreciseShot

  case object QuickDraw extends Feat with QuickDraw

  case object RapidReload extends Feat with RapidReload

  case object RapidShot extends Feat with RapidShot

  case object ShotOnTheRun extends Feat with ShotOnTheRun

  case object ShurikenExpertise extends Feat with ShurikenExpertise

  case object ZenArchery extends Feat with ZenArchery

  // Turn Undead related
  // case object ExtraTurning extends Feat with ExtraTurning

  //  case object ImprovedTurning extends Feat with ImprovedTurning

  // Spellcasting related
  case object AugmentSummoning extends Feat with AugmentSummoning

  case object MentalToughness extends Feat with MentalToughness

  case object ImprovedMentalToughness extends Feat with ImprovedMentalToughness

  case object MobileSpellcasting extends Feat with MobileSpellcasting

  case object NaturalFighting extends Feat with NaturalFighting


  // @todo add [Superior|Greater] spell focus X here
  case object SpellFocus extends Feat with SpellFocusBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = spellFocusAny
  }

  case class SpellFocus(school: School) extends Feat with SpellFocusBase with SubFeat with Prefix {
    override protected def nameSource: String = school.displayText

    /** * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("SpellFocus".splitByCase)
  }

  def spellFocusAny: Seq[SpellFocus] = for {x <- School.values} yield SpellFocus(x)

  case object GreaterSpellFocus extends Feat with GreaterSpellFocusBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = greaterWeaponFocusAny
  }

  case class GreaterSpellFocus(school: School) extends Feat with GreaterSpellFocusBase with SubFeat with Prefix {
    override protected def nameSource: String = school.displayText

    /** Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("GreaterSpellFocus".splitByCase)

    def lesser = spellFocusAny.filter { x => x.school.eq(school) }

    override def allOfFeats: Seq[Feat] = lesser
  }

  def greaterSpellFocusAny: Seq[GreaterSpellFocus] = for {x <- School.values} yield GreaterSpellFocus(x)


  case object SpellPenetration extends Feat with SpellPenetration with PostText {
    override def postText: Option[String] = Some("feat")
  }

  case object GreaterSpellPenetration extends Feat with GreaterSpellPenetration

  //Armor, Shield & Weapon proficiencies
  case object LightArmorProficiency extends Feat with LightArmorProficiency

  case object MediumArmorProficiency extends Feat with MediumArmorProficiency

  case object HeavyArmorProficiency extends Feat with HeavyArmorProficiency

  case object ShieldProficiency extends Feat with ShieldProficiency with PostText {
    override def postText: Option[String] = Some("General")
  }

  case object TowerShieldProficiency extends Feat with TowerShieldProficiency

  /**
    * The feat provides proficiency with basic weapons
    *
    * @todo grants
    * @param weapon The Simple Weapon Category (i.e. Short Sword, Dagger, Light Crossbow)
    * @note
    * Every class except Wizards, Monks, and Druids get this feat for free at level 1.
    * Wizards are only proficient in a subset of Simple weapons: (Club, Dagger, Quarterstaff, Heavy Crossbow, Light Crossbow, and Throwing Dagger).
    * Monks are proficient with these simple weapons: Club, Dagger, Quarterstaff, Heavy Crossbow, Light Crossbow and of course Handwraps (Unarmed)
    * Monks are also proficient with a few non-simple weapons: The Handaxe (Martial), The Kama (Exotic) and the Shuriken (Exotic)
    * Druids are proficient with these simple weapons: Club, Dagger, Dart, Quarterstaff, Sickle, and Unarmed.
    * Druids are also proficient with the Martial class Scimitar.
    */
  case class SimpleWeaponProficiency(weapon: WeaponCategory with SimpleWeapon*) extends Feat with SimpleWeaponProficiencyBase with Prefix with SubFeat {
    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Simple Weapon Proficiency")

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  case object SimpleWeaponProficiency extends Feat with SimpleWeaponProficiencyBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = simpleWeaponProficiencies
  }

  def simpleWeaponProficiencies: Seq[SimpleWeaponProficiency] = for {wc <- simpleWeapons} yield SimpleWeaponProficiency(wc)

  def simpleWeapons: Seq[WeaponCategory with SimpleWeapon] = {
    for {w <- WeaponCategory.values.filter { x =>
      x match {
        case _: SimpleWeapon => true
        case _ => false
      }
    }
    } yield w.asInstanceOf[WeaponCategory with SimpleWeapon]
  }

  case class MartialWeaponProficiency(weapon: WeaponCategory with MartialWeapon*) extends Feat with MartialWeaponProficiencyBase with Prefix with SubFeat {
    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Martial Weapon Proficiency")

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  def martialWeaponProficiencies: Seq[MartialWeaponProficiency] = for {wc <- martialWeapons} yield MartialWeaponProficiency(wc)

  def martialWeapons: Seq[WeaponCategory with MartialWeapon] = {
    for {w <- WeaponCategory.values.filter { x =>
      x match {
        case _: MartialWeapon => true
        case _ => false
      }
    }
    } yield w.asInstanceOf[WeaponCategory with MartialWeapon]
  }

  case object MartialWeaponProficiency extends Feat with MartialWeaponProficiencyBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = martialWeaponProficiencies
  }

  case class ExoticWeaponProficiency(weapon: WeaponCategory with ExoticWeapon*) extends Feat with ExoticWeaponProficiencyBase with Prefix with SubFeat {
    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Exotic Weapon Proficiency")

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  def exoticWeaponProficiencies: Seq[ExoticWeaponProficiency] = for {wc <- exoticWeapons} yield ExoticWeaponProficiency(wc)

  def exoticWeapons: Seq[WeaponCategory with ExoticWeapon] = {
    for {w <- WeaponCategory.values.filter { x =>
      x match {
        case _: ExoticWeapon => true
        case _ => false
      }
    }
    } yield w.asInstanceOf[WeaponCategory with ExoticWeapon]
  }

  case object ExoticWeaponProficiency extends Feat with ExoticWeaponProficiencyBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = exoticWeaponProficiencies

    override def prefix: Option[String] = None
  }


  // Skill bonus feats

  case object Acrobatic extends Feat with Acrobatic

  case object Alertness extends Feat with Alertness

  case object Athletic extends Feat with Athletic

  case object CombatCasting extends Feat with CombatCasting

  case object Negotiator extends Feat with Negotiator

  case object NimbleFingers extends Feat with NimbleFingers

  case object SelfSufficient extends Feat with SelfSufficient

  /**
    * @todo this needs to be a case class for each (allowed) Skill
    */
  case object SkillFocus extends Feat with SkillFocusBase with ParentFeat {
    override val subFeats: Seq[Feat with SubFeat] = skillFocusAny
  }

  case class SkillFocus(skill: Skill) extends Feat with SkillFocusBase with SubFeat with Prefix {
    override def prefix: Option[String] = Some("Skill Focus")

    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = skill.displayText
  }

  def skillFocusAny: Seq[SkillFocus] = for {x <- Skill.values} yield SkillFocus(x)

  case object Stealthy extends Feat with Stealthy

  // Skill and Save bonus feats

  case object Bullheaded extends Feat with Bullheaded

  case object Discipline extends Feat with Discipline

  // Save bonus feats
  case object GreatFortitude extends Feat with GreatFortitude

  case object IronWill extends Feat with IronWill

  case object LightningReflexes extends Feat with LightningReflexes

  case object LuckOfHeroes extends Feat with LuckOfHeroes

  case object ResistPoison extends Feat with ResistPoison

  case object SnakeBlood extends Feat with SnakeBlood

  // Save related feats
  case object ForceOfPersonality extends Feat with ForceOfPersonality

  case object InsightfulReflexes extends Feat with InsightfulReflexes

  // FavorPatron awarded free feats
  case object CoinLordFinishingSchoolTraining extends Feat with CoinLordFinishingSchoolTraining

  case object DraconicVitality extends Feat with DraconicVitality

  // Exchange feats
  // case object FeatRespecToken extends Feat with FeatRespecToken

  // Racial Feats
  case object HalfElfDilettanteMonk extends Feat with HalfElfDilettanteMonk {
    override protected def nameSource: String = "Monk".toPascalCase
  }

  case object HalfElfDilettanteBarbarian extends Feat with HalfElfDilettanteBarbarian {
    override protected def nameSource: String = "Barbarian".toPascalCase
  }

  case object HalfElfDilettanteBard extends Feat with HalfElfDilettanteBard {
    override protected def nameSource: String = "Bard".toPascalCase
  }

  case object HalfElfDilettanteCleric extends Feat with HalfElfDilettanteCleric {
    override protected def nameSource: String = "Cleric".toPascalCase
  }

  case object HalfElfDilettanteDruid extends Feat with HalfElfDilettanteDruid {
    override protected def nameSource: String = "Druid".toPascalCase
  }

  case object HalfElfDilettanteFavoredSoul extends Feat with HalfElfDilettanteFavoredSoul {
    override protected def nameSource: String = "FavoredSoul".splitByCase.toPascalCase
  }

  case object HalfElfDilettanteFighter extends Feat with HalfElfDilettanteFighter {
    override protected def nameSource: String = "Fighter".toPascalCase
  }


  case object HalfElfDilettantePaladin extends Feat with HalfElfDilettantePaladin {
    override protected def nameSource: String = "Paladin".toPascalCase
  }

  case object HalfElfDilettanteRanger extends Feat with HalfElfDilettanteRanger {
    override protected def nameSource: String = "Ranger".toPascalCase
  }

  case object HalfElfDilettanteRogue extends Feat with HalfElfDilettanteRogue {
    override protected def nameSource: String = "Rogue".toPascalCase
  }

  case object HalfElfDilettanteSorcerer extends Feat with HalfElfDilettanteSorcerer {
    override protected def nameSource: String = "Sorcerer".toPascalCase
  }

  case object HalfElfDilettanteWarlock extends Feat with HalfElfDilettanteWarlock {
    override protected def nameSource: String = "Warlock".toPascalCase
  }

  case object HalfElfDilettanteWizard extends Feat with HalfElfDilettanteWizard {
    override protected def nameSource: String = "Wizard".toPascalCase
  }

  override lazy val values: Seq[Feat] =
    findValues ++
      simpleWeaponProficiencies ++
      martialWeaponProficiencies ++
      exoticWeaponProficiencies ++
      weaponFocusAny ++
      greaterWeaponFocusAny ++
      superiorWeaponFocusAny ++
      spellFocusAny ++
      greaterSpellFocusAny ++
      weaponSpecializationAny ++
      greaterWeaponSpecializationAny ++
      skillFocusAny

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "Feat"
}
















