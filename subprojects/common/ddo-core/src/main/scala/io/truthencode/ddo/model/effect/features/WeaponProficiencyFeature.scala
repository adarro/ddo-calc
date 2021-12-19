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
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.item.weapon.WeaponCategory
import io.truthencode.ddo.model.stats.BasicStat
import io.truthencode.ddo.support.naming.UsingSearchPrefix

/**
 * Increases your Weapons Critical Threat range Different Weapon types have greater or lesser
 * bonuses and it may or may not apply to shields, depending on the feat / enhancement.
 */
trait WeaponProficiencyFeature extends Features {
  self: SourceInfo =>

  protected val proficiencyType: BonusType
  protected val proficiencyAmount: Seq[WeaponCategory]
  private val src = this
  protected[this] val triggerOn: Seq[TriggerEvent]
  protected[this] val triggerOff: Seq[TriggerEvent]
  protected[this] val weaponProficiencyCategories: Seq[effect.EffectCategories.Value]

  private[this] val proficiencyChance =
    new PartModifier[Seq[WeaponCategory], BasicStat] with UsingSearchPrefix {

      /**
       * The General Description should be just that. This should not include specific values unless
       * all instances will share that value. I.e. a Dodge Effect might state it increases your
       * miss-chance, but omit any value such as 20%. Those values will be displayed in the
       * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
       */
      override val generalDescription: String = "Reduce To Hit penalty when using specific weapons"

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
      override def categories: Seq[String] = weaponProficiencyCategories.map(_.toString)

      /**
       * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
       * qualified "Race:HalfElf"
       *
       * @return
       *   A default or applied prefix
       */
      override def searchPrefixSource: String = partToModify.searchPrefixSource

      override protected[this] lazy val partToModify: BasicStat =
        BasicStat.WeaponProficiency

      private val eb = EffectParameterBuilder()
        .toggleOffValue(triggerOff: _*)
        .toggleOnValue(triggerOn: _*)
        .addBonusType(proficiencyType)
        .build

      override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers
      override val effectDetail: DetailedEffect = DetailedEffect(
        id = "WeaponProficiency",
        description = "Reduce To Hit penalty when using specific weapons",
        triggersOn = triggerOn.map(_.entryName),
        triggersOff = triggerOff.map(_.entryName)
      )
      override val source: SourceInfo = src
      override lazy val value: Seq[WeaponCategory] = proficiencyAmount
      override lazy val effectText: Option[String] = Some(s"Weapon Proficiency: $value%")
    }

  abstract override def features: Seq[Feature[_]] = {
    assert(proficiencyChance.value == proficiencyAmount)
    super.features :+ proficiencyChance
  }

}
