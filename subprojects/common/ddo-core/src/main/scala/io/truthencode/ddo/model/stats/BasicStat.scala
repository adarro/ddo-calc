/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BasicStat.scala
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
package io.truthencode.ddo.model.stats

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.Abbreviation
import io.truthencode.ddo.model.abilities.{ActiveAbilities, UsingAbilitySearchPrefix}
import io.truthencode.ddo.model.schools.School
import io.truthencode.ddo.model.spells.SpellPower
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay, UsingSearchPrefix}

import java.util
import scala.collection.immutable
import scala.jdk.CollectionConverters.*

sealed trait BasicStat
  extends EnumEntry with DisplayName with FriendlyDisplay with UsingSearchPrefix {
  self: Category =>
  override protected lazy val nameSource: String =
    entryName.splitByCase.toPascalCase
  override val withPrefix: String = s"$searchPrefix$nameSource"

  override def searchPrefix: String = s"""$searchPrefixSource${searchDelimiter.getOrElse("")}"""

  /**
   * Override this function for a more specific match such as "ToHitChance"
   *
   * @return
   *   A default prefix "BasicStat"
   */
  override def searchPrefixSource: String = "BasicStat"
}

trait DodgeChance extends BasicStat with MissChance {
  override lazy val searchDelimiter: Option[String] = Some(":")

  override def searchPrefixSource: String = "MissChance"
}

trait ArmorClass extends BasicStat with MissChance

trait MaxDexterityBonus extends BasicStat with MissChance

trait ChanceToHit extends BasicStat with HitChance

trait WeaponDamage extends BasicStat with GeneralCombat

trait MeleePower extends BasicStat with MeleeCombat {

  /**
   * Override this function for a more specific match such as "ToHitChance"
   *
   * @return
   *   A default prefix "BasicStat"
   */
  override def searchPrefixSource: String = "Damage"
}

trait RangedPower extends BasicStat with RangedCombat

trait WeaponProficiency extends BasicStat with Proficiency

trait BaseAttackBonus extends BasicStat with Abbreviation with General {

  /**
   * The short form of the word
   */
  override val abbr: String = "BaB"

  /**
   * Expands the abbr to its full value
   */
  override def toFullWord: String = entryName
}

/**
 * Used to determine how much damage you can receive before becoming unconscious or dead.
 */
trait HitPoints extends BasicStat with Health

trait SpellPoints extends BasicStat with SpellPointPool

trait SpellCriticalChance extends BasicStat with SpellCasting

trait SpellCriticalMultiplier extends BasicStat with SpellCasting

trait SpellDifficultyCheck extends BasicStat with SpellCasting

/**
 * Affects Character speed when moving. (Moving while sneaking or wearing over-level equipment may
 * reduce this speed)
 */
trait MovementSpeedModifier extends BasicStat with Movement

/**
 * amount health is allowed to go negative in an 'incapacitated' manner before death is triggered.
 */
trait UnconsciousRange extends BasicStat with General
// Saving Throws

trait SavesVsSpells extends BasicStat with SavingThrows

trait SavesVsTraps extends BasicStat with SavingThrows

trait SavesVsFear extends BasicStat with SavingThrows

trait SavesVsEnchantment extends BasicStat with SavingThrows

trait SavesVsCurses extends BasicStat with SavingThrows

trait SavesVsIllusions extends BasicStat with SavingThrows

trait SavesVsSleep extends BasicStat with SavingThrows

trait SavesVsDiseases extends BasicStat with SavingThrows

trait SavesVsExhaustion extends BasicStat with SavingThrows

trait SavesVsNausea extends BasicStat with SavingThrows

trait SavesVsParalysis extends BasicStat with SavingThrows

trait SavesVsPoison extends BasicStat with SavingThrows

// Elemental Defenses

trait AcidResistance extends BasicStat with ElementalDefenses

trait ColdResistance extends BasicStat with ElementalDefenses

trait ElectricResistance extends BasicStat with ElementalDefenses

