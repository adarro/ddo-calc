/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Dodge.scala
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
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{DodgeChanceFeature, FeaturesImpl}
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute
}

/**
 * Usage: Passive Prerequisite: Dexterity 13+ Description This feat grants a 3% Dodge bonus. *
 *
 * A Fighter may select this feat as one of his fighter bonus feats. A Monk may select this feat as
 * one of his martial arts feats. An Alchemist may select this feat as one of his fighter bonus
 * feats.
 */
trait Dodge
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive
  with AttributeRequisiteImpl with RequiresAllOfAttribute with FighterBonusFeat
  with AlchemistBonusFeat with MartialArtsFeat with FeaturesImpl with DodgeChanceFeature {
  self: GeneralFeat =>

  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val dodgeCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.MissChance)
  override val dodgeBonusType: BonusType = BonusType.Feat
  override val dodgeBonusAmount: Int = 3

  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def gkBonusSelectableClasses: String = "BonusSelection"
}
