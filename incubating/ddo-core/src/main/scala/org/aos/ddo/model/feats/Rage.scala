/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Barbarian
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
  * [[http://ddowiki.com/page/Rage_(feat) Rage]]
  * A Barbarian can fly into a Barbarian Rage a certain number of times per day (Shrine).
  * While raged, a Barbarian temporarily gains a +4 bonus to Strength, a +4 bonus to Constitution, and a +2 morale bonus on Will saves, but they suffer a –2 penalty to Armor Class.
  * When the rage ends, however, you will lose the hit points granted to you by increased Constitution score and become fatigued
  * (–2 penalty to Strength, –2 penalty to Dexterity, -10% penalty to movement speed) for 60 seconds.
  * This penalty is removed later on by the Tireless Rage feat (granted at Barbarian level 17).
  * Also note the Warforged Race is immune to fatigue - including Barbarian fatigue, from level 1.
  * The fatigue may also be removed normally by standard means such as drinking a potion of lesser restoration.
  * You can prematurely end your rage by using the Dismiss Rage feat.
  * While raging, a barbarian cannot use any Intelligence-based skills or the Concentration skill,
  * nor can he cast spells or activate magic items that require a command word, a spell trigger (such as a wand or clicky), or spell completion (such as a scroll) to function.
  * He can use any feat he has except Defensive Fighting and Combat Expertise.
  *
  * A Barbarian also cannot drink any of the following potions while raged: Remove Curse, Remove Fear, Remove Blindness, Remove Disease unless they are the non-clicky types.
  * Generally ones purchased from a guild vendor are non-clicky. The only clicky-type potion that can be drunk while raged is lesser restore.
  * The duration of Rage is 18 + (Constitution modifier x 6) seconds. The rage-enhanced Constitution score is used for this calculation.
  * The in-game text description of "30 seconds plus an additional amount of time based on your Constitution" is incorrect.
  */
protected[feats] trait Rage
  extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Barbarian, 1))
}