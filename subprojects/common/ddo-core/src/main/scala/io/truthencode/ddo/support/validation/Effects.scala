package io.truthencode.ddo.support.validation

import io.truthencode.ddo.api.model.effect.{DetailedEffect, ScalingInfo}
import io.truthencode.ddo.support.validation.ScalingValidation.validateScalingPower
import zio.prelude.Validation

def validateDetailedEffect(
  id: String,
  description: String,
  triggersOn: Seq[String],
  triggersOff: Seq[String],
  bonusType: String,
  scaling: Option[Seq[ScalingInfo]])(using
  filterStrategy: invalidationOptions): Validation[String, DetailedEffect] =
  Validation.validateWith(
    validateName(id),
    validateDescription(description),
    validateTriggers(triggersOn),
    validateTriggers(triggersOff),
    validateBonusType(bonusType),
    validateScalingPower(scaling)
  )(DetailedEffect.apply)