trait FireResistance extends BasicStat with ElementalDefenses

trait LightResistance extends BasicStat with ElementalDefenses

trait NegativeResistance extends BasicStat with ElementalDefenses

trait PoisonResistance extends BasicStat with ElementalDefenses

trait SonicResistance extends BasicStat with ElementalDefenses

trait AcidAbsorption extends BasicStat with ElementalDefenses

trait ChaosAbsorption extends BasicStat with ElementalDefenses

trait ColdAbsorption extends BasicStat with ElementalDefenses

trait ElectricAbsorption extends BasicStat with ElementalDefenses

trait EvilAbsorption extends BasicStat with ElementalDefenses

trait FireAbsorption extends BasicStat with ElementalDefenses

trait ForceAbsorption extends BasicStat with ElementalDefenses

trait GoodAbsorption extends BasicStat with ElementalDefenses

trait LawfulAbsorption extends BasicStat with ElementalDefenses

trait LightAbsorption extends BasicStat with ElementalDefenses

trait NegativeAbsorption extends BasicStat with ElementalDefenses

trait PoisonAbsorption extends BasicStat with ElementalDefenses

trait SonicAbsorption extends BasicStat with ElementalDefenses

// Spellcasting
trait SpellCostReduction extends BasicStat with SpellCasting

trait SpellThreatMultiplier extends BasicStat with SpellCasting

trait SpellPenetrationBonuses extends BasicStat with SpellCasting

// Metamagic cost reduction (not displayed in current game UI)
trait EmpowerHealingCostReduction extends BasicStat with SpellCasting

trait EmpowerCostReduction extends BasicStat with SpellCasting

trait EnlargeCostReduction extends BasicStat with SpellCasting

trait EschewMaterialsCostReduction extends BasicStat with SpellCasting

trait ExtendCostReduction extends BasicStat with SpellCasting

trait HeightenCostReduction extends BasicStat with SpellCasting

trait MaximizeCostReduction extends BasicStat with SpellCasting

trait QuickenCostReduction extends BasicStat with SpellCasting

trait EmboldenCostReduction extends BasicStat with SpellCasting

trait IntensifyCostReduction extends BasicStat with SpellCasting

trait AccelerateCostReduction extends BasicStat with SpellCasting

trait TurnUndeadCategory extends BasicStat with TurnUndead

/**
 * Turn Check This determines the maximum number of Hit Dice a monster can have and still be
 * affected from your turn undead attempt. If this check does not produce a number equal or higher
 * to the monster near you that has the lowest hit dice, then you will not be able to turn at all
 * and a message pops up saying "You lack conviction to turn the target"
 */
trait MaxHitDice extends TurnUndeadCategory

/**
 * Turning damage: This determines the number of total Hit Dice you will be able to turn. This means
 * that the sum of the affected monsters' hit dice must be less or equal to that number. To
 * determine which monsters you turn, the game starts from the monster with the lowest HD (hit dice)
 * and works its way up. For example: If you can turn 30 total hit dice and you have monsters with
 * (5, 7, 10, 15) HD around you, you will turn the ones with (5, 7, 10) for a total of 22 hit dice
 * and not the (15, 10, 5) that would utilize all 30 of your hit dice.
 */
trait TotalHitDice extends TurnUndeadCategory

/**
 * The Number of Turns per rest your character can do. This is initially calculated by a base of 3.
 * Other effects such as Paladin / cleric level and some sovereign items / filigrees can increase
 * this number.
 */
trait NumberOfTurns extends TurnUndeadCategory

trait TurnUndeadLevel extends TurnUndeadCategory

// General Combat
protected trait GeneralCombatCategory extends BasicStat with GeneralCombat

trait FortificationBypass extends BasicStat with GeneralCombatCategory

trait DodgeBypass extends BasicStat with GeneralCombatCategory

trait HelplessDamage extends BasicStat with GeneralCombatCategory

trait CriticalHitConfirmation extends BasicStat with GeneralCombatCategory

