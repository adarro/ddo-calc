/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: MagicalTraining.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass._
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{
  FeaturesImpl,
  SpellCriticalPercentFeature,
  SpellPointAmountFeature
}
import io.truthencode.ddo.model.spells.SpellPower
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
 * This feat increases the maximum spell points by 80, increases the spell critical chance by 5% and
 * grants Echoes of Power if the spell points pool drops below 12.
 *
 * @note
 *   granted Level 1 : Cleric, Druid, Favored Soul, Artificer, Sorcerer, Wizard, Warlock granted via
 *   enhancement for: Bard - third rank of Magical Studies from Spellsinger enhancements Ranger -
 *   third rank of Energy of the Wild from Arcane Archer enhancements
 *
 * Any other class as a trainable feat
 */
protected[feats] trait MagicalTraining
  extends FeatRequisiteImpl with Passive with FreeFeat with ClassRequisiteImpl with GrantsToClass
  with FeaturesImpl with SpellPointAmountFeature with SpellCriticalPercentFeature {
  self: GeneralFeat =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  override protected lazy val spellCriticalCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.SpellCasting)
  // TODO: Need to add Echoes of Power effect (Magical Training etc.) [Low Priority]
  override protected val spellPointBonusType: BonusType = BonusType.Feat
  override protected val spellPointBonusAmount: Int = 80
  override protected val spellPointAmountCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.SpellCasting)
  override protected val spellCriticalBonusType: BonusType = BonusType.Feat
  override protected val schoolCritical: Seq[(SpellPower, Int)] =
    SpellPower.values.map((_, 5)).to(LazyList)
  val g: Seq[(SpellPower, Int)] = SpellPower.values.map((_, 5))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    magicClasses.map((_, 1))

  private def magicClasses =
    List(Cleric, Druid, FavoredSoul, Artificer, Sorcerer, Wizard, Warlock, Alchemist)
}
