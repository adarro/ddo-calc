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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass._
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
 * Icon Feat Light Armor Proficiency.png Light Armor Proficiency Passive You are proficient with
 * light armor, and do not suffer armor penalties to your attack rolls when wearing light armor. You
 * also gain 2 + 1/2 of your base attack bonus in physical resistance when wearing light armor.
 *
 * None
 */
protected[feats] trait LightArmorProficiency
  extends FeatRequisiteImpl with ClassRequisiteImpl with GrantsToClass with Passive with FreeFeat {
  self: GeneralFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = firstLevelClasses

  private def firstLevelClasses =
    List(Barbarian, Bard, Cleric, FavoredSoul, Fighter, Paladin, Ranger, Rogue, Warlock)
      .map((_, 1))

}
