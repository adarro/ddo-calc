/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GeneralFeat.scala
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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import enumeratum.Enum
import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Bard
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.{Feature, SourceInfo, TriggerEvent}
import io.truthencode.ddo.model.effect.features._
import io.truthencode.ddo.model.item.weapon.WeaponCategory._
import io.truthencode.ddo.model.item.weapon._
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.model.schools.School
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{FriendlyDisplay, PostText, Prefix}
import io.truthencode.ddo.support.requisite._

/**
 * [[http://ddowiki.com/page/Feats Feats]] are special abilities that give your character a new
 * capability, or improves one he or she already has.
 */
sealed trait GeneralFeat
  extends Feat with FriendlyDisplay with SubFeatInformation with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & Features =>

}

/**
 * General Feats aren't specific to a particular race or class.
 */
// scalastyle:off number.of.methods
object GeneralFeat
  extends Enum[GeneralFeat] with FeatSearchPrefix with FeatMatcher with LazyLogging {

  override lazy val values: IndexedSeq[GeneralFeat] =
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
      skillFocusAny ++
      improvedCriticalAny

  val matchFeat: PartialFunction[Feat, GeneralFeat] = { case x: GeneralFeat =>
    x
  }

  val matchFeatById: PartialFunction[String, GeneralFeat] = {
    case x: String if GeneralFeat.namesToValuesMap.contains(x) =>
      GeneralFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

  /**
   * Convenience function to group all weapon classes
   *
   * @return
   */
  def improvedCriticalAny: Seq[ImprovedCritical] = {
    for wc <- WeaponClass.values yield ImprovedCritical(wc)
  }

  /**
   * Convenience function to group all Weapon Focus Feats
   *
   * @return
   */
  def weaponFocusAny: Seq[WeaponFocus] = {
    for wc <- WeaponClass.values yield WeaponFocus(wc)
  }

  def greaterWeaponFocusAny: Seq[GreaterWeaponFocus] = {
    for wc <- WeaponClass.values yield GreaterWeaponFocus(wc)
  }

  def superiorWeaponFocusAny: Seq[SuperiorWeaponFocus] = {
    for wc <- WeaponClass.values yield SuperiorWeaponFocus(wc)
  }

  def weaponSpecializationAny: Seq[WeaponSpecialization] = {
    for wc <- WeaponClass.values yield WeaponSpecialization(wc)
  }

  def greaterWeaponSpecializationAny: Seq[GreaterWeaponSpecialization] = {
    for wc <- WeaponClass.values yield GreaterWeaponSpecialization(wc)
  }

  def spellFocusAny: Seq[SpellFocus] =
    for x <- School.values yield SpellFocus(x)

  def greaterSpellFocusAny: Seq[GreaterSpellFocus] =
    for x <- School.values yield GreaterSpellFocus(x)

  def simpleWeaponProficiencies: Seq[SimpleWeaponProficiency] =
    for
      wc <- WeaponCategory.simpleWeapons
      cl <- classSimpleWeaponGrants(wc)
    yield SimpleWeaponProficiency(cl, wc)

  def classSimpleWeaponGrants(
    wc: WeaponCategory & SimpleWeapon
  ): Iterable[List[(HeroicCharacterClass, Int)]] = {
    val autoGrant = List(
      HeroicCharacterClass.Barbarian,
      HeroicCharacterClass.Fighter,
      HeroicCharacterClass.Paladin,
      HeroicCharacterClass.Ranger,
      HeroicCharacterClass.Bard
    ).map((_, 1))

    val weaponGrants: Map[WeaponCategory & SimpleWeapon, List[(HeroicCharacterClass, Int)]] =
      WeaponCategory.simpleWeapons.map(_ -> autoGrant).toMap
    weaponGrants.filter(_._1.eq(wc)).values
  }

  def martialWeaponProficiencies: Seq[MartialWeaponProficiency] = {
    for
      wc <- WeaponCategory.martialWeapons
      rl <- racialMartialWeaponGrants(wc)
      cl <- classMartialWeaponGrants(wc)
    yield MartialWeaponProficiency(rl, cl, wc)
  }

  def classMartialWeaponGrants(
    wc: WeaponCategory & MartialWeapon
  ): Iterable[List[(HeroicCharacterClass, Int)]] = {
    val autoGrant = List(
      HeroicCharacterClass.Barbarian,
      HeroicCharacterClass.Fighter,
      HeroicCharacterClass.Paladin,
      HeroicCharacterClass.Ranger
    ).map((_, 1))
    val bardWeapons: Map[WeaponCategory & MartialWeapon, List[(HeroicCharacterClass, Int)]] =
      List(Longsword, Rapier, Shortsword, Shortbow)
        .map(_ -> List((Bard, 1)))
        .toMap
    val weaponGrants: Map[WeaponCategory & MartialWeapon, List[(HeroicCharacterClass, Int)]] =
      WeaponCategory.martialWeapons.map(_ -> autoGrant).toMap

    val unfiltered: Set[(WeaponCategory & MartialWeapon, List[(HeroicCharacterClass, Int)])] =
      for
        k <- weaponGrants.keySet
        x = for
          a <- weaponGrants.find(p => p._1 == k)
          b <- bardWeapons.find(p => p._1 == k)
        yield a._2 ++ b._2
        g <- weaponGrants.find(p => p._1.eq(k)).map(_._2)
      yield k -> x.getOrElse(g)

    unfiltered.filter(_._1.eq(wc)).toMap.values
    //  weaponGrants.filter(_._1.eq(wc)).values
  }

  def racialMartialWeaponGrants(
    wc: WeaponCategory & MartialWeapon
  ): Iterable[List[(Race, Int)]] = {
    val weaponGrants: Map[WeaponCategory & MartialWeapon, List[(Race, Int)]] =
      Map(
        WeaponCategory.Longsword -> List((Race.Elf, 1), (Race.Morninglord, 1)),
        WeaponCategory.Longbow -> List((Race.Elf, 1), (Race.Morninglord, 1)),
        WeaponCategory.Shortbow -> List((Race.Elf, 1), (Race.Morninglord, 1)),
        WeaponCategory.Rapier -> List((Race.Elf, 1), (Race.Morninglord, 1), (Race.DrowElf, 1)),
        WeaponCategory.Shortsword -> List((Race.DrowElf, 1)),
        WeaponCategory.LightHammer -> List((Race.Gnome, 1), (Race.DeepGnome, 1)),
        WeaponCategory.ThrowingHammer -> List((Race.Gnome, 1), (Race.DeepGnome, 1)),
        WeaponCategory.Warhammer -> List((Race.Gnome, 1), (Race.DeepGnome, 1))
      )
    weaponGrants.filter(_._1.eq(wc)).values
  }

  def exoticWeaponProficiencies: Seq[ExoticWeaponProficiency] =
    for
      wc <- WeaponCategory.exoticWeapons
      rl <- racialExoticWeaponGrants(wc)
    yield ExoticWeaponProficiency(rl, wc)

  def racialExoticWeaponGrants(
    wc: WeaponCategory & ExoticWeapon
  ): Iterable[List[(Race, Int)]] = {
    val weaponGrants: Map[WeaponCategory & ExoticWeapon, List[(Race, Int)]] =
      Map(
        WeaponCategory.Shuriken -> List((Race.DrowElf, 1)),
        WeaponCategory.DwarvenWarAxe -> List((Race.Dwarf, 1))
      )
    weaponGrants.filter(_._1.eq(wc)).values
  }

  def skillFocusAny: Seq[SkillFocus] =
    for x <- Skill.values
    yield SkillFocus(x)

  case class ImprovedCritical(weaponClass: WeaponClass)
    extends GeneralFeat with ImprovedCriticalBase with SubFeat with Prefix with FriendlyDisplay
    with FeaturesImpl with CriticalThreatRangeFeature {

    override protected lazy val effectDetail: DetailedEffect = DetailedEffect(
      id = "CriticalThreatRange",
      description = "Improves your critical threat range for a specific weapon",
      triggersOn = Seq(TriggerEvent.OnEquip.entryName),
      triggersOff = Seq(TriggerEvent.OnUnEquip.entryName),
      bonusType = criticalThreatRangeType.entryName
    )

    /**
     * @note
     *   Will need to look at stacking logic checks to make sure this stacks correctly (or more
     *   accurately won't) with Keen / Impact et al.
     */
    override protected lazy val criticalThreatRangeType: BonusType = BonusType.Feat
    val wow: Seq[(WeaponCategory, Int)] = improvedCriticalRangeByWeapon(weaponClass) // wow1.flatten
    override protected val criticalThreatRangeAmount: Seq[(WeaponCategory, Int)] = wow

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Improved Critical")

    override protected def nameSource: String = weaponClass.displayText

  }

  // Common features of all Weapon Focus feats
  private def weaponDamageFeature(
    source: SourceInfo,
    weaponClass: WeaponClass,
    toDamage: Int = 2) = {
    new FeaturesImpl with ToDamageByWeaponClassFeature with SourceInfo {
      override val sourceId: String = source.sourceId
      override val sourceRef: AnyRef = source.sourceRef
      override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
      override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)

      override protected lazy val toDamageType: BonusType = BonusType.Feat
      override protected val toDamageAmount: Seq[(WeaponCategory, Int)] =
        filterByWeaponClass(weaponClass).map((_, toDamage))
      override protected val toDmgWcCategories: Seq[effect.EffectCategories.Value] = Seq(
        effect.EffectCategories.GeneralCombat)
    }
  }

  private def weaponHitFeature(source: SourceInfo, weaponClass: WeaponClass, toHit: Int = 1) = {
    new FeaturesImpl with ToHitByWeaponClassFeature with SourceInfo {
      override val sourceId: String = source.sourceId
      override val sourceRef: AnyRef = source.sourceRef
      override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
      override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)

      override protected lazy val toHitType: BonusType = BonusType.Feat
      override protected val toHitAmount: Seq[(WeaponCategory, Int)] =
        filterByWeaponClass(weaponClass).map((_, toHit))
      override protected val toHitWcCategories: Seq[effect.EffectCategories.Value] = Seq(
        effect.EffectCategories.GeneralCombat)
    }

  }

  private def weaponPowerFeatures(source: SourceInfo, mp: Int = 2, rp: Int = 2) = {

    val mpFeature = new FeaturesImpl with MeleePowerFeature with SourceInfo {
      override val sourceId: String = source.sourceId
      override val sourceRef: AnyRef = source.sourceRef
      override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
      override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)

      override protected lazy val meleePowerCategories: Seq[effect.EffectCategories.Value] =
        Seq(effect.EffectCategories.GeneralCombat)
      override protected lazy val meleePowerBonusType: BonusType = BonusType.Feat
      override protected val meleePowerBonusAmount: Int = mp
    }

    val rpFeature = new FeaturesImpl with RangePowerFeature with SourceInfo {
      override val sourceId: String = source.sourceId
      override val sourceRef: AnyRef = source.sourceRef

      override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
      override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)

      override protected lazy val rangePowerBonusType: BonusType = BonusType.Feat
      override protected val rangePowerBonusAmount: Int = rp
      override protected val rangePowerCategories: Seq[effect.EffectCategories.Value] = Seq(
        effect.EffectCategories.GeneralCombat)
    }
    List(mpFeature, rpFeature)
  }

  case class WeaponFocus(weaponClass: WeaponClass)
    extends GeneralFeat with WeaponFocusBase with SubFeat with Prefix with FriendlyDisplay
    with Features {

    private val wpFeatures = weaponPowerFeatures(this).flatMap(_.features)
    private val whFeatures = weaponHitFeature(this, weaponClass).features
    override def features: Seq[Feature[?]] =
      super.features ++ wpFeatures ++ whFeatures

//      private[this] lazy val allCategories =
//      meleePowerCategories ++ toHitWcCategories ++ rangePowerCategories

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Weapon Focus")

    override protected def nameSource: String = weaponClass.displayText
  }

  case class GreaterWeaponFocus(weaponClass: WeaponClass)
    extends GeneralFeat with GreaterWeaponFocusBase with RequiresAllOfFeat with SubFeat with Prefix
    with FighterBonusFeat with FeaturesImpl {

    private val wpFeatures = weaponPowerFeatures(this).flatMap(_.features)
    private val whFeatures = weaponHitFeature(this, weaponClass).features
    override def features: Seq[Feature[?]] =
      super.features ++ wpFeatures ++ whFeatures

    override protected val prefixSeparator: String = ": "
    override def prefix: Option[String] =
      Some("GreaterWeaponFocus".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] = weaponFocusAny.filter { x =>
      x.weaponClass.eq(weaponClass)
    }

    override protected def nameSource: String = weaponClass.displayText
  }

  case class SuperiorWeaponFocus(weaponClass: WeaponClass)
    extends GeneralFeat with SuperiorWeaponFocusBase with RequiresAllOfFeat with SubFeat with Prefix
    with FriendlyDisplay with FeaturesImpl {

    private val wpFeatures = weaponPowerFeatures(this).flatMap(_.features)
    private val whFeatures = weaponHitFeature(this, weaponClass).features
    override def features: Seq[Feature[?]] =
      super.features ++ wpFeatures ++ whFeatures

    override protected val prefixSeparator: String = ": "
    override def prefix: Option[String] =
      Some("SuperiorWeaponFocus".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] = greaterWeaponFocusAny.filter { x =>
      x.weaponClass.eq(weaponClass)
    }

    override protected def nameSource: String = weaponClass.displayText
  }

  case class WeaponSpecialization(weaponClass: WeaponClass)
    extends GeneralFeat with WeaponSpecializationBase with RequiresAllOfFeat with SubFeat
    with Prefix with FriendlyDisplay with FeaturesImpl {

    override protected val prefixSeparator: String = ": "
    override def prefix: Option[String] =
      Some("Weapon Specialization".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] = weaponFocusAny.filter { x =>
      x.weaponClass.eq(weaponClass)
    }

    override protected def nameSource: String = weaponClass.displayText
  }

  case class GreaterWeaponSpecialization(weaponClass: WeaponClass)
    extends GeneralFeat with GreaterWeaponSpecializationBase with RequiresAllOfFeat with SubFeat
    with Prefix with FeaturesImpl with FriendlyDisplay {
    private val wpFeatures = weaponPowerFeatures(this).flatMap(_.features)
    private val wdFeatures = weaponDamageFeature(this, weaponClass).features
    override def features: Seq[Feature[?]] =
      super.features ++ wpFeatures ++ wdFeatures

    override protected val prefixSeparator: String = ": "
    override def prefix: Option[String] =
      Some("GreaterWeaponSpecialization".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] =
      greaterWeaponFocusAny.filter { x =>
        x.weaponClass.eq(weaponClass)
      } ++ weaponSpecializationAny.filter { x =>
        x.weaponClass.eq(weaponClass)
      }

    override protected def nameSource: String = weaponClass.displayText
  }

  case class SpellFocus(school: School)
    extends GeneralFeat with SpellFocusBase with SubFeat with Prefix with FeaturesImpl
    with SpellFocusFeature {
    override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
    override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
    override protected lazy val spellFocusCategories: Seq[effect.EffectCategories.Value] =
      Seq(effect.EffectCategories.SpellCasting)
    override protected lazy val spellFocusBonusType: BonusType = BonusType.Feat
    override protected val spellFocusDifficultyCheck: Int = 1
    override protected val spellSchool: School = school

    /**
     * * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("SpellFocus".splitByCase)

    override protected def nameSource: String = school.displayText
  }

  case class GreaterSpellFocus(school: School)
    extends GeneralFeat with GreaterSpellFocusBase with SubFeat with Prefix with FeaturesImpl
    with SpellFocusFeature {
    override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
    override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
    override protected lazy val spellFocusCategories: Seq[effect.EffectCategories.Value] =
      Seq(effect.EffectCategories.SpellCasting)
    override protected lazy val spellFocusBonusType: BonusType = BonusType.Feat
    override protected val spellFocusDifficultyCheck: Int = 1
    override protected val spellSchool: School = school

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("GreaterSpellFocus".splitByCase)

    override def allOfFeats: Seq[GeneralFeat] = lesser

    private def lesser = spellFocusAny.filter { x =>
      x.school.eq(school)
    }

    override protected def nameSource: String = school.displayText
  }

  /**
   * The feat provides proficiency with basic weapons
   *
   * @param weapon
   *   The Simple Weapon Category (i.e. Short Sword, Dagger, Light Crossbow)
   * @note
   *   Every class except Wizards, Monks, and Druids get this feat for free at level 1. Wizards are
   *   only proficient in a subset of Simple weapons: (Club, Dagger, Quarterstaff, Heavy Crossbow,
   *   Light Crossbow, and Throwing Dagger). Monks are proficient with these simple weapons: Club,
   *   Dagger, Quarterstaff, Heavy Crossbow, Light Crossbow and of course Handwraps (Unarmed) Monks
   *   are also proficient with a few non-simple weapons: The Handaxe (Martial), The Kama (Exotic)
   *   and the Shuriken (Exotic) Druids are proficient with these simple weapons: Club, Dagger,
   *   Dart, Quarterstaff, Sickle, and Unarmed. Druids are also proficient with the Martial class
   *   Scimitar.
   */
  case class SimpleWeaponProficiency(
    override val grantToClass: Seq[(HeroicCharacterClass, Int)],
    weapon: WeaponCategory & SimpleWeapon*
  ) extends GeneralFeat with SimpleWeaponProficiencyBase with GrantsToClass with Prefix with SubFeat
    with WeaponProficiencyFeature {
    override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
    override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
    override protected lazy val weaponProficiencyCategories: Seq[effect.EffectCategories.Value] =
      Seq(effect.EffectCategories.GeneralCombat)
    override protected lazy val proficiencyType: BonusType = BonusType.Feat
    override protected val proficiencyAmount: Seq[WeaponCategory] = weapon

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Simple Weapon Proficiency")

    override def entryName: String = {
      val p = prefix.getOrElse("SimpleWeaponProficiency")
      s"$p($nameSource)".replace(" ", "")
    }

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  case class MartialWeaponProficiency(
    override val grantsToRace: Seq[(Race, Int)],
    override val grantToClass: Seq[(HeroicCharacterClass, Int)],
    weapon: WeaponCategory & MartialWeapon*
  ) extends GeneralFeat with RaceRequisiteImpl with MartialWeaponProficiencyBase with Prefix
    with SubFeat with FeaturesImpl with WeaponProficiencyFeature {
    override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
    override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
    override protected lazy val weaponProficiencyCategories: Seq[effect.EffectCategories.Value] =
      Seq(effect.EffectCategories.GeneralCombat)

    override protected lazy val proficiencyType: BonusType = BonusType.Feat
    override protected val proficiencyAmount: Seq[WeaponCategory] = weapon

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Martial Weapon Proficiency")

    // Need to override entryName because the default logic will include the grant arrays in the name.
    override def entryName: String = {
      val p = prefix.getOrElse("MartialWeaponProficiency")
      s"$p($nameSource)".replace(" ", "")
    }

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  case class ExoticWeaponProficiency(
    override val grantsToRace: Seq[(Race, Int)],
    weapon: WeaponCategory & ExoticWeapon*
  ) extends GeneralFeat with RaceRequisiteImpl with ExoticWeaponProficiencyBase with Prefix
    with SubFeat with FeaturesImpl with WeaponProficiencyFeature {
    override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
    override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
    override protected lazy val weaponProficiencyCategories: Seq[effect.EffectCategories.Value] =
      Seq(effect.EffectCategories.GeneralCombat)
    override protected lazy val proficiencyType: BonusType = BonusType.Feat
    override protected val proficiencyAmount: Seq[WeaponCategory] = weapon

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Exotic Weapon Proficiency")

    // Need to override entryName because the default logic will include the grant arrays in the name.
    override def entryName: String = {
      val p = prefix.getOrElse("ExoticWeaponProficiency")
      s"$p($nameSource)".replace(" ", "")
    }

    override protected def nameSource: String = weapon.headOption match {
      case Some(x) => x.displayText
      case _ => entryName
    }
  }

  case class SkillFocus(skill: Skill)
    extends GeneralFeat with SkillFocusBase with SubFeat with Prefix with FeaturesImpl
    with SkillFeature {

    override lazy val bonusType: BonusType = BonusType.Feat
    override val affectedSkills: List[(Skill, Int)] = List((skill, 3))

    /**
     * Delimits the prefix and text.
     */
    override protected val prefixSeparator: String = ": "

    override def prefix: Option[String] = Some("Skill Focus")

    override protected def nameSource: String = skill.displayText
  }

  case object Attack extends GeneralFeat with Attack

  /**
   * Defensive Combat Stance: While using Defensive Fighting mode, you gain a 5% bonus to Armor
   * Class and a -5% penalty to-hit.
   *
   * This is the standard defensive stance and is granted automatically to all characters. Casting a
   * spell ends this mode.
   */
  case object DefensiveFighting extends GeneralFeat with DefensiveFighting

  /**
   * Activate this short-ranged ability while targeting a charmed, commanded, controlled, or
   * dominated enemy that is under your control to dispel the controlling effect.
   * [https://ddowiki.com/page/Dismiss_Charm]
   */
  case object DismissCharm extends GeneralFeat with DismissCharm

  /**
   * https://ddowiki.com/page/Sneak
   */
  case object Sneak extends GeneralFeat with Sneak

  /**
   * A sunder attempt is a melee special attack that, when successful, results in a -10% AC penalty
   * and - 25% Fortification to the target for 6 seconds if it fails a DC (10 + Str mod) Fortitude
   * save. Some creatures may be immune to the sunder effect.[official].
   *
   * Notes:
   *
   * Has an unlisted prerequisite of Base Attack Bonus of 1 for most classes. Monks and Rogues
   * receive Trip at level 1, despite their 3/4 BAB progression. https://ddowiki.com/page/Sunder
   */
  case object Sunder extends GeneralFeat with Sunder

  case object Trip extends GeneralFeat with Trip

  case object HeroicDurability extends GeneralFeat with HeroicDurability

  case object ImprovedHeroicDurability extends GeneralFeat with ImprovedHeroicDurability

  case object MagicalTraining extends GeneralFeat with MagicalTraining

  case object Cleave extends GeneralFeat with Cleave

  case object GreatCleave extends GeneralFeat with GreatCleave

  case object Hamstring extends GeneralFeat with Hamstring

  case object ImprovedFeint extends GeneralFeat with ImprovedFeint

  // Single Weapon Fighting Passive Feats

  case object ImprovedSunder extends GeneralFeat with ImprovedSunder

  case object ImprovedTrip extends GeneralFeat with ImprovedTrip

  case object Sap extends GeneralFeat with Sap

  case object SlicingBlow extends GeneralFeat with SlicingBlow

  case object StunningBlow extends GeneralFeat with StunningBlow

  case object WhirlingSteelStrike extends GeneralFeat with WhirlingSteelStrike

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

  case object DeflectArrows extends GeneralFeat with DeflectArrows

  case object Mobility extends GeneralFeat with Mobility

  case object SpringAttack extends GeneralFeat with SpringAttack

  case object ImprovedCritical
    extends GeneralFeat with ImprovedCriticalBase with ParentFeat with FeaturesImpl {
    override val subFeats: Seq[GeneralFeat & SubFeat] = improvedCriticalAny
  }

  case object PowerCritical extends GeneralFeat with PowerCritical

  case object Toughness extends GeneralFeat with Toughness

  case object KnightsTraining extends GeneralFeat with KnightsTraining

  case object WeaponFinesse extends GeneralFeat with WeaponFinesse

  case object ShieldMastery extends GeneralFeat with ShieldMastery

  case object ImprovedShieldMastery extends GeneralFeat with ImprovedShieldMastery

  case object ImprovedShieldBash extends GeneralFeat with ImprovedShieldBash

  case object ShieldDeflection extends GeneralFeat with ShieldDeflection

  case object SingleWeaponFighting extends GeneralFeat with SingleWeaponFighting

  case object ImprovedSingleWeaponFighting extends GeneralFeat with ImprovedSingleWeaponFighting

  case object GreaterSingleWeaponFighting extends GeneralFeat with GreaterSingleWeaponFighting

  case object TwoHandedFighting extends GeneralFeat with TwoHandedFighting

  case object ImprovedTwoHandedFighting extends GeneralFeat with ImprovedTwoHandedFighting

  case object GreaterTwoHandedFighting extends GeneralFeat with GreaterTwoHandedFighting

  // Two Weapon Passive Feats
  case object TwoWeaponFighting extends GeneralFeat with TwoWeaponFighting

  case object ImprovedTwoWeaponFighting extends GeneralFeat with ImprovedTwoWeaponFighting

  case object GreaterTwoWeaponFighting extends GeneralFeat with GreaterTwoWeaponFighting

  case object OversizedTwoWeaponFighting extends GeneralFeat with OversizedTwoWeaponFighting

  case object TwoWeaponDefense extends GeneralFeat with TwoWeaponDefense

  case object TwoWeaponBlocking extends GeneralFeat with TwoWeaponBlocking

  // Weapon Focus Passive Feats
  case object WeaponFocus extends GeneralFeat with WeaponFocusBase with ParentFeat {
    override val subFeats: Seq[GeneralFeat & SubFeat] = weaponFocusAny
  }

  case object GreaterWeaponFocus
    extends GeneralFeat with GreaterWeaponFocusBase with ParentFeat with RequiresAllOfFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      greaterWeaponFocusAny

    override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.WeaponFocus)
  }

  case object SuperiorWeaponFocus
    extends GeneralFeat with SuperiorWeaponFocusBase with ParentFeat with RequiresAllOfFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      superiorWeaponFocusAny

    override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.WeaponFocus)
  }

  case object WeaponSpecialization
    extends GeneralFeat with WeaponSpecializationBase with ParentFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      weaponSpecializationAny
  }

  case object GreaterWeaponSpecialization
    extends GeneralFeat with GreaterWeaponSpecializationBase with RequiresAllOfFeat
    with ParentFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      greaterWeaponSpecializationAny

    override def allOfFeats: Seq[GeneralFeat] =
      List(GeneralFeat.WeaponSpecialization)
  }

  case object SimpleThrownWeaponExpertise extends GeneralFeat with SimpleThrownWeaponExpertise

  // Tactical Passive Feats
  case object TacticalTraining extends GeneralFeat with TacticalTraining

  case object TacticalCombatant extends GeneralFeat with TacticalCombatant

  case object TacticalMastery extends GeneralFeat with TacticalMastery

  case object TacticalSupremacy extends GeneralFeat with TacticalSupremacy

  // Heavy armor passive feats
  case object HeavyArmorTraining extends GeneralFeat with HeavyArmorTraining

  case object HeavyArmorCombatant extends GeneralFeat with HeavyArmorCombatant

  case object HeavyArmorMaster extends GeneralFeat with HeavyArmorMaster

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

  case object SwordsToPlowshares extends GeneralFeat with SwordsToPlowshares

  case object StunningFist extends GeneralFeat with StunningFist

  /**
   * You can use your [Wisdom](/page/Wisdom "Wisdom") bonus instead of [Dexterity](/page/Dexterity
   * "Dexterity") bonus to determine bonus to attack with ranged missile weapons if it is higher.
   *
   * * Does <u>not</u> apply to [thrown weapons](/page/Thrown_weapon "Thrown weapon"). *
   * [Shortbows](/page/Shortbow "Shortbow") and [Longbows](/page/Longbow "Longbow") are considered
   * [Ki weapons](/page/Ki_weapons "Ki weapons").
   */
  case object ZenArchery extends GeneralFeat with ZenArchery

  // Spellcasting related
  case object AugmentSummoning extends GeneralFeat with AugmentSummoning

  case object MentalToughness extends GeneralFeat with MentalToughness

  case object ImprovedMentalToughness extends GeneralFeat with ImprovedMentalToughness

  case object MobileSpellcasting extends GeneralFeat with MobileSpellcasting

  case object NaturalFighting extends GeneralFeat with NaturalFighting

  case object SpellFocus extends GeneralFeat with SpellFocusBase with ParentFeat {
    override val subFeats: Seq[GeneralFeat & SubFeat] = spellFocusAny
  }

  case object GreaterSpellFocus extends GeneralFeat with GreaterSpellFocusBase with ParentFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      greaterWeaponFocusAny
  }

  case object SpellPenetration extends GeneralFeat with SpellPenetration with PostText {
    override def postText: Option[String] = Some("feat")
  }

  case object GreaterSpellPenetration extends GeneralFeat with GreaterSpellPenetration

  // Armor, Shield & Weapon proficiencies
  case object LightArmorProficiency extends GeneralFeat with LightArmorProficiency

  case object MediumArmorProficiency extends GeneralFeat with MediumArmorProficiency

  case object HeavyArmorProficiency extends GeneralFeat with HeavyArmorProficiency

  case object ShieldProficiency extends GeneralFeat with ShieldProficiency with PostText {
    override def postText: Option[String] = Some("General")
  }

  case object TowerShieldProficiency extends GeneralFeat with TowerShieldProficiency

  // Skill bonus feats

  case object SimpleWeaponProficiency
    extends GeneralFeat with SimpleWeaponProficiencyBase with GrantsToClass with ParentFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      simpleWeaponProficiencies
  }

  case object MartialWeaponProficiency
    extends GeneralFeat with RaceRequisiteImpl with MartialWeaponProficiencyBase with ParentFeat {

    override val subFeats: Seq[GeneralFeat & SubFeat] =
      martialWeaponProficiencies
  }

  case object ExoticWeaponProficiency
    extends GeneralFeat with RaceRequisiteImpl with ExoticWeaponProficiencyBase with ParentFeat {

    override val subFeats: Seq[ExoticWeaponProficiency] =
      exoticWeaponProficiencies

    override def prefix: Option[String] = None
  }

  case object Acrobatic extends GeneralFeat with Acrobatic

  case object Alertness extends GeneralFeat with Alertness

  case object Athletic extends GeneralFeat with Athletic

  case object CombatCasting extends GeneralFeat with CombatCasting

  case object Negotiator extends GeneralFeat with Negotiator

  case object NimbleFingers extends GeneralFeat with NimbleFingers

  case object SelfSufficient extends GeneralFeat with SelfSufficient

  case object SkillFocus extends GeneralFeat with SkillFocusBase with ParentFeat {
    override val subFeats: Seq[GeneralFeat & SubFeat] = skillFocusAny
  }

  // Skill and Save bonus feats

  case object Stealthy extends GeneralFeat with Stealthy

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
    extends GeneralFeat with CoinLordFinishingSchoolTraining

  case object DraconicVitality extends GeneralFeat with DraconicVitality
}
