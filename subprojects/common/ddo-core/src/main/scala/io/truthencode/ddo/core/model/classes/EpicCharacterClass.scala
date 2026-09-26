/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EpicCharacterClass.scala
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
package io.truthencode.ddo.model.classes

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.misc.{Availability, FreeToPlayFeature}

import scala.collection.immutable

/**
 * And epic character has achieved level 20 and can now enter Epic Quests. This also unlocks
 * Destinies.
 */
sealed trait EpicCharacterClass extends CharacterClass with EnumEntry {
  self: Availability =>
}

/**
 * Represents character's between 20 and 30
 */
object EpicCharacterClass extends Enum[EpicCharacterClass] {
  private val generateLevels = {
    (21 to 30).map { x =>
      {
        val y = x - 20
        EpicLevel(y)
      }
    }
  }

  override def values: immutable.IndexedSeq[EpicCharacterClass] = generateLevels
}

/**
 * Represents an Epic level.
 * @param level
 */
case class EpicLevel(level: Int) extends EpicCharacterClass with FreeToPlayFeature {
  override def entryName: String = s"Level$level"
}
