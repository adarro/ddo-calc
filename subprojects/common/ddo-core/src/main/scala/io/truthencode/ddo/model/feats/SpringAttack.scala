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

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/**
 * Icon Feat Spring Attack.png Spring Attack Passive Character suffers no penalty to his attack roll when meleeing and
 * moving. You will also gain a 2% dodge bonus.
 * NOT YET IMPLEMENTED: No attack penalty for melee while moving.
 *
 * Dodge, Mobility Dexterity 13 , Base Attack Bonus 4,
 */
protected[feats] trait SpringAttack
  extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB
  with FighterBonusFeat with MartialArtsFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.Dodge, GeneralFeat.Mobility)

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 4

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

    override val grantBonusType: BonusType = BonusType.Feat
    override val grantedAbility: ActiveAbilities = ActiveAbilities.SpringAttack
}
