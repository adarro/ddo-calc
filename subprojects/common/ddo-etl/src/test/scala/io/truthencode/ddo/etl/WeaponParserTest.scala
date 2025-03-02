/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WeaponParserTest.scala
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
package io.truthencode.ddo.etl

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.io.BufferedSource

class WeaponParserTest extends AnyFunSpec with Matchers {
  def loadData: BufferedSource = {
    scala.io.Source.fromResource(s"data/weapons/item_weapons_great_clubs.json")
  }

  val exampleWeapon: String =
    """
      |{
      |   "weapon": [
      |    {
      |     "name": "Thrall of the Fire Temple",
      |     "damangeAndType": "Handwrap",
      |     "minLevel": "7",
      |     "binding": "Unbound",
      |     "quest": "Temple of Elemental Evil Part One, and Temple of Elemental Evil Part Two end chests, rare encounter chests, pyramid chest",
      |     "criticalProfile": "Bludgeoning",
      |     "enchantments": "+3 Enhancement Bonus",
      |     "enchantments_url": "https://ddowiki.com/page/Enhancement_bonus",
      |     "errata": "Bludgeoning",
      |     "weaponDetail": [
      |      {
      |       "proficiency": "Simple Weapon Proficiency",
      |       "name": "Thrall of the Fire Temple",
      |       "damangeAndType": "Handwrap",
      |       "criticalThreatRange": "20 / x2",
      |       "weaponType": [
      |        {
      |         "weaponCategory": "Collar",
      |         "damageCategory": "/ Bludgeoning weapons"
      |        }
      |       ],
      |       "absRaceRequired": "Pet",
      |       "minLevel": "7",
      |       "requiredTrait": "None",
      |       "umd": "No UMD needed",
      |       "handedness": "",
      |       "attackMod": "STR",
      |       "damageMod": "STR",
      |       "binding": "Unbound",
      |       "binding_url": "https://ddowiki.com/page/Bind",
      |       "durability": "90",
      |       "material": "Silver",
      |       "material_url": "https://ddowiki.com/page/Silver",
      |       "hardness": "8",
      |       "baseValue": "2,803pp",
      |       "weight": "0.5 lbs",
      |       "upgradable": "Not upgradeable",
      |       "description": "\"There are benefits to submitting to the path of flame.\" ~Alrrem",
      |       "enchantments": [
      |        {
      |         "enchantment": [
      |          {
      |           "name": "+3 Enhancement Bonus",
      |           "name_url": "https://ddowiki.com/page/Enhancement_bonus",
      |           "fullText": "+3 Enhancement Bonus"
      |          }
      |         ]
      |        },
      |        {
      |         "enchantment": [
      |          {
      |           "name": "Fire Vulnerability",
      |           "name_url": "https://ddowiki.com/page/Vulnerable",
      |           "fullText": "Fire Vulnerability"
      |          }
      |         ]
      |        },
      |        {
      |         "enchantment": [
      |          {
      |           "name": "Fire Guard II",
      |           "name_url": "https://ddowiki.com/page/Fire_Guard",
      |           "fullText": "Fire Guard II"
      |          }
      |         ]
      |        },
      |        {
      |         "enchantment": [
      |          {
      |           "name": "Flaming II",
      |           "name_url": "https://ddowiki.com/page/Elemental_(weapon)#Flaming",
      |           "fullText": "Flaming II"
      |          }
      |         ]
      |        },
      |        {
      |         "enchantment": [
      |          {
      |           "name": "Fire Resistance +10",
      |           "name_url": "https://ddowiki.com/page/Fire_Resistance",
      |           "fullText": "Fire Resistance +10"
      |          }
      |         ]
      |        },
      |        {
      |         "enchantment": [
      |          {
      |           "name": "Mythic Weapon Boost",
      |           "name_url": "https://ddowiki.com/page/Mythic_Boost",
      |           "fullText": "Mythic Weapon Boost +2 or +4"
      |          }
      |         ]
      |        }
      |       ],
      |       "locationInformation": [
      |        {
      |         "location": "Temple of Elemental Evil Part Two",
      |         "location_url": "https://ddowiki.com/page/Temple_of_Elemental_Evil_Part_Two",
      |         "locationExtra": ", and"
      |        }
      |       ]
      |      }
      |     ]
      |    }
      |   ]
      |  }
      |""".stripMargin

  describe("A weapon in json format ") {
    it("should work") {
      val data = loadData
      data shouldNot have size 0
    }
  }

}
