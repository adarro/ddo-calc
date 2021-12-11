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
import io.truthencode.ddo.model.item.weapon.{WeaponCategory, WeaponClass}
import io.truthencode.ddo.model.stats.BasicStat

/**
 * Increases your Weapons Critical Threat range Different Weapon types have greater or lesser bonuses and it may or may
 * not apply to shields, depending on the feat / enhancement.
 */
trait ToDamageByWeaponClassFeature extends Features {
  self: SourceInfo =>
  protected val toDamageType: BonusType
  protected val toDamageAmount: Seq[(WeaponCategory, Int)]
  private val src = this
  protected[this] val triggerOn: TriggerEvent
  protected[this] val triggerOff: TriggerEvent
  protected[this] val categories: Seq[effect.EffectCategories.Value]

  private[this] val toDamageChance =
    new PartModifier[Seq[(WeaponCategory, Int)], BasicStat]
      with ParameterModifier[Seq[(WeaponCategory, Int)], BonusType] {

      lazy override protected[this] val partToModify: BasicStat =
        BasicStat.ToDamage

      lazy override protected[this] val parameterToModify: BonusType =
        toDamageType
      override val effectDetail: DetailedEffect = DetailedEffect(
        id = "WeaponDamage",
        description = "Increases damage for a given weapon type",
        categories = categories.map(_.toString),
        triggersOn = triggerOn.entryName,
        triggersOff = triggerOff.entryName
      )
      override val source: SourceInfo = src
      override lazy val value: Seq[(WeaponCategory, Int)] = toDamageAmount
      override lazy val effectText: Option[String] = Some(s"To Damage Amount: $value%")
    }

  abstract override def features: Seq[Feature[_]] = {
    assert(toDamageChance.value == toDamageAmount)
    super.features :+ toDamageChance
  }

}
