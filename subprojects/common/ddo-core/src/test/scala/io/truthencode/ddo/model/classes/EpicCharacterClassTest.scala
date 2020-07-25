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

import enumeratum.EnumEntry
import org.scalatest.{FunSpec, Matchers}

class EpicCharacterClassTest extends FunSpec with Matchers{
  describe("An Epic Character Class") {
    it("Has values from 1 to 10 representing effective level 21 to 30") {
      EpicCharacterClass.values.foreach{x :EnumEntry=>
        x.entryName should startWith ("Level")
        x.entryName should fullyMatch regex "Level\\d+"
      }
    }
  }

}
