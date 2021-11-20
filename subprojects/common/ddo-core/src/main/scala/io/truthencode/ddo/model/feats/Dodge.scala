/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.effect.features.{DodgeChanceFeature, FeaturesImpl}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  *
  * Usage: Passive
  * Prerequisite: Dexterity 13+
  * Description
  * This feat grants a 3% Dodge bonus.
  * *
  *
  * @todo A Fighter may select this feat as one of his fighter bonus feats.
  * @todo A Monk may select this feat as one of his martial arts feats.
  */
trait Dodge
    extends FeatRequisiteImpl
    with Passive
    with RequiresAttribute
    with FighterBonusFeat
    with AlchemistBonusFeat
    with MartialArtsFeat
    with FeaturesImpl
    with DodgeChanceFeature {
  self: GeneralFeat =>

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override val dodgeBonusType: BonusType = BonusType.Feat
  override val dodgeBonusAmount: Int = 3
}