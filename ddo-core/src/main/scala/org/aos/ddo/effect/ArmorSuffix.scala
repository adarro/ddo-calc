package org.aos.ddo.effect

import enumeratum.{ Enum => SmartEnum, EnumEntry }
import org.aos.ddo.support.Descriptive

sealed trait ArmorSuffix extends EnumEntry with Suffix
object ArmorSuffix extends SmartEnum[ArmorSuffix] {
  override val values = findValues
  case object AcidResistance extends ArmorSuffix with Resist with Acid
  case object Axeblock extends ArmorSuffix with DamageReduction with Slashing
  case object ColdResistance extends ArmorSuffix with Resist with Cold
  case object Command extends ArmorSuffix
  // case object  Crafteditemenchantments/Temporarylist  extends ArmorSuffix
  case object ElectricResistance extends ArmorSuffix with Resist with Electric
  /**
    * Enumeration indicating All elements
    */
  case object ElementalResistance extends ArmorSuffix with Resist with ElementalResistance
  case object FalseLife extends ArmorSuffix
  case object FireResistance extends ArmorSuffix with Resist with Fire
  case object GreaterLuck extends ArmorSuffix
  case object Hammerblock extends ArmorSuffix with DamageReduction with Bludgeoning
  case object Incineration extends ArmorSuffix
  case object Invulnerability extends ArmorSuffix with DamageReduction with Magic
  case object LesserSpearblock extends ArmorSuffix with DamageReduction with Slashing
  case object LightResistance extends ArmorSuffix with DamageReduction with Light
  case object Power extends ArmorSuffix
  case object Prisms extends ArmorSuffix with ElementalResistance
  // TODO: Need to add Protection to Reinforcement
  case object Reinforcement extends ArmorSuffix with DamageReduction with FullPhysical
  case object Shadow extends ArmorSuffix
  case object SilentMoves extends ArmorSuffix
  case object SonicResistance extends ArmorSuffix with Resist with Sonic
  case object Spearblock extends ArmorSuffix with DamageReduction with Piercing
  case object Stability extends ArmorSuffix
  case object UndeadGuard extends ArmorSuffix
  case object Warding extends ArmorSuffix
  case object Watchfulness extends ArmorSuffix
  case object Wizardry extends ArmorSuffix
}
