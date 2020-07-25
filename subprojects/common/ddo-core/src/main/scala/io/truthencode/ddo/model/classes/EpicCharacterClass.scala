/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.support.SearchPrefix

import scala.collection.immutable

sealed trait EpicCharacterClass extends CharacterClass with EnumEntry {
  self: Availability =>
}

object EpicCharacterClass extends Enum[EpicCharacterClass] {
  private val generateLevels = {
    (21 to 30).map { x => {
      val y = x - 20
      epicLevel(y)
    }
    }
  }

  override def values: immutable.IndexedSeq[EpicCharacterClass] = generateLevels
}

case class epicLevel(level: Int) extends EpicCharacterClass with FreeToPlayFeature{
  override def entryName: String = s"Level$level"
}