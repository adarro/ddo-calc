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

import java.time.Duration

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/** Icon Feat Resilience.png
  * [[https://ddowiki.com/page/Resilience Resilience]]
  * Active - Defensive Combat Stance You gain a +4 to all saving throws.
  * Spells have three times their normal cooldown when this mode is active.
  *
  * Constitution 13
  * Base Attack Bonus +1
  * @todo Adds 3x spell cooldown timers
  */
protected[feats] trait Resilience
    extends FeatRequisiteImpl
    with ActiveFeat
    with DefensiveCombatStance
    with RequiresAttribute
    with RequiresBaB
    with FighterBonusFeat
    with MartialArtsFeat {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Constitution, 13))

    override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))

    override def requiresBaB: Int = 1
}
