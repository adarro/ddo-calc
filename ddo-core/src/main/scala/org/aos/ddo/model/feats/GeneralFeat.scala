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
package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.item.weapon._
import org.aos.ddo.model.race.Race
import org.aos.ddo.model.schools.School
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.{FriendlyDisplay, PostText, Prefix}
import org.aos.ddo.support.requisite.{GrantsToClass, Inclusion, RequiresAllOfFeat, Requisite}

/**
  * [[http://ddowiki.com/page/Feats Feats]] are special abilities that give your character a new capability, or improves one he or she already has.
  */
sealed trait GeneralFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation
    with FeatMatcher { self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, GeneralFeat] = {
    case x: GeneralFeat => x
  }
  val matchFeatById: PartialFunction[String, GeneralFeat] = {
    case x: String if GeneralFeat.namesToValuesMap.contains(x) =>
      GeneralFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

/**
  * General Feats aren't specific to a particular race or class.
  */
//noinspection ScalaStyle
object GeneralFeat extends Enum[GeneralFeat] with FeatSearchPrefix {

  case object Attack extends GeneralFeat with Attack

  case object DefensiveFighting extends GeneralFeat with DefensiveFighting

  case object DismissCharm extends GeneralFeat with DismissCharm

  case object Sneak extends GeneralFeat with Sneak

  case object Sunder extends GeneralFeat with Sunder

  case object Trip extends GeneralFeat with Trip

  case object HeroicDurability extends GeneralFeat with HeroicDurability

  case object ImprovedHeroicDurability
      extends GeneralFeat
      with ImprovedHeroicDurability

  case object MagicalTraining extends GeneralFeat with MagicalTraining

  case object Cleave extends GeneralFeat with Cleave

  case object GreatCleave extends GeneralFeat with GreatCleave

  case object Hamstring extends GeneralFeat with Hamstring

  case object ImprovedFeint extends GeneralFeat with ImprovedFeint

  case object ImprovedSunder extends GeneralFeat with ImprovedSunder

  case object ImprovedTrip extends GeneralFeat with ImprovedTrip

  case object Sap extends GeneralFeat with Sap

  case object SlicingBlow extends GeneralFeat with SlicingBlow

  case object StunningBlow extends GeneralFeat with StunningBlow

  case object WhirlwindAttack extends GeneralFeat with WhirlwindAttack

  // Combat Stances
  case object CombatExpertise extends GeneralFeat with CombatExpertise

  case object Manyshot extends GeneralFeat with Manyshot

  case object TenThousandStars extends GeneralFeat with TenThousandStars

  case object PowerAttack extends GeneralFeat with PowerAttack

  case object ImprovedPreciseShot extends GeneralFeat with ImprovedPreciseShot

  case object Precision extends GeneralFeat with Precision

  case object Resilience extends GeneralFeat with Resilience

  // Passive Feats
  // General passive feats
  case object Diehard extends GeneralFeat with Diehard

  case object Dodge extends GeneralFeat with Dodge

  case object Mobility extends GeneralFeat with Mobility

  case object SpringAttack extends GeneralFeat with SpringAttack

  case object ImprovedCritical extends GeneralFeat with ImprovedCritical

  case object PowerCritical extends GeneralFeat with PowerCritical

  case object Toughness extends GeneralFeat with Toughness

  case object WeaponFinesse extends GeneralFeat with WeaponFinesse

  case object ShieldMastery extends GeneralFeat with ShieldMastery

  case object ImprovedShieldMastery
      extends GeneralFeat
      with ImprovedShieldMastery

  case object ImprovedShieldBash extends GeneralFeat with ImprovedShieldBash

  case object ShieldDeflection extends GeneralFeat with ShieldDeflection

  // Single Weapon Fighting Passive Feats

  case object SingleWeaponFighting
      extends GeneralFeat
      with SingleWeaponFighting

  case object ImprovedSingleWeaponFighting
      extends GeneralFeat
      with ImprovedSingleWeaponFighting

  case object GreaterSingleWeaponFighting
      extends GeneralFeat
      with GreaterSingleWeaponFighting

  case object TwoHandedFighting extends GeneralFeat with TwoHandedFighting

  case object ImprovedTwoHandedFighting
      extends GeneralFeat
      with ImprovedTwoHandedFighting

  case object GreaterTwoHandedFighting
      extends GeneralFeat
      with GreaterTwoHandedFighting

  // Two Weapon Passive Feats
  case object TwoWeaponFighting extends GeneralFeat with TwoWeaponFighting

  case object ImprovedTwoWeaponFighting
      extends GeneralFeat
      with ImprovedTwoWeaponFighting

  case object GreaterTwoWeaponFighting
      extends GeneralFeat
      with GreaterTwoWeaponFighting

  case object OversizedTwoWeaponFighting
      extends GeneralFeat
      with OversizedTwoWeaponFighting

  case object TwoWeaponDefense extends GeneralFeat with TwoWeaponDefense

  case object TwoWeaponBlocking extends GeneralFeat with TwoWeaponBlocking

  // Weapon Focus Passive Feats
  case object WeaponFocus
      extends GeneralFeat
      with WeaponFocusBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] = weaponFocusAny
  }

  /**
    * Convenience function to group all Weapon Focus Feats
    *
    * @return
    */
  def weaponFocusAny: Seq[WeaponFocus] = {
    for { wc <- WeaponClass.values } yield WeaponFocus(wc)
  }

  // Seq(Feat.WeaponFocusBludgeon, Feat.WeaponFocusPiercing, Feat.WeaponFocusSlashing, Feat.WeaponFocusRanged, Feat.WeaponFocusThrown)

  case class WeaponFocus(weaponClass: WeaponClass)
      extends GeneralFeat
      with WeaponFocusBase
      with SubFeat
      with Prefix
      with FriendlyDisplay {
    override def prefix: Option[String] = Some("Weapon Focus")

    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText
  }

  case object GreaterWeaponFocus
      extends GeneralFeat
      with GreaterWeaponFocusBase
      with ParentFeat
      with RequiresAllOfFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      greaterWeaponFocusAny

    override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.WeaponFocus)
  }

  case class GreaterWeaponFocus(weaponClass: WeaponClass)
      extends GeneralFeat
      with GreaterWeaponFocusBase
      with RequiresAllOfFeat
      with SubFeat
      with Prefix {
    override def prefix: Option[String] =
      Some("GreaterWeaponFocus".splitByCase)

    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText

    override def allOfFeats: Seq[GeneralFeat] = weaponFocusAny.filter { x =>
      x.weaponClass.eq(weaponClass)
    }
  }

  def greaterWeaponFocusAny: Seq[GreaterWeaponFocus] = {
    for { wc <- WeaponClass.values } yield GreaterWeaponFocus(wc)
  }

  case object SuperiorWeaponFocus
      extends GeneralFeat
      with SuperiorWeaponFocusBase
      with ParentFeat
      with RequiresAllOfFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      superiorWeaponFocusAny

    override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.WeaponFocus)
  }

  case class SuperiorWeaponFocus(weaponClass: WeaponClass)
      extends GeneralFeat
      with SuperiorWeaponFocusBase
      with RequiresAllOfFeat
      with SubFeat
      with Prefix
      with FriendlyDisplay {
    override def prefix: Option[String] =
      Some("SuperiorWeaponFocus".splitByCase)

    override protected def nameSource: String = weaponClass.displayText

    override protected val prefixSeparator: String = ": "

    override def allOfFeats: Seq[GeneralFeat] = greaterWeaponFocusAny.filter {
      x =>
        x.weaponClass.eq(weaponClass)
    }
  }

  def superiorWeaponFocusAny: Seq[SuperiorWeaponFocus] = {
    for { wc <- WeaponClass.values } yield SuperiorWeaponFocus(wc)
  }

  case object WeaponSpecialization
      extends GeneralFeat
      with WeaponSpecializationBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      weaponSpecializationAny
  }

  case class WeaponSpecialization(weaponClass: WeaponClass)
      extends GeneralFeat
      with WeaponSpecializationBase
      with RequiresAllOfFeat
      with SubFeat
      with Prefix
      with FriendlyDisplay {
    override def prefix: Option[String] =
      Some("Weapon Specialization".splitByCase)

    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = weaponClass.displayText

    override def allOfFeats: Seq[GeneralFeat] = weaponFocusAny.filter { x =>
      x.weaponClass.eq(weaponClass)
    }
  }

  def weaponSpecializationAny: Seq[WeaponSpecialization] = {
    for { wc <- WeaponClass.values } yield WeaponSpecialization(wc)
  }

  case object GreaterWeaponSpecialization
      extends GeneralFeat
      with GreaterWeaponSpecializationBase
      with RequiresAllOfFeat
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      greaterWeaponSpecializationAny

    override def allOfFeats: Seq[GeneralFeat] =
      List(GeneralFeat.WeaponSpecialization)
  }

  case class GreaterWeaponSpecialization(weaponClass: WeaponClass)
      extends GeneralFeat
      with GreaterWeaponSpecializationBase
      with RequiresAllOfFeat
      with SubFeat
      with Prefix
      with FriendlyDisplay {
    override def prefix: Option[String] =
      Some("GreaterWeaponSpecialization".splitByCase)

    override protected val prefixSeparator: String = ": "

    override def allOfFeats: Seq[GeneralFeat] =
      greaterWeaponFocusAny.filter { x =>
        x.weaponClass.eq(weaponClass)
      } ++ weaponSpecializationAny.filter { x =>
        x.weaponClass.eq(weaponClass)
      }

    override protected def nameSource: String = weaponClass.displayText
  }

  def greaterWeaponSpecializationAny: Seq[GreaterWeaponSpecialization] = {
    for { wc <- WeaponClass.values } yield GreaterWeaponSpecialization(wc)
  }

  //Tactical Passive Feats
  case object TacticalTraining extends GeneralFeat with TacticalTraining

  case object TacticalCombatant extends GeneralFeat with TacticalCombatant

  case object TacticalMastery extends GeneralFeat with TacticalMastery

  case object TacticalSupremacy extends GeneralFeat with TacticalSupremacy

  //Heavy armor passive feats
  case object HeavyArmorTraining extends GeneralFeat with HeavyArmorTraining

  case object HeavyArmorCombatant extends GeneralFeat with HeavyArmorCombatant

  case object HeavyArmorMaster extends GeneralFeat with HeavyArmorMastery

  case object HeavyArmorChampion extends GeneralFeat with HeavyArmorChampion

  // Ranged Combat passive feats
  case object BowStrength extends GeneralFeat with BowStrength

  case object BrutalThrow extends GeneralFeat with BrutalThrow

  case object PointBlankShot extends GeneralFeat with PointBlankShot

  case object PreciseShot extends GeneralFeat with PreciseShot

  case object QuickDraw extends GeneralFeat with QuickDraw

  case object RapidShot extends GeneralFeat with RapidShot

  case object ShotOnTheRun extends GeneralFeat with ShotOnTheRun

  case object ShurikenExpertise extends GeneralFeat with ShurikenExpertise

  case object ZenArchery extends GeneralFeat with ZenArchery

  // Turn Undead related
  // case object ExtraTurning extends Feat with ExtraTurning

  //  case object ImprovedTurning extends Feat with ImprovedTurning

  // Spellcasting related
  case object AugmentSummoning extends GeneralFeat with AugmentSummoning

  case object MentalToughness extends GeneralFeat with MentalToughness

  case object ImprovedMentalToughness
      extends GeneralFeat
      with ImprovedMentalToughness

  case object MobileSpellcasting extends GeneralFeat with MobileSpellcasting

  case object NaturalFighting extends GeneralFeat with NaturalFighting

  case object SpellFocus
      extends GeneralFeat
      with SpellFocusBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] = spellFocusAny
  }

  case class SpellFocus(school: School)
      extends GeneralFeat
      with SpellFocusBase
      with SubFeat
      with Prefix {
    override protected def nameSource: String = school.displayText

    /** * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("SpellFocus".splitByCase)
  }

  def spellFocusAny: Seq[SpellFocus] =
    for { x <- School.values } yield SpellFocus(x)

  case object GreaterSpellFocus
      extends GeneralFeat
      with GreaterSpellFocusBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      greaterWeaponFocusAny
  }

  case class GreaterSpellFocus(school: School)
      extends GeneralFeat
      with GreaterSpellFocusBase
      with SubFeat
      with Prefix {
    override protected def nameSource: String = school.displayText

    /** Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("GreaterSpellFocus".splitByCase)

    private def lesser = spellFocusAny.filter { x =>
      x.school.eq(school)
    }

    override def allOfFeats: Seq[GeneralFeat] = lesser
  }

  def greaterSpellFocusAny: Seq[GreaterSpellFocus] =
    for { x <- School.values } yield GreaterSpellFocus(x)

  case object SpellPenetration
      extends GeneralFeat
      with SpellPenetration
      with PostText {
    override def postText: Option[String] = Some("feat")
  }

  case object GreaterSpellPenetration
      extends GeneralFeat
      with GreaterSpellPenetration

  //Armor, Shield & Weapon proficiencies
  case object LightArmorProficiency
      extends GeneralFeat
      with LightArmorProficiency

  case object MediumArmorProficiency
      extends GeneralFeat
      with MediumArmorProficiency

  case object HeavyArmorProficiency
      extends GeneralFeat
      with HeavyArmorProficiency

  case object ShieldProficiency
      extends GeneralFeat
      with ShieldProficiency
      with PostText {
    override def postText: Option[String] = Some("General")
  }

  case object TowerShieldProficiency
      extends GeneralFeat
      with TowerShieldProficiency

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
  case class SimpleWeaponProficiency(weapon: WeaponCategory with SimpleWeapon*)
      extends GeneralFeat
      with SimpleWeaponProficiencyBase
      with Prefix
      with SubFeat {

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

  case object SimpleWeaponProficiency
      extends GeneralFeat
      with SimpleWeaponProficiencyBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      simpleWeaponProficiencies
  }

  def simpleWeaponProficiencies: Seq[SimpleWeaponProficiency] =
    for { wc <- simpleWeapons } yield SimpleWeaponProficiency(wc)

  def simpleWeapons: Seq[WeaponCategory with SimpleWeapon] = {
    for {
      w <- WeaponCategory.values.filter { x =>
        x match {
          case _: SimpleWeapon => true
          case _ => false
        }
      }
    } yield w.asInstanceOf[WeaponCategory with SimpleWeapon]
  }

  case class MartialWeaponProficiency(
      override val grantsToRace: Seq[(Race, Int)],override val grantToClass: Seq[(CharacterClass, Int)],
      weapon: WeaponCategory with MartialWeapon*)
      extends GeneralFeat
      with MartialWeaponProficiencyBase
      with Prefix
      with SubFeat {

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

  def martialWeaponProficiencies: Seq[MartialWeaponProficiency] = {
    for {
      wc <- martialWeapons
      rl <- racialMartialWeaponGrants(wc)
      cl <- classMartialWeaponGrants(wc)
    } yield MartialWeaponProficiency(rl,cl, wc)
  }

  def classMartialWeaponGrants( wc: WeaponCategory with MartialWeapon): Iterable[List[(CharacterClass, Int)]] = {
    val autoGrant = List(CharacterClass.Barbarian,CharacterClass.Fighter,CharacterClass.Paladin,CharacterClass.Ranger).map((_ ,1))

    val weaponGrants: Map[WeaponCategory with MartialWeapon, List[(CharacterClass, Int)]] = martialWeapons.map(_ -> autoGrant).toMap
    weaponGrants.filter { x =>
      x._1.eq(wc)
    }.values
  }

  def racialMartialWeaponGrants(
      wc: WeaponCategory with MartialWeapon): Iterable[List[(Race, Int)]] = {
    val weaponGrants: Map[WeaponCategory with MartialWeapon, List[(Race, Int)]] =
      Map(
        WeaponCategory.Longsword -> List((Race.Elf, 1)),
        WeaponCategory.Longbow -> List((Race.Elf, 1)),
        WeaponCategory.Shortbow -> List((Race.Elf, 1)),
        WeaponCategory.Rapier -> List((Race.Elf, 1), (Race.DrowElf, 1)),
        WeaponCategory.Shortsword -> List((Race.DrowElf, 1)),
        WeaponCategory.LightHammer -> List((Race.Gnome, 1)),
        WeaponCategory.ThrowingHammer -> List((Race.Gnome, 1)),
        WeaponCategory.Warhammer -> List((Race.Gnome, 1))
      )
    weaponGrants.filter { x =>
      x._1.eq(wc)
    }.values
  }

  def racialExoticWeaponGrants(
      wc: WeaponCategory with ExoticWeapon): Iterable[List[(Race, Int)]] = {
    val weaponGrants: Map[WeaponCategory with ExoticWeapon, List[(Race, Int)]] =
      Map(
        WeaponCategory.Shuriken -> List((Race.DrowElf, 1)),
        WeaponCategory.DwarvenWarAxe -> List((Race.Dwarf, 1))
      )
    weaponGrants.filter(_._1.eq(wc)).values
  }

  def martialWeapons: Seq[WeaponCategory with MartialWeapon] = {
    for {
      w <- WeaponCategory.values.filter { x =>
        x match {
          case _: MartialWeapon => true
          case _ => false
        }
      }
    } yield w.asInstanceOf[WeaponCategory with MartialWeapon]
  }

  case object MartialWeaponProficiency
      extends GeneralFeat
      with MartialWeaponProficiencyBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      martialWeaponProficiencies
  }

  case class ExoticWeaponProficiency(
      override val grantsToRace: Seq[(Race, Int)],
      weapon: WeaponCategory with ExoticWeapon*)
      extends GeneralFeat
      with ExoticWeaponProficiencyBase
      with Prefix
      with SubFeat {

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

  def exoticWeaponProficiencies: Seq[ExoticWeaponProficiency] =
    for {
      wc <- exoticWeapons
      rl <- racialExoticWeaponGrants(wc)
    } yield ExoticWeaponProficiency(rl, wc)

  def exoticWeapons: Seq[WeaponCategory with ExoticWeapon] = {
    for {
      w <- WeaponCategory.values.filter { x =>
        x match {
          case _: ExoticWeapon => true
          case _ => false
        }
      }
    } yield w.asInstanceOf[WeaponCategory with ExoticWeapon]
  }

  case object ExoticWeaponProficiency
      extends GeneralFeat
      with ExoticWeaponProficiencyBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] =
      exoticWeaponProficiencies

    override def prefix: Option[String] = None
  }

  // Skill bonus feats

  case object Acrobatic extends GeneralFeat with Acrobatic

  case object Alertness extends GeneralFeat with Alertness

  case object Athletic extends GeneralFeat with Athletic

  case object CombatCasting extends GeneralFeat with CombatCasting

  case object Negotiator extends GeneralFeat with Negotiator

  case object NimbleFingers extends GeneralFeat with NimbleFingers

  case object SelfSufficient extends GeneralFeat with SelfSufficient

  /**
    * @todo this needs to be a case class for each (allowed) Skill
    */
  case object SkillFocus
      extends GeneralFeat
      with SkillFocusBase
      with ParentFeat {
    override val subFeats: Seq[GeneralFeat with SubFeat] = skillFocusAny
  }

  case class SkillFocus(skill: Skill)
      extends GeneralFeat
      with SkillFocusBase
      with SubFeat
      with Prefix {
    override def prefix: Option[String] = Some("Skill Focus")

    /**
      * Delimits the prefix and text.
      */
    override protected val prefixSeparator: String = ": "

    override protected def nameSource: String = skill.displayText
  }

  def skillFocusAny: Seq[SkillFocus] =
    for {
      x <- Skill.values
    } yield SkillFocus(x)

  case object Stealthy extends GeneralFeat with Stealthy

  // Skill and Save bonus feats

  case object Bullheaded extends GeneralFeat with Bullheaded

  case object Discipline extends GeneralFeat with Discipline

  // Save bonus feats
  case object GreatFortitude extends GeneralFeat with GreatFortitude

  case object IronWill extends GeneralFeat with IronWill

  case object LightningReflexes extends GeneralFeat with LightningReflexes

  case object LuckOfHeroes extends GeneralFeat with LuckOfHeroes

  case object ResistPoison extends GeneralFeat with ResistPoison

  case object SnakeBlood extends GeneralFeat with SnakeBlood

  // Save related feats
  case object ForceOfPersonality extends GeneralFeat with ForceOfPersonality

  case object InsightfulReflexes extends GeneralFeat with InsightfulReflexes

  // FavorPatron awarded free feats
  case object CoinLordFinishingSchoolTraining
      extends GeneralFeat
      with CoinLordFinishingSchoolTraining

  case object DraconicVitality extends GeneralFeat with DraconicVitality

  // Exchange feats
  // case object FeatRespecToken extends Feat with FeatRespecToken

  override lazy val values: Seq[GeneralFeat] =
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
}
