/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DetailedEffect.scala
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
package io.truthencode.ddo.api.model.effect

import scala.annotation.targetName

/**
 * Represents a specific effect This includes all details such as what it modifies and when it takes
 * effect.
 *
 * @param id
 *   This will need to uniquely identify the effect
 * @param description
 *   A description of the effect
 * @param triggersOn
 *   When this effect applies. Should correspond to some state chance such as full health,
 *   unconscious, on critical hit, on miss, or Always on, etc.
 * @param triggersOff
 *   When this effect does not apply. Could be, never or some duration or any of the list from
 *   triggersOn.
 * @param bonusType
 *   Used to determine stacking rules for applying this effect
 * @see
 *   TriggerEvent - Valid values for trigger on / off values.
 * @see
 *   Category - Valid values
 */
case class DetailedEffect(
  id: String,
  description: String,
  triggersOn: Seq[String],
  triggersOff: Seq[String],
  bonusType: String,
  override val scaling: Option[Map[String, Int]] = None)
  extends DetailedEffectInfo

object DetailedEffect {
  @targetName("applyWithScalingInfo")

  def apply(
    id: String,
    description: String,
    triggersOn: Seq[String],
    triggersOff: Seq[String],
    bonusType: String,
    si: Option[Seq[ScalingInfo]]): DetailedEffect = {
    val scaling = si match
      case Some(value) => Some(value.map(s => s.source -> s.value).toMap)
      case None => None
    DetailedEffect(id, description, triggersOn, triggersOff, bonusType, scaling)
  }
}
