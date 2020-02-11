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
import org.aos.ddo.model.classes.HeroicCharacterClass.Monk
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
  * You can avoid even magical and unusual attacks with great agility.
  * When you attempt a Reflex save against an effect that normally does half damage when saved against,
  * you suffer no damage if you successfully save and half damage even if you fail.
  * Improved Evasion can be used only if the character is wearing light armor or no armor,
  * is not wielding a heavy shield or tower shield, and is not heavily encumbered.
  * A helpless character does not gain the benefit of improved evasion.
  */
trait ImprovedEvasion
  extends FeatRequisiteImpl
    with Passive
    with GrantsToClass with RogueOptionalAbility
    with FreeFeat {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = rogueOptionMatrix :+ (Monk, 9)


}
