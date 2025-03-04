/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: LayOnHands.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Paladin
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.*

/**
 * [[http://ddowiki.com/page/Lay_on_Hands Lay On Hands]] The Paladin ability Lay on Hands heals a
 * friendly target for ( 10 + Paladin level ) x Charisma modifier amount of hit points, or deals
 * that much damage to an undead target. The amount healed does not suffer a penalty when used on
 * Warforged (that is, it heals them for 100% of its total). This ability does not require the
 * paladin to be within melee (touch) range of the friend or foe being targeted, but line of sight
 * is required. This Paladin feat is granted at level 2, and may be used once per rest. The Extra
 * Lay on Hands enhancements may be taken at level 1 and above to increase the number of uses per
 * rest.
 *
 * Notes This can also be acquired and enhanced through the Unyielding Sentinel Epic Destiny without
 * requiring any Paladin levels.
 */
protected[feats] trait LayOnHands
  extends FeatRequisiteImpl with TriggeredActivationImpl with Passive with ActiveFeat
  with AtWillEvent with GrantsToClass with FreeFeat with DefaultCoolDown { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 2))

}
