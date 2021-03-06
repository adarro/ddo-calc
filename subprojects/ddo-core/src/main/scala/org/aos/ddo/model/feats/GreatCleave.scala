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

import org.aos.ddo.support.requisite._

/**
  * Great Cleave  Active - Special Attack
  * This feat attacks enemies in a wider arc than Cleave, hence Great Cleave has a greater chance to hit more enemies than Cleave.
  * This attack deals +2[W] damage.
  * Cooldown: 6 seconds
  * Usage: Active, Toggled Stance
  * Prerequisites: Cleave
  * Base Attack Bonus +4
  *
  * @todo Flag Toggled Stance
  *
  */
trait GreatCleave extends FeatRequisiteImpl
  with Active
  with RequiresAllOfFeat
  with RequiresBaB
  with FighterBonusFeat {
  self: GeneralFeat =>
  override val allOfFeats = List(GeneralFeat.Cleave)

  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 4
}
