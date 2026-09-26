/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedTwoHandedFighting.scala
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
import io.truthencode.ddo.support.requisite._

/**
 * Icon Feat Improved Two Handed Fighting.png Improved Two Handed Fighting Passive Increases the
 * damage of glancing blow attacks when wielding a two-handed weapon by an additional 10%. Also
 * increases the chance for weapon effects to trigger on glancing blows by an additional 3% (6%) and
 * an additional +2 Combat Style bonus to Melee Power (total of +4).
 *
 * Two Handed Fighting Strength 17 Base Attack Bonus +6
 */
trait ImprovedTwoHandedFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresAllOfFeat
  with AttributeRequisiteImpl with RequiresAllOfAttribute with RequiresBaB with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Strength, 17))

  override def requiresBaB: Int = 6

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.TwoHandedFighting)
}
