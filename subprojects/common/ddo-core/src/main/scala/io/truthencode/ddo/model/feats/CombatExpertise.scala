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
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{
  ArmorClassPercentFeature,
  FeaturesImpl,
  GrantAbilityFeature
}
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute
}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Combat_Expertise Combat Expertise]] Cooldown: 30 seconds Usage:
 * Active, Toggled Stance Prerequisite: Intelligence 13 Description Defensive Combat Stance: While
 * using Combat Expertise mode, you suffer -5 to your attack rolls but gain +10% feat bonus to Armor
 * Class. Spells have three times their normal cooldown when this mode is active. Combat Expertise
 * dispels and wards against all Rage effects.
 */
protected[feats] trait CombatExpertise
  extends FeatRequisiteImpl with ActiveFeat with AttributeRequisiteImpl with RequiresAllOfAttribute
  with MartialArtsFeat with FighterBonusFeat with ArtificerBonusFeat with DefensiveCombatStance
  with FeaturesImpl with GrantAbilityFeature with ArmorClassPercentFeature {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.CombatExpertise
  override protected[this] lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnStance)
  override protected[this] lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnSpellCast)
  override protected[this] lazy val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Stance)
  override val allOfAttributes = List((Attribute.Intelligence, 13))
  override val grantBonusType: BonusType = BonusType.Feat
  override protected val armorBonusType: BonusType = BonusType.Feat
  override protected val armorBonusAmount: Int = 10
  override val abilityId: String = "CombatExpertise"
  override val description: String =
    "Defensive Combat Stance: While using Combat Expertise mode, you suffer -5 to your attack rolls but gain +10% feat bonus to Armor Class."

// TODO: Add attack roll penalty and 3x Spell CoolDown
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(30))
}
