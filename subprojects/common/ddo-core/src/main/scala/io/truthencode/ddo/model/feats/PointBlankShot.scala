/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: PointBlankShot.scala
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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * Icon Feat Point Blank Shot.png Point Blank Shot Passive Grants a +1 bonus to hit within 15
 * meters, and your ranged weapons deal +1[W]. (A weapon that deals 1d6 damage per hit will deal 2d6
 * damage per hit instead, while a weapon that deals 2d8 damage per hit will deal 4d8 damage per hit
 * instead. This affects the base dice of the associated weapon any time they are rolled, including
 * critical hits.)
 *
 * None
 */
protected[feats] trait PointBlankShot
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with FreeFeat
  with FighterBonusFeat with ArtificerBonusFeat with AlchemistBonusFeat {
  self: GeneralFeat =>
}
