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
package io.truthencode.ddo.model.character

import enumeratum.EnumEntry
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.race.{IconicClass, Race}

sealed trait PastLife extends EnumEntry {

  /**
   * Number of times this has been acquired.
   * @note
   *   Past life feats apply a maximum of 3 times, so timesAcuired will return the lesser of times
   *   acquired or 3
   */
  val timesAcquired: Int
}

trait RacialPastLife extends PastLife {
  val pastRace: Race
}

trait IconicPastLife extends PastLife {
  val pastIconClass: IconicClass
}

trait EpicPastLife extends PastLife {
  // TODO: Revisit EpicPastLife trait post U51 to determine needed qualifiers, if any.
  // May just need Any PL now.
  // val pastEpicClass : EpicCharacterClass
}

trait HeroicPastLife extends PastLife {
  val pastHeroicClass: HeroicCharacterClass
}
