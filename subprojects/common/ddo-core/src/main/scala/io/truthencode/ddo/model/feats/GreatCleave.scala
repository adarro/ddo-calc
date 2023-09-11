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

import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect.features.{
  FeaturesImpl,
  GrantAbilityFeature,
  SpecialAttackFeature
}
import io.truthencode.ddo.model.misc.CoolDownPool.GreatCleave
import io.truthencode.ddo.model.misc.SharedCoolDown
import io.truthencode.ddo.support.requisite._

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Great_Cleave Great Cleave]] Active - Special Attack This feat attacks
 * enemies in a wider arc than Cleave, hence Great Cleave has a greater chance to hit more enemies
 * than Cleave. This attack deals +2[W] damage. Cooldown: 6 seconds Usage: Active Prerequisites:
 * Cleave Base Attack Bonus +4
 *
 * @note
 *   Prior to Update 45, it would also produce a glancing blow to each target (if using an
 *   appropriate weapon that allows them). Glancing blows have now been replaced by Strikethrough,
 *   which does not benefit Cleave or Great Cleave.
 */
trait GreatCleave
  extends FeatRequisiteImpl with ActiveFeat with AtWillEvent with SharedCoolDown
  with RequiresAllOfFeat with RequiresBaB with FighterBonusFeat with FeaturesImpl
  with SpecialAttackFeature with GrantAbilityFeature {
  self: GeneralFeat =>

  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.GreatCleave
  override val allOfFeats = List(GeneralFeat.Cleave)
  override val coolDownPoolId: String = GreatCleave.coolDownPoolId
  override val grantBonusType: BonusType = BonusType.Feat
  override val abilityId: String = "Cleave"
  override val description: String =
    "Special Attack This feat attacks enemies in a wider\n * arc than Cleave, hence Great Cleave has a greater chance to hit more enemies than Cleave."

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(5))

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 4
}