trait CriticalHitDamage extends BasicStat with GeneralCombatCategory

trait CriticalThreatRange extends BasicStat with GeneralCombatCategory

trait SneakAttackHitBonus extends BasicStat with GeneralCombatCategory

trait SneakAttackDamageBonus extends BasicStat with GeneralCombatCategory

trait SneakAttackDice extends BasicStat with GeneralCombatCategory

// Melee Combat
trait MeleeCombatCategory extends BasicStat with MeleeCombat

trait OneHandedAttackSpeedBonus extends BasicStat with MeleeCombatCategory

trait TwoWeaponAttackSpeedBonus extends BasicStat with MeleeCombatCategory

trait TwoHandedAttackSpeedBonus extends BasicStat with MeleeCombatCategory

trait QuarterstaffAttackSpeedBonus extends BasicStat with MeleeCombatCategory

/**
 * @note
 *   I could not find the display for Shield Bash chance on the character sheet. however, secondary
 *   shield bash is available in the '+' menu under melee combat
 */
trait ShieldBashChance extends BasicStat with MeleeCombatCategory

trait SecondaryShieldBashChance extends BasicStat with MeleeCombatCategory

trait OffhandHitChance extends BasicStat with MeleeCombatCategory

trait OffhandDoublestrike extends BasicStat with MeleeCombatCategory

trait GlancingblowDamage extends BasicStat with MeleeCombatCategory

trait GlancingBlowProcChance extends BasicStat with MeleeCombatCategory

trait MeleeThreatMultiplier extends BasicStat with MeleeCombatCategory

trait StrikeThroughChance extends BasicStat with MeleeCombatCategory

// Ranged Combat
trait RangedCombatCategory extends BasicStat with RangedCombat

trait ThrownAttackSpeedBonus extends BasicStat with RangedCombatCategory

trait NonRepeatingCrossbowAttackSpeedBonus extends BasicStat with RangedCombatCategory

trait RepeatingCrossbowAttackSpeedBonus extends BasicStat with RangedCombatCategory

trait BowAttackSpeedBonus extends BasicStat with RangedCombatCategory

trait RangedThreatMultiplier extends BasicStat with RangedCombatCategory

/** Distance from target used for point blank damage (not shown in Game UI) */
trait PointBlankShotRange extends BasicStat with RangedCombatCategory

trait DoubleShot extends BasicStat with RangedCombatCategory

trait AutoRecovery extends BasicStat with Recovery

trait GrantedAbility extends BasicStat with Ability with UsingAbilitySearchPrefix {
  val ability: ActiveAbilities
}

// scalastyle:off number.of.methods
object BasicStat extends Enum[BasicStat] with SearchPrefix {

  lazy val allGrantedAbilities: Seq[GrantedAbilities] =
    ActiveAbilities.values.map(GrantedAbilities.apply)

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Stat"

  override def values: immutable.IndexedSeq[BasicStat] = findValues ++ allGrantedAbilities

  def asJava: util.List[BasicStat] = values.asJava

  val extractCategory: PartialFunction[BasicStat, Category] = { case x: Category =>
    x
  }

  /**
   * Extracts all categories used by this Enumeration.
   * @return
   *   A collection of categories
   */
  def categories: List[Category] = values
    .collect(extractCategory)
    .groupBy(_.categoryId)
    .map { c =>
      c._2.head
    }
    .toList

  /**
   * Java-friendly version of categories
   * @return
   */
  def categoriesJava: util.List[Category] = categories.asJava

  case class GrantedAbilities(override val ability: ActiveAbilities)
    extends GrantedAbility, BasicStat {
    override lazy val entryName: String = s"${ability.entryName}"
  }

  case class SpellCriticalChanceSchool(spellPower: SpellPower)
    extends SpellCriticalChance, BasicStat

  case class SpellFocus(school: School) extends SpellDifficultyCheck, BasicStat

  /**
   * The dodge mechanic works as a miss chance - a simple percentile chance to completely avoid
   * physical attacks.
   */
  case object DodgeChance extends DodgeChance, BasicStat {
    override def entryName: String = "Dodge"
  }

