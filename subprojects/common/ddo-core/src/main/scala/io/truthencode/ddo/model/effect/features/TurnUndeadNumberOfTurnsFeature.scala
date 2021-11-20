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
import io.truthencode.ddo.model.effect.{Feature, ParameterModifier, PartModifier, SourceInfo}
import io.truthencode.ddo.model.stats.BasicStat

trait TurnUndeadNumberOfTurnsFeature extends Features {
  self: SourceInfo =>
  val numberOfTurnsBonusType: BonusType
  val numberOfTurnsBonusAmount: Int
  private val src = this

  private[this] val nHD =
    new PartModifier[Int, BasicStat] with ParameterModifier[Int, BonusType] {

      lazy override protected[this] val partToModify: BasicStat =
        BasicStat.TurnUndeadMaxHitDice

      lazy override protected[this] val parameterToModify: BonusType =
        numberOfTurnsBonusType

      override val source: SourceInfo = src
      override lazy val value: Int = numberOfTurnsBonusAmount

    }

  abstract override def features: List[Feature[_]] = {
    assert(nHD.value == numberOfTurnsBonusAmount)
    super.features :+ nHD
  }

}
