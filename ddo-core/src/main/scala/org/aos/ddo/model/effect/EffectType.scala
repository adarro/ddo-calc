package org.aos.ddo.model.effect

import enumeratum.{ Enum => SmartEnum }
import enumeratum.EnumEntry

sealed trait AugmentEffectType extends EnumEntry

object AugmentEffectType extends SmartEnum[AugmentEffectType] {
  case object Attribute extends AugmentEffectType
  case object Skill extends AugmentEffectType

  override val values: Seq[AugmentEffectType] = findValues
}

sealed trait GrantEffectType extends EnumEntry

object GrantEffectType extends SmartEnum[GrantEffectType] {
  override val values: Seq[GrantEffectType] = findValues
  case object ListSomeEffects extends GrantEffectType
}
