package org.aos.ddo.effect

import enumeratum.{ Enum => SmartEnum, EnumEntry }
sealed trait EffectParameter extends EnumEntry
object EffectParameter extends SmartEnum[EffectParameter] {
  val values = findValues
  case object Trigger extends EffectParameter
  case object BonusType extends EffectParameter
  case object Magnitude extends EffectParameter
  case object DifficultyCheck extends EffectParameter
  case object Target extends EffectParameter
}
