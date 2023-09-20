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
package io.truthencode.ddo.model.effect

import enumeratum.{Enum => SmartEnum, EnumEntry}

sealed trait AugmentEffectType extends EnumEntry

object AugmentEffectType extends SmartEnum[AugmentEffectType] {
  override val values = findValues

  case object Attribute extends AugmentEffectType

  case object Skill extends AugmentEffectType
}

sealed trait GrantEffectType extends EnumEntry

object GrantEffectType extends SmartEnum[GrantEffectType] {
  override val values = findValues
  case object ListSomeEffects extends GrantEffectType
}
