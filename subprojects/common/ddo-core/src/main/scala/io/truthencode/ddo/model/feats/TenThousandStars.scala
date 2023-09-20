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
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{FeaturesImpl, GrantAbilityFeature}
import io.truthencode.ddo.model.misc.CoolDownPool.ManyShot
import io.truthencode.ddo.model.misc.SharedCoolDown
import io.truthencode.ddo.support.requisite._

import java.time.Duration

/**
 * Icon Feat Ten Thousand Stars.png
 * [[https://ddowiki.com/page/Ten_Thousand_Stars Ten Thousand Stars]] Active - Ability For the next
 * 30 seconds, add your Wisdom ability score to your Ranged Power and add your monk level * 5 to
 * your Doubleshot. This ability puts Manyshot on a 30 second cooldown. Cooldown: one minute.
 * @fixme
 *   Currently have only one cooldown pool, but this has a 1 minute cooldown and adds Manyshot
 *   cooldown event.
 *
 * Level 6: Monk Dexterity 13
 */
protected[feats] trait TenThousandStars
  extends FeatRequisiteImpl with ClassRequisiteImpl with ActiveFeat with AtWillEvent
  with SharedCoolDown with AttributeRequisiteImpl with RequiresAllOfAttribute
  with RequiresAllOfClass with MartialArtsFeat with FeaturesImpl with GrantAbilityFeature {
  self: GeneralFeat =>

  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.TenThousandStars
  override val coolDownPoolId: String = ManyShot.coolDownPoolId
  override protected[this] val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected[this] val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected[this] val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.RangedCombat)
  override val grantBonusType: BonusType = BonusType.Feat
  override val abilityId: String = "TenThousandStars"
  override val description: String =
    "or the next 30 seconds, add your Wisdom ability score to your Ranged Power and add your monk level * 5 to your Doubleshot."

  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(60))

// TODO: CoolDownPool 10K stars now has 1 minute cool down and puts Manyshot on 30 cool-down
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Monk, 6))
}