  /**
   * Armor Class, also called AC, represents your chance to be missed by melee attacks - the higher
   * your AC, the less you get hit. This chance is also influenced by the attackers attack bonus.
   */
  case object ArmorClass extends ArmorClass, BasicStat

  /**
   * MDB - caps your dexterity bonus when wearing certain armor.
   * [[https://ddowiki.com/page/Maximum_dexterity_bonus]]
   */
  case object MaxDexterityBonus extends MaxDexterityBonus, BasicStat

  /**
   * Chance to Hit, generally applies your Melee and Ranged Weapons.
   */
  case object ChanceToHit extends ChanceToHit, BasicStat

  /**
   * Adds Damage to equipped weapon.
   */
  case object ToDamage extends WeaponDamage, BasicStat

  case object MeleePower extends MeleePower, BasicStat

  case object RangedPower extends RangedPower, BasicStat

  case object WeaponProficiency extends WeaponProficiency, BasicStat

  /**
   * Your Hit points
   */
  case object HitPoints extends HitPoints, BasicStat

  case object SpellPoints extends SpellPoints, BasicStat

  case object BaseAttackBonus extends BaseAttackBonus, BasicStat

  case object MovementSpeedModifier extends MovementSpeedModifier, BasicStat

  case object UnconsciousRange extends UnconsciousRange, BasicStat

  case object SavesVsSpells extends SavesVsSpells, BasicStat

  case object SavesVsTraps extends SavesVsTraps, BasicStat

  case object SavesVsFear extends SavesVsFear, BasicStat

  case object SavesVsEnchantment extends SavesVsEnchantment, BasicStat

  case object SavesVsCurses extends SavesVsCurses, BasicStat

  case object SavesVsIllusions extends SavesVsIllusions, BasicStat

  case object SavesVsSleep extends SavesVsSleep, BasicStat

  case object SavesVsDiseases extends SavesVsDiseases, BasicStat

  case object SavesVsExhaustion extends SavesVsExhaustion, BasicStat

  case object SavesVsNausea extends SavesVsNausea, BasicStat

  case object SavesVsParalysis extends SavesVsParalysis, BasicStat

  case object SavesVsPoison extends SavesVsPoison, BasicStat

  case object AcidResistance extends AcidResistance, BasicStat

  case object ColdResistance extends ColdResistance, BasicStat

  case object ElectricResistance extends ElectricResistance, BasicStat

  case object FireResistance extends FireResistance, BasicStat

  case object LightResistance extends LightResistance, BasicStat

  case object NegativeResistance extends NegativeResistance, BasicStat

  case object PoisonResistance extends PoisonResistance, BasicStat

  case object SonicResistance extends SonicResistance, BasicStat

  case object AcidAbsorption extends AcidAbsorption, BasicStat

  case object ChaosAbsorption extends ChaosAbsorption, BasicStat

  case object ColdAbsorption extends ColdAbsorption, BasicStat

  case object ElectricAbsorption extends ElectricAbsorption, BasicStat

  case object EvilAbsorption extends EvilAbsorption, BasicStat

  case object FireAbsorption extends FireAbsorption, BasicStat

  case object ForceAbsorption extends ForceAbsorption, BasicStat

  case object GoodAbsorption extends GoodAbsorption, BasicStat

  case object LawfulAbsorption extends LawfulAbsorption, BasicStat

  case object LightAbsorption extends LightAbsorption, BasicStat

  case object NegativeAbsorption extends NegativeAbsorption, BasicStat

  case object PoisonAbsorption extends PoisonAbsorption, BasicStat

  case object SonicAbsorption extends SonicAbsorption, BasicStat

  case object SpellCostReduction extends SpellCostReduction, BasicStat

  case object SpellThreatMultiplier extends SpellThreatMultiplier, BasicStat

  case object SpellPenetrationBonuses extends SpellPenetrationBonuses, BasicStat

