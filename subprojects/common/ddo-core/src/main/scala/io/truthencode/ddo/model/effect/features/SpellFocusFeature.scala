/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpellFocusFeature.scala
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
package io.truthencode.ddo.model.effect.features

import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.schools.School
import io.truthencode.ddo.model.stats.BasicStat
import io.truthencode.ddo.support.naming.UsingSearchPrefix

/**
 * Affects your Maximum Spell Points by the specific amount
 */
trait SpellFocusFeature extends Features {
  self: SourceInfo =>
  protected def spellFocusBonusType: BonusType
  protected val spellFocusDifficultyCheck: Int
  protected val spellSchool: School
  protected def triggerOn: Seq[TriggerEvent]
  protected def triggerOff: Seq[TriggerEvent]
  protected def spellFocusCategories: Seq[effect.EffectCategories.Value]
  private val src = this
  private val spellFocusAmount =
    new PartModifier[Int, BasicStat] with UsingSearchPrefix {

      override protected lazy val partToModify: BasicStat =
        BasicStat.SpellFocus(spellSchool)

      /**
       * The General Description should be just that. This should not include specific values unless
       * all instances will share that value. I.e. a Dodge Effect might state it increases your
       * miss-chance, but omit any value such as 20%. Those values will be displayed in the
       * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
       */
      override val generalDescription: String =
        "Increases your Difficulty Check or chance to land certain spells."

      /**
       * a list of Categories useful for menu / UI placement and also for searching / querying for
       * Miss-Chance or other desired effects.
       *
       * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to
       * enable quick and advanced searching for specific categories from general (Miss-Chance) to
       * specific (evasion). In addition, it may be useful for deep searching such as increasing
       * Spot, which should suggest not only +Spot items, but +Wisdom or eventually include a feat
       * or enhancement that allows the use of some other value as your spot score.
       */
      override def categories: Seq[String] = spellFocusCategories.map(_.toString)

      private val eb = EffectParameterBuilder()
        .toggleOffValue(triggerOff*)
        .toggleOnValue(triggerOn*)
        .addBonusType(spellFocusBonusType)
        .build

      override protected def effectParameters: Seq[ParameterModifier[?]] = eb.modifiers
      override val effectDetail: DetailedEffect = DetailedEffect(
        id = "SpellPoints",
        description = "Increases your total spell points",
        triggersOn = triggerOn.map(_.entryName),
        triggersOff = triggerOff.map(_.entryName),
        bonusType = spellFocusBonusType.entryName
      )
      override val source: SourceInfo = src
      override lazy val value: Int = spellFocusDifficultyCheck
      override lazy val effectText: Option[String] = Some(s"Spell Focus $spellSchool by $value")

      /**
       * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
       * qualified "Race:HalfElf"
       *
       * @return
       *   A default or applied prefix
       */
      override def searchPrefixSource: String = partToModify.searchPrefixSource
    }

  abstract override def features: Seq[Feature[?]] = {
    assert(spellFocusAmount.value == spellFocusDifficultyCheck)
    super.features :+ spellFocusAmount
  }

}
