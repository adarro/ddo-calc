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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Barbarian, Rogue}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}
import io.truthencode.ddo.support.TraverseOps.Crossable

/**
  * [[http://ddowiki.com/page/Trap_Sense Trap Sense]]
  * This class feat of Barbarians and Rogues grants the following bonuses:
  * +1 on reflex saving throws vs. traps
  * +1 Armor Class vs. trap attacks.
  */
protected[feats] trait TrapSense
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  private def levelMatrix = 3 to 20 by 3
  private def classMatrix = List(Barbarian,Rogue)
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = (classMatrix cross levelMatrix).toSeq
}
