/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WeaponFocusBase.scala
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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/**
 * Weapon Focus Passive Feats Icon Feat Type Description Prerequisites Icon Feat Weapon Focus.png
 * Weapon Focus Passive Provides a +1 bonus to attack rolls with the chosen weapon type Bludgeoning
 * (includes animal form), Piercing, Ranged, Slashing or (Thrown) and +2 stacking Melee Power or
 * Ranged Power. It can be taken multiple times, once for each of the different types.
 *
 * Base Attack Bonus +1
 */
trait WeaponFocusBase
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresBaB
  with FighterBonusFeat {

  self: GeneralFeat =>

  override def requiresBaB: Int = 1
}
