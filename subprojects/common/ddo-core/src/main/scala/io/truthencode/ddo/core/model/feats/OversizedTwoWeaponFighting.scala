/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: OversizedTwoWeaponFighting.scala
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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute,
  RequiresAllOfFeat
}

/**
 * Icon Feat Oversized Two Weapon Fighting.png Oversized Two Weapon Fighting Passive When wielding a
 * one handed weapon in your off-hand, you take penalties for fighting with two weapons as if you
 * were wielding a light weapon on your offhand.
 *
 * Two Weapon Fighting Strength 12
 */
trait OversizedTwoWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresAllOfFeat
  with AttributeRequisiteImpl with RequiresAllOfAttribute with FighterBonusFeat {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Strength, 12))

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.TwoWeaponFighting)
}
