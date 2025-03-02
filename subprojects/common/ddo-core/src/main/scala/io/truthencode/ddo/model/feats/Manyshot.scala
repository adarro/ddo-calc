/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Manyshot.scala
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
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{
  DoubleShotFeature,
  FeaturesImpl,
  GrantAbilityFeature
}
import io.truthencode.ddo.model.misc.{PoolManyShot, SharedCoolDown}
import io.truthencode.ddo.providers.SimpleValueProvider
import io.truthencode.ddo.support.charges.{Chargeable, Rechargeable, TimedCharge}
import io.truthencode.ddo.support.requisite.*

import java.time.Duration

/**
 * Icon Feat Many Shot.png [[https://ddowiki.com/page/Manyshot Manyshot]] Active - Ability For the
 * next 20 seconds, add your base attack bonus * 4 to your Doubleshot and Ranged power. This ability
 * puts Ten Thousand Stars on a 30 second cooldown.
 *
 * Cooldown: 2 minutes.
 *
 * Point Blank Shot, Rapid Shot Dexterity 17, Base Attack Bonus +6
 *
 * @note
 *   This feat does not work with thrown weapons or crossbows. Rangers receive this feat for free at
 *   level 6, even if the prerequisites are not met. Using this ability places Ten Thousand Stars on
 *   a 30 second cool-down if its current cool-down is less than 30 seconds. Tip: Increase your Base
 *   attack bonus, for example using Tenser's Transformation, to receive maximum benefit from this
 *   feat. Fighters may select this feat as one of their fighter bonus feats.
 *
 * @todo
 *   add 20 second Active
 */
protected[feats] trait Manyshot
  extends FeatRequisiteImpl with ClassRequisiteImpl with TriggeredActivationImpl
  with BonusSelectableToClassFeatImpl with ActiveFeat with AtWillEvent with RequiresAllOfFeat
  with AttributeRequisiteImpl with RequiresAllOfAttribute with RequiresBaB with GrantsToClass
  with FighterBonusFeat with FeaturesImpl with GrantAbilityFeature with DoubleShotFeature
  with SharedCoolDown with Chargeable {
  self: GeneralFeat =>
  override lazy val grantedAbility: ActiveAbilities = ActiveAbilities.Manyshot
  override protected val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.AtWill)
  override protected val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)

  override def doubleShotEffectText: Option[String] = Some(
    "You gain Doubleshot equal to 1.5x your Base Attack Bonus with Longbows and Shortbows.")

  /**
   * Used to group shared timer resources. It is strongly recommended to use one of the values in
   * [[io.truthencode.ddo.model.misc.CoolDownPool]]
   */
  override val coolDownPoolId: String = PoolManyShot
  override val grantBonusType: BonusType = BonusType.Feat
  override protected val doubleShotTriggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  override protected val doubleShotTriggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)

  override protected val grantAbilityCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.Ability, effect.EffectCategories.RangedCombat)
  override val abilityId: String = "ManyShot"
  override val description: String =
    "Ability For the next 20 seconds, add your base attack bonus * 4 to your Doubleshot and Ranged power"

// TODO: Add Doubleshot feature ADD BAB +4 Feature
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PointBlankShot, GeneralFeat.RapidShot)

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 6

  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 17))

  override def gkBonusSelectableClasses: String = "BonusSelection"

  override def gkGrantClasses: String = "ClassGrant"

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 6))

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(2))
  def provider = new BabPercentAmpPerLevelProvider()

  override protected val doubleShotBonusType: BonusType = BonusType.Feat
  override protected val doubleShotValue: Int = 0
  override protected val doubleShotCategories: Seq[effect.EffectCategories.Value] = Seq(
    effect.EffectCategories.RangedCombat)
  override def calculate: Int => Int = doMath

  def doMath[Int](implicit
    provider: SimpleValueProvider[Int] = provider.asInstanceOf[SimpleValueProvider[Int]])
    : Int => Int = {
    provider.createValue
  }
  val maxCharges: Int = 3
  val chargeQuantity: Option[Int] = Some(1)
  val chargeInterval: Duration = Duration.ofSeconds(12)
  override def charges: Rechargeable =
    TimedCharge(chargeQuantity, maxCharges, chargeInterval)
}
