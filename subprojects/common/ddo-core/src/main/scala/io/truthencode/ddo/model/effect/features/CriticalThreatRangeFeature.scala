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
import io.truthencode.ddo.model.effect.{EffectCategories, Feature, SourceInfo}
import io.truthencode.ddo.model.item.weapon.WeaponCategory
import io.truthencode.ddo.model.stats.BasicStat

/**
 * Increases your Weapons Critical Threat range Different Weapon types have greater or lesser
 * bonuses and it may or may not apply to shields, depending on the feat / enhancement.
 */
trait CriticalThreatRangeFeature extends Features {
  self: SourceInfo =>
  lazy val ctr: Feature.CriticalThreatRangeEffect = Feature.CriticalThreatRangeEffect(
    criticalThreatRangeAmount,
    BasicStat.CriticalThreatRange,
    criticalThreatRangeType,
    src,
    cats,
    effectDetail)
  val cats = LazyList(EffectCategories.GeneralCombat.toString)
  protected def criticalThreatRangeType: BonusType
  protected val criticalThreatRangeAmount: Seq[(WeaponCategory, Int)]
  protected[this] def effectDetail: DetailedEffect
  private val src = this
//  private[this] val criticalThreatRange =
//    new PartModifier[Seq[(WeaponCategory, Int)], BasicStat]
//      with ParameterModifier[Seq[(WeaponCategory, Int)], BonusType] {
//
//      lazy override protected[this] val partToModify: BasicStat =
//        BasicStat.CriticalThreatRange
//
//      lazy override protected[this] val parameterToModify: BonusType =
//        criticalThreatRangeType
//
//      override val source: SourceInfo = src
//      override lazy val value: Seq[(WeaponCategory, Int)] = criticalThreatRangeAmount
//      override lazy val effectText: Option[String] = Some(s"Increased Critical Threat Amount: $value%")
//    }

  abstract override def features: Seq[Feature[_]] = {
    assert(ctr.value == criticalThreatRangeAmount)
    super.features :+ ctr
  }

}
