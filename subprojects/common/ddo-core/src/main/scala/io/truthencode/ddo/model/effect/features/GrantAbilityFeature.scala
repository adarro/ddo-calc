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
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect.{Feature, ParameterModifier, PartModifier, SourceInfo}
import io.truthencode.ddo.model.stats.BasicStat

/**
 * Grants an ability such as Bard Songs, Attack or Cleave
 */
trait GrantAbilityFeature extends Features {
  self: SourceInfo =>
  val grantBonusType: BonusType
  val grantedAbility: ActiveAbilities
  private val src = this

  private[this] val dodgeChance =
    new PartModifier[ActiveAbilities, BasicStat] with ParameterModifier[ActiveAbilities, BonusType] {

      lazy override protected[this] val partToModify: BasicStat =
        BasicStat.GrantedAbility

      lazy override protected[this] val parameterToModify: BonusType =
        grantBonusType

      override val source: SourceInfo = src
      override lazy val value: ActiveAbilities = grantedAbility
      override lazy val effectText: Option[String] = Some(s"Granted Ability $grantedAbility")
    }

  abstract override def features: Seq[Feature[_]] = {
    assert(dodgeChance.value == grantedAbility)
    super.features :+ dodgeChance
  }

}
