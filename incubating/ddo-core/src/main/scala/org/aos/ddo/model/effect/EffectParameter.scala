/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.model.effect

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
