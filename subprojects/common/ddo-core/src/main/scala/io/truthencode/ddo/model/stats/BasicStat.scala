/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

sealed trait BasicStat extends EnumEntry with DisplayName with FriendlyDisplay {
    self: Category =>
    override protected def nameSource: String =
        entryName.splitByCase.toPascalCase
}


trait DodgeChance extends BasicStat with General

trait BaseAttackBonus extends BasicStat with Abbreviation with General {

    /** The short form of the word
      */
    override val abbr: String = "BaB"

    /** Expands the abbr to its full value
      */
    override def toFullWord: String = entryName
}

/**
  * Affects Character speed when moving.
  * (Moving while sneaking or wearing over-level equipment may reduce this speed)
  */
trait MovementSpeedModifier extends BasicStat with Movement

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


// General Combat
protected trait GeneralCombatCategory extends BasicStat with GeneralCombat

trait FortificationBypass extends GeneralCombatCategory

trait DodgeBypass extends GeneralCombatCategory

trait HelplessDamage extends GeneralCombatCategory

trait CriticalHitConfirmation extends GeneralCombatCategory

trait CriticalHitDamage extends GeneralCombatCategory

trait SneakAttackHitBonus extends GeneralCombatCategory

trait SneakAttackDamageBonus extends GeneralCombatCategory

trait SneakAttackDice extends GeneralCombatCategory

// Melee Combat
trait MeleeCombatCategory extends BasicStat with MeleeCombat

trait OneHandedAttackSpeedBonus extends MeleeCombatCategory

trait TwoWeaponAttackSpeedBonus extends MeleeCombatCategory

trait TwoHandedAttackSpeedBonus extends MeleeCombatCategory

trait QuarterstaffAttackSpeedBonus extends MeleeCombatCategory

/**
  * @note I could not find the display for Shield Bash chance on the character sheet.
  *       however, secondary shield bash is available in the '+' menu under melee combat
  */
trait ShieldBashChance extends MeleeCombatCategory

trait SecondaryShieldBashChance extends MeleeCombatCategory

trait OffhandHitChance extends MeleeCombatCategory

trait OffhandDoublestrike extends MeleeCombatCategory

trait GlancingblowDamage extends MeleeCombatCategory

trait GlancingBlowProcChance extends MeleeCombatCategory

trait MeleeThreatMultiplier extends MeleeCombatCategory

trait StrikeThroughChance extends MeleeCombatCategory

// Ranged Combat
trait RangedCombatCategory extends BasicStat with RangedCombat

trait ThrownAttackSpeedBonus extends RangedCombatCategory

trait NonRepeatingCrossbowAttackSpeedBonus extends RangedCombatCategory

trait RepeatingCrossbowAttackSpeedBonus extends RangedCombatCategory

trait BowAttackSpeedBonus extends RangedCombatCategory

trait RangedThreatMultiplier extends RangedCombatCategory

// scalastyle:off number.of.methods
object BasicStat extends Enum[BasicStat] with SearchPrefix {
    override def values: immutable.IndexedSeq[BasicStat] = findValues

    case object DodgeChance extends DodgeChance

    case object BaseAttackBonus extends BaseAttackBonus

    case object MovementSpeedModifier extends MovementSpeedModifier

    case object SavesVsSpells extends SavesVsSpells

    case object SavesVsTraps extends SavesVsTraps

    case object SavesVsFear extends SavesVsFear

    case object SavesVsEnchantment extends SavesVsEnchantment

    case object SavesVsCurses extends SavesVsCurses

    case object SavesVsIllusions extends SavesVsIllusions

    case object SavesVsSleep extends SavesVsSleep

    case object SavesVsDiseases extends SavesVsDiseases

    case object SavesVsExhaustion extends SavesVsExhaustion

    case object SavesVsNausea extends SavesVsNausea

    case object SavesVsParalysis extends SavesVsParalysis

    case object SavesVsPoison extends SavesVsPoison

    case object AcidResistance extends AcidResistance

    case object ColdResistance extends ColdResistance

