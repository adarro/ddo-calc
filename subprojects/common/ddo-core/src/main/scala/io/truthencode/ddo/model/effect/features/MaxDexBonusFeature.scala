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

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.{
  DetailedEffect,
  Feature,
  ParameterModifier,
  PartModifier,
  SourceInfo,
  TriggerEvent
}
import io.truthencode.ddo.model.stats.BasicStat

/**
 * Increases your Max Dexterity Bonus (MDB) on armor.
 */
trait MaxDexBonusFeature extends Features {
  self: SourceInfo =>
  val mdbBonusType: BonusType
  val mdbAmount: Int
  private val src = this
  protected[this] val triggerOn: TriggerEvent
  protected[this] val triggerOff: TriggerEvent
  protected[this] val categories: Seq[effect.EffectCategories.Value]
  private[this] val maxDexterityBonus =
    new PartModifier[Int, BasicStat] with ParameterModifier[Int, BonusType] {

      lazy override protected[this] val partToModify: BasicStat =
        BasicStat.MaxDexterityBonus

      lazy override protected[this] val parameterToModify: BonusType =
        mdbBonusType
      lazy override val effectDetail: DetailedEffect = DetailedEffect(
        id = "HitChance",
        description = "Increases your Max Dexterity Bonus",
        categories = categories.map(_.toString),
        triggersOn = triggerOn.entryName,
        triggersOff = triggerOff.entryName
      )
      override val source: SourceInfo = src
      override lazy val value: Int = mdbAmount
      override lazy val effectText: Option[String] = Some(s"Max Dexterity Bonus by $value")
    }

  abstract override def features: Seq[Feature[_]] = {
    assert(maxDexterityBonus.value == mdbAmount)
    super.features :+ maxDexterityBonus
  }

}
