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
package io.truthencode.ddo.model.meta

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.model.effect._

import scala.collection.immutable

sealed trait MagicDamageType extends EnumEntry with Damage with NoDefault[MagicDamageType]

object MagicDamageType extends Enum[MagicDamageType] {
  override def values: immutable.IndexedSeq[MagicDamageType] = findValues
  case object Acid extends Acid with MagicDamageType
  case object Fire extends Fire with MagicDamageType
  case object Cold extends Cold with MagicDamageType
  case object Electric extends Electric with MagicDamageType
  case object Force extends Force with MagicDamageType
  case object Sonic extends Sonic with MagicDamageType
  case object Poison extends Poison with MagicDamageType
  case object Evil extends Evil with MagicDamageType
  case object Good extends Good with MagicDamageType
  case object UnTyped extends MagicDamageType
}
