/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfFeat, RequiresBaB}

/** Icon Feat Power Critical.png
  * Power Critical 	Passive 	Provides a +2 bonus to confirm critical hits and +2 bonus to critical hit damage (before multipliers are applied).
  * *
  * Weapon Focus: Any
  * Base Attack Bonus 4
  *
  * */
protected[feats] trait PowerCritical extends FeatRequisiteImpl
  with Passive
  with RequiresBaB
  with RequiresAnyOfFeat
  with FighterBonusFeat {
  self: GeneralFeat =>

  override def requiresBaB: Int = 4

  override def anyOfFeats: Seq[GeneralFeat] = GeneralFeat.weaponFocusAny
}
