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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB}

/**
  * Icon Feat Whirlwind Attack.png
  * Whirlwind Attack 	Active - Special Attack 	This feat attacks all enemies in a 360 degree arc around the character. This attack deals +4[W] damage.
  *
  * Dodge, Mobility, Spring Attack
  * Combat Expertise, Base Attack Bonus +4
  *
  * @note Only Combat Expertise and Spring Attack are required as feats. However, Spring Attack depends on Mobility with Depends on Dodge
  *       and are listed on the Wiki as such. Nested dependencies should be inferred and this list may remove all but explicit dependencies.
  */
protected[feats] trait WhirlwindAttack extends FeatRequisiteImpl
  with Active
  with RequiresAllOfFeat
  with RequiresBaB
  with FighterBonusFeat {
  self: GeneralFeat =>
  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 4

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.Dodge, GeneralFeat.Mobility, GeneralFeat.SpringAttack, GeneralFeat.CombatExpertise)
}
