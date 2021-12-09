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

import java.time.Duration
import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.abilities.ActiveAbilities
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Icon Feat Improved Trip.png [[https://ddowiki.com/page/Improved_Trip Improved Trip]] Active - Special Attack This
 * feat has a chance to trip the target, if the target fails a Balance check (DC 14 + Str mod), rendering it prone for a
 * longer period of time than Trip. Some creatures may be immune to this effect. * Combat Expertise
 */
protected[feats] trait ImprovedTrip
  extends FeatRequisiteImpl with ActiveFeat with Tactical with RequiresAllOfFeat with FighterBonusFeat
  with MartialArtsFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(10))

  override val allOfFeats = List(GeneralFeat.CombatExpertise)
  override val grantBonusType: BonusType = BonusType.Feat
  override val grantedAbility: ActiveAbilities = ActiveAbilities.ImprovedTrip
}
