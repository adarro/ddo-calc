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
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.stats.BasicStat
import io.truthencode.ddo.support.naming.UsingSearchPrefix

import scala.util.{Success, Try}

trait DoubleShotFeature extends Features {
  self: SourceInfo =>
  protected val doubleShotBonusType: BonusType
  protected val doubleShotValue: Int
  protected[this] val doubleShotTriggerOn: Seq[TriggerEvent]
  protected[this] val doubleShotTriggerOff: Seq[TriggerEvent]
  protected[this] val doubleShotCategories: Seq[effect.EffectCategories.Value]
  private val src = this
  def doubleShotEffectText: Option[String] = None
  private[this] val doubleShotEffect =
    new PartModifier[Int, BasicStat] with DynamicFeature[Int] with UsingSearchPrefix {

      /**
       * a list of Categories useful for menu / UI placement and also for searching / querying for
       * Miss-Chance or other desired effects.
       *
       * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to
       * enable quick and advanced searching for specific categories from general (Miss-Chance) to
       * specific (evasion). In addition, it may be useful for deep searching such as increasing
       * Spot, which should suggest not only +Spot items, but +Wisdom or eventually include a feat
       * or enhancement that allows the use of some other value as your spot score.
       */
      override def categories: Seq[String] = doubleShotCategories.map(_.toString)

      /**
       * The current Seaerch-Fu is weak. Override this default function.
       */
      override lazy val part: Try[EffectPart] = Success(EffectPart.DoubleShot)

      /**
       * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
       * qualified "Race:HalfElf"
       *
       * @return
       *   A default or applied prefix
       */
      override def searchPrefixSource: String = partToModify.searchPrefixSource

      override protected[this] lazy val partToModify: BasicStat =
        BasicStat.DoubleShot

      private lazy val eb = EffectParameterBuilder()
        .toggleOffValue(doubleShotTriggerOff: _*)
        .toggleOnValue(doubleShotTriggerOn: _*)
        .addBonusType(doubleShotBonusType)
        .build

      override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers

      /**
       * The General Description should be just that. This should not include specific values unless
       * all instances will share that value. I.e. a Dodge Effect might state it increases your
       * miss-chance, but omit any value such as 20%. Those values will be displayed in the
       * effectText of a specific implementation such as the Dodge Feat or Uncanny Dodge
       */
      override val generalDescription: String = "Chance to periodically deflect an incoming missile"

      override lazy val effectDetail: DetailedEffect = DetailedEffect(
        id = "DoubleShot",
        description =
          "grants a character a chance to do additional damage with their ranged or thrown weapon any time they make an attack.",
        triggersOn = doubleShotTriggerOn.map(_.entryName),
        triggersOff = doubleShotTriggerOff.map(_.entryName),
        bonusType = doubleShotBonusType.entryName
      )
      override val source: SourceInfo = src
      override lazy val value: Int = doubleShotValue
      override lazy val effectText: Option[String] = doubleShotEffectText match {
        case x: Some[String] => x
        case _ =>
          Some(
            "Grants a character a chance to do additional damage with their ranged or thrown weapon any time they make an attack.")
      }

      override def computedValue: Int => Int = calculate
    }
  def calculate: Int => Int
  abstract override def features: Seq[Feature[_]] = {
    assert(doubleShotEffect.value == doubleShotValue)
    super.features :+ doubleShotEffect
  }

}
