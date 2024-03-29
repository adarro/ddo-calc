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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Barbarian
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
 * [[http://ddowiki.com/page/Sneak_Attack Sneak Attack]] Rogues (or any other character with a
 * source of sneak attack damage) may do additional damage when attacking an opponent if one of the
 * following conditions is met:
 *
 * The target is unaware of the character's presence. The target is attacking another player
 * (possibly under the influence of Intimidate from that player or Diplomacy from the passive
 * character). The target is under the effects of a successful Bluff. The target is helpless. The
 * target is blind. The target is under the effects of a Deception item or attack.
 *
 * @todo
 *   Need to apply Rogue Auto Grant
 */
protected[feats] trait DismissRage
  extends FeatRequisiteImpl with Passive with GrantsToClass with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Barbarian, 1))
}
