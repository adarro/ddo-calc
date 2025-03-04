/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Effects.scala
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
