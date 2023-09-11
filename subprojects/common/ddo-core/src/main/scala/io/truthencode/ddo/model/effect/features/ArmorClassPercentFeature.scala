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
package io.truthencode.ddo.model.effect.features

import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.stats.{BasicStat, MissChance}
import io.truthencode.ddo.support.naming.UsingSearchPrefix

/**
 * Affects armor class by the specified percentage
 */
trait ArmorClassPercentFeature extends Features {
  self: SourceInfo =>
  protected val armorBonusType: BonusType
  protected val armorBonusAmount: Int
  protected[this] val triggerOn: Seq[TriggerEvent]
  protected[this] val triggerOff: Seq[TriggerEvent]
  private val src = this
  private[this] val armorChance =
    new PartModifier[Int, BasicStat with MissChance] with UsingSearchPrefix {

      override protected[this] lazy val partToModify: BasicStat with MissChance =
        BasicStat.ArmorClass

      private val eb = EffectParameterBuilder()
        .toggleOffValue(triggerOff: _*)
        .toggleOnValue(triggerOn: _*)
        .addBonusType(armorBonusType)
        .build

      override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers

      /**
       * The General Description should be just that. This should not include specific values unless
       * all instances will share that value. I.e. a Dodge Effect might state it increases your
       * miss-chance, but omit any value such as 20%. Those values will be displayed in the
       * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
       */
      override val generalDescription: String = "Increases your Armor Class"
      override val categories: Seq[String] = Seq(EffectCategories.MissChance).map(_.toString)
      override lazy val effectDetail: DetailedEffect = DetailedEffect(
        id = "ArmorClass",
        description = "Improves your Armor Class",
        triggersOn = triggerOn.map(_.entryName),
        triggersOff = triggerOff.map(_.entryName),
        bonusType = armorBonusType.toString
      )

      /**
       * The main name of the effect.
       *
       * Naming conventions The name should be concisely non-specific. i.e. Prefer "ArmorClass"
       * instead of "Deflection" or "Miss-Chance" Deflection is too specific as there are several
       * stacking and non-stacking types (Natural Armor, Shield) that all contribute to your
       * specific goal of increasing your armor class. Miss-Chance is to vague as it encompasses
       * everything from incorporeal, dodge, armor class, arrow-deflection etc.
       */
      override lazy val name: String = "ArmorClass"

      override val source: SourceInfo = src
      override lazy val value: Int = armorBonusAmount
      override lazy val effectText: Option[String] = Some(s"Armor Class by $value%")

      /**
       * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
       * qualified "Race:HalfElf"
       *
       * @return
       *   A default or applied prefix
       */
      override def searchPrefixSource: String = partToModify.searchPrefixSource
    }

  abstract override def features: Seq[Feature[_]] = {
    assert(armorChance.value == armorBonusAmount)
    super.features :+ armorChance
  }

}
