/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BonusTypeTest.scala
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
package io.truthencode.ddo.enhancement

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.slots.WearLocation
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableFor1
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import scala.collection.immutable.HashMap

class BonusTypeTest
  extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks with LazyLogging {
  // https://ddowiki.com/page/Category:Mythic_Boost_items
  val boosts: Seq[String] = List(
    "Armor",
    "Belt",
    "Boot",
    "Cloak",
    "Goggle",
    "Hands",
    "Head",
    "Weapon",
    "Neck",
    "Ring",
    "Shield",
    "Trinket",
    "Wrist").map { s => s"Mythic $s Boost" }
  val expectedMythicBoosts: TableFor1[String] = Table("Boost_Type", boosts*)
  describe("Mythic Boosts") {
    /*
     * Weapons, belts, gloves, goggles, rings, and trinkets grant Mythic bonus to Melee, Ranged, and Universal Spell Power.
     * Armor, boots, bracers, cloaks, headwear, necklaces, shields grant Mythic bonus to Physical and Magical Resistance Rating.
     * Orbs, rune arms, and collars can appear Shield and/or Weapon boost.
     * (Some) ToEE items can appear with two mythic bonuses, e.g., Weapon and Shield.
     */
    they("Should apply to a specific set of item slots") {
      // TODO: Mythic Boost validation Dynamically / programmatically link location to boost.
      // Some ToEE items can have both Weapon and Shield bonuses.
      // bonus constrained between 1 to 3 and 2 to 4 depending on level and item type.
      val nameMap = HashMap(
        WearLocation.MainHand -> "Weapon",
        WearLocation.OffHand -> "Shield",
        WearLocation.Belt -> "Belt",
        WearLocation.Gloves -> "Hands",
        WearLocation.Goggles -> "Goggle",
        WearLocation.FirstFinger -> "Ring",
        WearLocation.SecondFinger -> "Ring",
        WearLocation.Trinket -> "Trinket",
        WearLocation.Body -> "Armor",
        WearLocation.Feet -> "Boot",
        WearLocation.Wrist -> "Wrist",
        WearLocation.Back -> "Cloak",
        WearLocation.Head -> "Head",
        WearLocation.Neck -> "Neck"
      )
      val boostsFromWearLocation = nameMap.map { n => s"Mythic ${n._2} Boost" }
      logger.warn(s"bootsFromWear head ${boostsFromWearLocation.head} ")
      forAll(expectedMythicBoosts) { (b: String) =>
        logger.warn(s"testing $b")
        boostsFromWearLocation should contain(b)

      }

    }

  }
}
