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
package io.truthencode.ddo.model.misc

import enumeratum.{Enum => SmartEnum, EnumEntry}

/**
 * Represents the material make up of an object.
 *
 * @note
 *   This is not to be confused with Material Components, which are used for spells or crafting etc.
 *   This is 'Steel', 'Cloth' etc.
 */
sealed trait Material extends EnumEntry
// scalastyle:off number.of.types number.of.methods.in.type
/**
 * Companion Enumeration for [[io.truthencode.ddo.model.misc.Material]]
 */
object Material extends SmartEnum[Material] {
  val values = findValues
  case object Adamantine extends Material
  case object Blueshine extends Material
  case object Bone extends Material
  case object Byeshk extends Material
  case object Cloth extends Material
  case object ColdIron extends Material
  case object Crystal extends Material
  case object Darkleaf extends Material
  case object Darkwood extends Material
  case object Densewood extends Material
  case object DwarvenIron extends Material
  case object Feyleather extends Material
  case object FlametouchedIron extends Material
  case object Flesh extends Material
  case object Force extends Material
  case object Gem extends Material
  case object Glass extends Material
  case object Ice extends Material
  case object Leather extends Material
  case object Light extends Material
  case object Magesteel extends Material
  case object Mithral extends Material
  case object PlaneforgedSteel extends Material
  case object Rust extends Material
  case object Silver extends Material
  case object SpiritcraftLeather extends Material
  case object SpiritforgedIron extends Material
  case object Steel extends Material
  case object Stone extends Material
  case object Wood extends Material

}
// scalastyle:on number.of.types number.of.methods.in.type
