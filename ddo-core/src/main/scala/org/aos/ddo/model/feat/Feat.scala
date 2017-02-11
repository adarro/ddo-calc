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
import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.item.weapon.{SimpleWeapon, WeaponCategory, WeaponClass}
import org.aos.ddo.support.SearchPrefix
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Feats Feats]] are special abilities that give your character a new capability, or improves one he or she already has.
  */
sealed trait Feat extends EnumEntry {
  self: FeatType with Requisite with Inclusion =>
}

//noinspection ScalaStyle
object Feat extends Enum[Feat] with SearchPrefix {

  /**
    * Convenience function to group all Weapon Focus Feats
    *
    * @return
    */
  def WeaponFocusAny = Seq(Feat.WeaponFocusBludgeon, Feat.WeaponFocusPiercing, Feat.WeaponFocusSlashing, Feat.WeaponFocusRanged, Feat.WeaponFocusThrown)

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

  case object ManyShot extends Feat with ManyShot

  case object TenThousandStars extends Feat with TenThousandStars

  case object PowerAttack extends Feat with PowerAttack

  case object ImprovedPreciseShot extends Feat with ImprovedPreciseShot

  case object Precision extends Feat with Precision

  case object Resilience extends Feat with Resilience

  // Passive Feats
  // General passive feats
  case object DieHard extends Feat with DieHard

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
  case object WeaponFocusBludgeon extends WeaponClass.Bludgeon with Feat with WeaponFocus

  case object WeaponFocusSlashing extends WeaponClass.Slashing with Feat with WeaponFocus

  case object WeaponFocusPiercing extends WeaponClass.Piercing with Feat with WeaponFocus

  case object WeaponFocusRanged extends WeaponClass.Ranged with Feat with WeaponFocus

  case object WeaponFocusThrown extends WeaponClass.Thrown with Feat with WeaponFocus

