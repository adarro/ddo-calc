/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedCriticalBase.scala
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
 * Usage: Passive Prerequisite: Base attack bonus of 8 or higher Description This feat adds 1, 2, or
 * 3 to critical threat range based on the weapon type's unmodified threat range.
 *
 * +3 for falchion, great crossbow, kukri, rapier, and scimitar. +2 for bastard sword, dagger,
 * greatsword, heavy crossbow, khopesh, light crossbow, long sword, repeating heavy crossbow,
 * repeating light crossbow, short sword, and throwing dagger. +1 to all other weapons. Improved
 * Critical does not stack with the Keen or Impact weapon enchantments.
 *
 * Multiple versions of this feat exist, one for each weapon type (Bludgeoning, Piercing, Ranged,
 * Slashing or Thrown). This feat can be taken multiple times, though each time must be for a
 * different type. Shields do not benefit from Improved Critical. Notes Artificers may select this
 * feat as one of their artificer bonus feats. Fighters may select this feat as one of their fighter
 * bonus feats. For artificers taking it as a bonus feat, it is restricted to bludgeoning, ranged,
 * and slashing.
 *
 * @note
 *   Not Implemented?: Artificers are restricted to Bludgeon / ranged / slash.
 */
trait ImprovedCriticalBase
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresBaB
  with FighterBonusFeat with ArtificerBonusFeat {

  self: GeneralFeat =>

  override def requiresBaB: Int = 8
}
