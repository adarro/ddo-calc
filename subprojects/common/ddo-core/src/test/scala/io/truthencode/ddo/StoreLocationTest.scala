/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StoreLocationTest.scala
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

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.slots.WearLocation
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class StoreLocationTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("Basic Store Locations") {
    they("Should be inside an enum") {
      StoreLocation.values should not be empty
    }
    it("should have one for Equipment") {
      StoreLocation.values.foreach { v =>
        logger.info(s"entryName: ${v.entryName}")
      }
//      StoreLocation.Equipment should not be empty
    }
  }
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
      val aug = StoreLocation.ChromaticAugments
      aug.map(_.generalAugment).contains(AugmentLocation.RedAugmentSlot)
      StoreLocation.values.contains(AugmentLocation.RedAugmentSlot)
    }
  }

  describe("Celestial Augment Slots") {
    they("should include Solar and Lunar Augment Slots") {
      val aug = StoreLocation.CelestialAugments
      aug.map(_.celestialAugment).contains(AugmentLocation.SunAugmentSlot)
    }
  }
}