  case object GreaterWeaponFocusBludgeon extends WeaponClass.Bludgeon with Feat with GreaterWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(WeaponFocusBludgeon)
  }

  case object GreaterWeaponFocusPiercing extends WeaponClass.Piercing with Feat with GreaterWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(WeaponFocusPiercing)
  }

  case object GreaterWeaponFocusSlashing extends WeaponClass.Slashing with Feat with GreaterWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(WeaponFocusSlashing)
  }

  case object GreaterWeaponFocusRanged extends WeaponClass.Ranged with Feat with GreaterWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(WeaponFocusRanged)
  }

  case object GreaterWeaponFocusThrown extends WeaponClass.Thrown with Feat with GreaterWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(WeaponFocusThrown)
  }

  case object SuperiorWeaponFocusBludgeon extends WeaponClass.Bludgeon with Feat with SuperiorWeaponFocus with RequiresAllOfFeat {
    override lazy val allOfFeats: Seq[Feat] = List(GreaterWeaponFocusBludgeon)
  }

  case object SuperiorWeaponFocusPiercing extends WeaponClass.Piercing with Feat with SuperiorWeaponFocus with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(GreaterWeaponFocusPiercing)
  }

  case object SuperiorWeaponFocusSlashing extends WeaponClass.Slashing with Feat with SuperiorWeaponFocus with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(GreaterWeaponFocusSlashing)
  }

  case object SuperiorWeaponFocusRanged extends WeaponClass.Ranged with Feat with SuperiorWeaponFocus with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(GreaterWeaponFocusRanged)
  }

  case object SuperiorWeaponFocusThrown extends WeaponClass.Thrown with Feat with SuperiorWeaponFocus with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(GreaterWeaponFocusThrown)
  }

  //  Weapon Specialization Passive Feats
  case object WeaponSpecializationBludgeon extends WeaponClass.Bludgeon with Feat with WeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusBludgeon)
  }

  case object WeaponSpecializationPiercing extends WeaponClass.Piercing with Feat with WeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusPiercing)
  }

  case object WeaponSpecializationSlashing extends WeaponClass.Slashing with Feat with WeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusSlashing)
  }

  case object WeaponSpecializationRanged extends WeaponClass.Ranged with Feat with WeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusRanged)
  }

  case object WeaponSpecializationThrown extends WeaponClass.Thrown with Feat with WeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusThrown)
  }

  case object GreaterWeaponSpecializationBludgeon extends WeaponClass.Bludgeon with Feat with GreaterWeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusBludgeon, Feat.WeaponSpecializationBludgeon)
  }

  case object GreaterWeaponSpecializationPiercing extends WeaponClass.Piercing with Feat with GreaterWeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusPiercing, Feat.WeaponSpecializationPiercing)
  }

  case object GreaterWeaponSpecializationSlashing extends WeaponClass.Slashing with Feat with GreaterWeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusSlashing, Feat.WeaponSpecializationSlashing)
  }

  case object GreaterWeaponSpecializationRanged extends WeaponClass.Ranged with Feat with GreaterWeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusRanged, Feat.WeaponSpecializationRanged)
  }

  case object GreaterWeaponSpecializationThrown extends WeaponClass.Thrown with Feat with GreaterWeaponSpecialization with RequiresAllOfFeat {
    override def allOfFeats: Seq[Feat] = List(Feat.WeaponFocusThrown, Feat.WeaponSpecializationThrown)
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
  case object SpellPenetration extends Feat with SpellPenetration

  case object GreaterSpellPenetration extends Feat with GreaterSpellPenetration


  //Armor, Shield & Weapon proficiencies
  case object LightArmorProficiency extends Feat with LightArmorProficiency

  case object MediumArmorProficiency extends Feat with MediumArmorProficiency

  case object HeavyArmorProficiency extends Feat with HeavyArmorProficiency

  case object ShieldProficiency extends Feat with ShieldProficiency

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
  case class SimpleWeaponProficiency(weapon: WeaponCategory with SimpleWeapon*) extends Feat with SimpleWeaponProficiencyBase

  case object MartialWeaponProficiency extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyBattleaxe extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyFalchion extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyGreataxe extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyGreatclub extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyGreatsword extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyHandaxe extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyHeavyPick extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyKukri extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyLightHammer extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyLightPick extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyLongbow extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyLongsword extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyMaul extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyRapier extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyScimitar extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyShortSword extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyShortbow extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyCompositeShortbow extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyThrowingAxe extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyThrowingHammer extends Feat with MartialWeaponProficiency

  case object MartialWeaponProficiencyWarhammer extends Feat with MartialWeaponProficiency

  case object ExoticWeaponProficiencyBastardSword extends Feat with ExoticWeaponProficiency with RequiresAttribute {
    override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 13))
  }

  /**
    * @todo This is free for Dwarves, need to add Grant trait for AllOff[Dwarf with MartialWeaponProf]
    */
  case object ExoticWeaponProficiencyDwarvenAxe extends Feat with ExoticWeaponProficiency with RequiresAttribute {
    override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 13))
  }

  /**
    * @todo Need to add Free for Artificers and Rogue Mechanic:Arbalester
    */
  case object ExoticWeaponProficiencyGreatCrossBow extends Feat with ExoticWeaponProficiency

  /**
    * @todo Free for Monks
    */
  case object ExoticWeaponProficiencyHandWraps extends Feat with ExoticWeaponProficiency

  /**
    * @todo Free for Monks
    */
  case object ExoticWeaponProficiencyKama extends Feat with ExoticWeaponProficiency

  case object ExoticWeaponProficiencyKhopesh extends Feat with ExoticWeaponProficiency

  /*
  @todo free for Artificers and Rogue Mechanic: Improved Detection
   */
  case object ExoticWeaponProficiencyRepeatingHeavyCrossbow extends Feat with ExoticWeaponProficiency

  /*
  (free for Artificers and Rogue Mechanic: Targeting Sights)
   */
  case object ExoticWeaponProficiencyRepeatingLightCrossbow extends Feat with ExoticWeaponProficiency

  /*
  @todo (free for Monks and Drow)
   */
  case object ExoticWeaponProficiencyShuriken extends Feat with ExoticWeaponProficiency

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
  case object SkillFocus extends Feat with SkillFocus

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

  // Favor awarded free feats
  // case object CoinLordFinishingSchoolTraining extends Feat with CoinLordFinishingSchoolTraining

  //  case object DraconicVitality extends Feat with DraconicVitality

  // Exchange feats
  // case object FeatRespecToken extends Feat with FeatRespecToken

  // Racial Feats
  case object HalfElfDilettanteMonk extends Feat with HalfElfDilettanteMonk

  case object HalfElfDilettanteBarbarian extends Feat with HalfElfDilettanteBarbarian

  case object HalfElfDilettanteBard extends Feat with HalfElfDilettanteBard

  case object HalfElfDilettanteCleric extends Feat with HalfElfDilettanteCleric

  case object HalfElfDilettanteDruid extends Feat with HalfElfDilettanteDruid

  case object HalfElfDilettanteFavoredSoul extends Feat with HalfElfDilettanteFavoredSoul

  case object HalfElfDilettanteFighter extends Feat with HalfElfDilettanteFighter

  case object HalfElfDilettantePaladin extends Feat with HalfElfDilettantePaladin

  case object HalfElfDilettanteRanger extends Feat with HalfElfDilettanteRanger

  case object HalfElfDilettanteRogue extends Feat with HalfElfDilettanteRogue

  case object HalfElfDilettanteSorcerer extends Feat with HalfElfDilettanteSorcerer

  case object HalfElfDilettanteWarlock extends Feat with HalfElfDilettanteWarlock

  case object HalfElfDilettanteWizard extends Feat with HalfElfDilettanteWizard

  override lazy val values: Seq[Feat] = findValues

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "Feat"
}
















