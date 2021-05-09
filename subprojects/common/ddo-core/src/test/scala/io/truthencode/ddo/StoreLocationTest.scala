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
package io.truthencode.ddo

import org.scalatest.{FunSpec, Matchers}

class StoreLocationTest extends FunSpec with Matchers {
  describe("Wearable Items") {
    they("should have a designated Equipment Slot as a location") {
      val eq = StoreLocation.Equipment
      eq should not be empty
      val eqFromLocations = eq.map(_.wearLocation)

      eqFromLocations shouldEqual WearLocation.values
    }
  }

  describe("Colored Augment Slots") {
    they("should include Red Augment Slots") {
      val aug = StoreLocation.Augments
      aug.map(_.generalAugment).contains(AugmentLocation.RedAugmentSlot)
    }
  }
}
