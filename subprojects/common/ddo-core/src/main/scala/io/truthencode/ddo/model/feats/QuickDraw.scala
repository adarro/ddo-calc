/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: QuickDraw.scala
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
 * Icon Feat Quick Draw.png Quick Draw Passive Allows the character to switch weapons and armor
 * faster than they would normally be able to. It also increases the rate of fire of thrown weapons,
 * but not of other ranged weapons.
 *
 * Base Attack Bonus +1
 */
trait QuickDraw
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresBaB
  with FighterBonusFeat with AlchemistBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB: Int = 1
}
