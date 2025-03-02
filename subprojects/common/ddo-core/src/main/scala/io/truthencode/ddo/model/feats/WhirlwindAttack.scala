/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WhirlwindAttack.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB}

import java.time.Duration

/**
 * Icon Feat Whirlwind Attack.png [[https://ddowiki.com/page/Whirlwind_Attack Whirlwind Attack]]
 * Active - Special Attack This feat attacks all enemies in a 360 degree arc around the character.
 * This attack deals +4[W] damage.
 *
 * Dodge, Mobility, Spring Attack Combat Expertise, Base Attack Bonus +4
 *
 * @note
 *   Only Combat Expertise and Spring Attack are required as feats. However, Spring Attack depends
 *   on Mobility with Depends on Dodge and are listed on the Wiki as such. Nested dependencies
 *   should be inferred and this list may remove all but explicit dependencies.
 */
protected[feats] trait WhirlwindAttack
  extends FeatRequisiteImpl with TriggeredActivationImpl with BonusSelectableToClassFeatImpl
  with ActiveFeat with AtWillEvent with RequiresAllOfFeat with RequiresBaB with FighterBonusFeat
  with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>

  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.WhirlwindAttack
  override val grantBonusType: BonusType = BonusType.Feat
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.SpecialAttack)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.SpecialAttack, effect.EffectCategories.Ability)
  override val abilityId: String = "WhirlwindAttack"
  override val description: String =
    "Special Attack This feat attacks all enemies in a 360 degree arc around the character."

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(5))

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 4

  override def allOfFeats: Seq[GeneralFeat] =
    List(
      GeneralFeat.Dodge,
      GeneralFeat.Mobility,
      GeneralFeat.SpringAttack,
      GeneralFeat.CombatExpertise
    )
}
