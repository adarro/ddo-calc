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

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Paladin
import org.aos.ddo.support.requisite._

/**
  * [[http://ddowiki.com/page/Remove_Disease]]
  * At 6th level, a paladin can produce a remove disease effect, as the spell, once per rest period.
  * It can use this ability one additional time per rest period for every three levels after 6th (twice per rest at 9th level).
  */
protected[feats] trait RemoveDisease
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Paladin, 6))

}
