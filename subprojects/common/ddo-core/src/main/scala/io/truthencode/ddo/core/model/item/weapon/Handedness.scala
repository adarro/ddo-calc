/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Handedness.scala
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
package io.truthencode.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault

/**
 * handedness is used to determine a one-handed, two-handed or offhand equip. DDOwiki lists bows as
 * ranged
 */
sealed trait Handedness extends EnumEntry with NoDefault[Handedness]
object Handedness extends Enum[Handedness] {
  override val values = findValues

  case object OneHand extends Handedness

  case object TwoHand extends Handedness

  case object OffHand extends Handedness
}