  case object EmpowerHealingCostReduction extends EmpowerHealingCostReduction, BasicStat

  case object EmpowerCostReduction extends EmpowerCostReduction, BasicStat

  case object EnlargeCostReduction extends EnlargeCostReduction, BasicStat

  case object EschewMaterialsCostReduction extends EschewMaterialsCostReduction, BasicStat

  case object ExtendCostReduction extends ExtendCostReduction, BasicStat

  case object HeightenCostReduction extends HeightenCostReduction, BasicStat

  case object MaximizeCostReduction extends MaximizeCostReduction, BasicStat

  case object QuickenCostReduction extends QuickenCostReduction, BasicStat

  case object EmboldenCostReduction extends EmboldenCostReduction, BasicStat

  case object IntensifyCostReduction extends IntensifyCostReduction, BasicStat

  case object AccelerateCostReduction extends AccelerateCostReduction, BasicStat

  case object FortificationBypass extends FortificationBypass, BasicStat

  case object DodgeBypass extends DodgeBypass, BasicStat

  case object HelplessDamage extends HelplessDamage, BasicStat

  case object CriticalHitConfirmation extends CriticalHitConfirmation, BasicStat

  case object CriticalHitDamage extends CriticalHitDamage, BasicStat

  case object CriticalThreatRange extends CriticalThreatRange, BasicStat

  case object SneakAttackHitBonus extends SneakAttackHitBonus, BasicStat

  case object SneakAttackDamageBonus extends SneakAttackDamageBonus, BasicStat

  case object SneakAttackDice extends SneakAttackDice, BasicStat

  case object OneHandedAttackSpeedBonus extends OneHandedAttackSpeedBonus, BasicStat

  case object TwoWeaponAttackSpeedBonus extends TwoWeaponAttackSpeedBonus, BasicStat

  case object TwoHandedAttackSpeedBonus extends TwoHandedAttackSpeedBonus, BasicStat

  case object QuarterstaffAttackSpeedBonus extends QuarterstaffAttackSpeedBonus, BasicStat

  case object ShieldBashChance extends ShieldBashChance, BasicStat

  case object SecondaryShieldBashChance extends SecondaryShieldBashChance, BasicStat

  case object OffhandHitChance extends OffhandHitChance, BasicStat

  /**
   * As of [[https://ddowiki.com/page/Update_49_Release_Notes#What.27s_Changing: Update 49]], this
   * is no longer a configurable stat and is by default 50% of your Mainhand doublestrike *
   */
  case object OffhandDoublestrike extends OffhandDoublestrike, BasicStat

  case object GlancingblowDamage extends GlancingblowDamage, BasicStat

  case object GlancingBlowProcChance extends GlancingBlowProcChance, BasicStat

  case object StrikeThroughChance extends StrikeThroughChance, BasicStat

  case object MeleeThreatMultiplier extends MeleeThreatMultiplier, BasicStat

  case object ThrownAttackSpeedBonus extends ThrownAttackSpeedBonus, BasicStat

  case object NonRepeatingCrossbowAttackSpeedBonus
    extends NonRepeatingCrossbowAttackSpeedBonus, BasicStat

  case object RepeatingCrossbowAttackSpeedBonus extends RepeatingCrossbowAttackSpeedBonus, BasicStat

  case object BowAttackSpeedBonus extends BowAttackSpeedBonus, BasicStat

  case object RangedThreatMultiplier extends RangedThreatMultiplier, BasicStat

  case object PointBlankShotRange extends PointBlankShotRange, BasicStat

  case object TurnUndeadMaxHitDice extends MaxHitDice, BasicStat

  case object TurnUndeadTotalHitDice extends TotalHitDice, BasicStat

  case object TurnUndeadNumberOfTurns extends NumberOfTurns, BasicStat

  case object TurnUndeadLevel extends TurnUndeadLevel, BasicStat

  case object AutoRecovery extends AutoRecovery, BasicStat

  case object DoubleShot extends DoubleShot, BasicStat
}