    case object ElectricResistance extends ElectricResistance

    case object FireResistance extends FireResistance

    case object LightResistance extends LightResistance

    case object NegativeResistance extends NegativeResistance

    case object PoisonResistance extends PoisonResistance

    case object SonicResistance extends SonicResistance

    case object AcidAbsorption extends AcidAbsorption

    case object ChaosAbsorption extends ChaosAbsorption

    case object ColdAbsorption extends ColdAbsorption

    case object ElectricAbsorption extends ElectricAbsorption

    case object EvilAbsorption extends EvilAbsorption

    case object FireAbsorption extends FireAbsorption

    case object ForceAbsorption extends ForceAbsorption

    case object GoodAbsorption extends GoodAbsorption

    case object LawfulAbsorption extends LawfulAbsorption

    case object LightAbsorption extends LightAbsorption

    case object NegativeAbsorption extends NegativeAbsorption

    case object PoisonAbsorption extends PoisonAbsorption

    case object SonicAbsorption extends SonicAbsorption

    case object SpellCostReduction extends SpellCostReduction

    case object SpellThreatMultiplier extends SpellThreatMultiplier

    case object SpellPenetrationBonuses extends SpellPenetrationBonuses

    case object EmpowerHealingCostReduction extends EmpowerHealingCostReduction

    case object EmpowerCostReduction extends EmpowerCostReduction

    case object EnlargeCostReduction extends EnlargeCostReduction

    case object EschewMaterialsCostReduction extends EschewMaterialsCostReduction

    case object ExtendCostReduction extends ExtendCostReduction

    case object HeightenCostReduction extends HeightenCostReduction

    case object MaximizeCostReduction extends MaximizeCostReduction

    case object QuickenCostReduction extends QuickenCostReduction

    case object EmboldenCostReduction extends EmboldenCostReduction

    case object IntensifyCostReduction extends IntensifyCostReduction

    case object AccelerateCostReduction extends AccelerateCostReduction

    case object FortificationBypass extends FortificationBypass

    case object DodgeBypass extends DodgeBypass

    case object HelplessDamage extends HelplessDamage

    case object CriticalHitConfirmation extends CriticalHitConfirmation

    case object CriticalHitDamage extends CriticalHitDamage

    case object SneakAttackHitBonus extends SneakAttackHitBonus

    case object SneakAttackDamageBonus extends SneakAttackDamageBonus

    case object SneakAttackDice extends SneakAttackDice

    case object OneHandedAttackSpeedBonus extends OneHandedAttackSpeedBonus

    case object TwoWeaponAttackSpeedBonus extends TwoWeaponAttackSpeedBonus

    case object TwoHandedAttackSpeedBonus extends TwoHandedAttackSpeedBonus

    case object QuarterstaffAttackSpeedBonus extends QuarterstaffAttackSpeedBonus

    case object ShieldBashChance extends ShieldBashChance

    case object SecondaryShieldBashChance extends SecondaryShieldBashChance

    case object OffhandHitChance extends OffhandHitChance

    case object OffhandDoublestrike extends OffhandDoublestrike

    case object GlancingblowDamage extends GlancingblowDamage

    case object GlancingBlowProcChance extends GlancingBlowProcChance

    case object StrikeThroughChance extends StrikeThroughChance

    case object MeleeThreatMultiplier extends MeleeThreatMultiplier

    case object ThrownAttackSpeedBonus extends ThrownAttackSpeedBonus

    case object NonRepeatingCrossbowAttackSpeedBonus
        extends NonRepeatingCrossbowAttackSpeedBonus

    case object RepeatingCrossbowAttackSpeedBonus
        extends RepeatingCrossbowAttackSpeedBonus

    case object BowAttackSpeedBonus extends BowAttackSpeedBonus

    case object RangedThreatMultiplier extends RangedThreatMultiplier

    /**
      * Used when qualifying a search with a prefix.
      * Examples include finding "HalfElf" from qualified "Race:HalfElf"
      *
      * @return A default or applied prefix
      */
    override def searchPrefixSource: String = "Stat"
}
