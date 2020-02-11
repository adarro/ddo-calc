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
package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.Ranger
import org.aos.ddo.support.requisite._

/**
  * Icon Feat Many Shot.png
  * Manyshot
  * Active - Ability
  * For the next 20 seconds, add your base attack bonus * 4 to your Doubleshot and Ranged power.
  * This ability puts Ten Thousand Stars on a 30 second cooldown.
  *
  * Cooldown: 2 minutes.
  *
  * Point Blank Shot, Rapid Shot
  * Dexterity 17, Base Attack Bonus +6
  *
  * @todo add 30 second Cooldown
  * @todo add 20 second Active
  */
protected[feats] trait Manyshot
  extends FeatRequisiteImpl
    with Active
    with RequiresAllOfFeat
    with RequiresAttribute
    with RequiresBaB
    with ClassRequisiteImpl
    with GrantsToClass
    with FighterBonusFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PointBlankShot, GeneralFeat.RapidShot)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 6

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 17))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 6))
}
