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

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.{Barbarian, Rogue}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Improved_Uncanny_Dodge Improved Uncanny Dodge]]
  * This feat grants you a 1% passive bonus to Dodge at levels 4, 6, 8, 12, 16, and 20.
  * Also, you can activate this ability to gain a temporary 50% dodge bonus and a +6 reflex save bonus.
  * As of Update 14, using this ability is no longer restricted by number of uses per rest. However, it is restricted by cooldown.
  *
  * @note This feat effectively replaces [[UncannyDodge]] if the character has both.
  *       i.e. Does not stack, but improves the Dodge feat with an improved Active ability.
  */
protected[feats] trait ImprovedUncannyDodge
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Barbarian, 8), (Rogue, 8))
}
