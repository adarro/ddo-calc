/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DeflectArrows.scala
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
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.{DeflectArrowsFeature, FeaturesImpl}
import io.truthencode.ddo.model.misc.CoolDown
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute,
  RequiresAllOfClass
}

import java.time.Duration

trait DeflectArrows
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with RequiresAllOfClass with Passive
  with AttributeRequisiteImpl with RequiresAllOfAttribute with MartialArtsFeat with FeaturesImpl
  with DeflectArrowsFeature with CoolDown {
  self: GeneralFeat =>
  override protected lazy val triggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.OnTimer)
  override protected lazy val triggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.OnCoolDown)
  override protected lazy val deflectCategories: Seq[effect.EffectCategories.Value] =
    Seq(effect.EffectCategories.MissChance, effect.EffectCategories.Ability)
  override protected val deflectArrowsBonusType: BonusType = BonusType.Feat
  override protected val secondsPerArrow: Int = 6

  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  /**
   * Some duration until this action / spell / ability can be used again.
   *
   * @return
   *   Some Time span which must elapse before re-activation.
   */
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(secondsPerArrow))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Monk, 1))

}
