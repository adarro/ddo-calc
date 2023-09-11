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
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect.EffectPart.Feat
import io.truthencode.ddo.model.feats.{Feat => Feats}
import io.truthencode.ddo.model.stats.BasicStat
import io.truthencode.ddo.support.dice.{DamageDice, DamageInfo}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
// import io.truthencode.ddo.model.effect.EffectParameter.{DifficultyCheck, Magnitude}

class EffTest extends AnyFunSpec with Matchers {
  describe("An Effect (EFF)") {
    it("should not need a description") {
      val preStar = BasicStat.values
      //  typical random loot such as Warrior's boots of X
      val featList = Feats.values.collect(Feats.fnTacticalFeats)
      val randomLootWarriorPrefix =
        for { s <- featList } yield Eff(
          TriggerEvent.Passive,
          bonusType = BonusType.Enhancement,
          magnitude = new Magnitude {

            /**
             * Percent chance to occur
             */
            override val chance: Int = 4

            /**
             * Damage Dice
             */
            override val damage: DamageDice = DamageInfo("2d1")

            /**
             * Base Price Modifier
             */
            override val bpm: Int = 3
          },
          difficultyCheck = None,
          target = Feat(s)
        )
    }
    ignore("should support things such as Combat Mastery") {
      /* https://ddowiki.com/page/Combat_Mastery
     Combat Mastery is an item enchantment that increases the DC to resist the character's tactical feats, such as Trip or Stunning Fist.

     Random loot

    Enhancement bonus is available on randomly generated armor / boots / shields with the prefix Warrior's.
    Insightful bonus is available on randomly generated armor / gloves with the suffix of Combat Mastery.
    Named loot

    Quality bonus: C:Quality Combat Mastery items
       */
      // typical random loot such as Warrior's boots of X
//      val featList = Feats.values collect Feats.fnTacticalFeats
//        val randomLootWarriorPrefix = for {s <- featList} yield Eff(
//          TriggerEvent.Passive,
//          bonusType = BonusType.Enhancement,
//          magnitude = 5,
//          difficultyCheck = None,
//            target = Feat(s)
//      )

    }
  }
}
