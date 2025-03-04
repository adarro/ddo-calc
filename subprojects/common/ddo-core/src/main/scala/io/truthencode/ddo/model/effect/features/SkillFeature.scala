/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SkillFeature.scala
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
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.model.skill.Skill

import scala.collection.immutable

/**
 * Affects One or more skills by the specified values
 */
trait SkillFeature extends Features {
  self: SourceInfo =>
  private lazy val skillChance: immutable.Seq[SkillEffect] = affectedSkills.map { f =>
    val categories = Seq(EffectCategories.Skill).map(_.toString)
    val effectDetail = DetailedEffect(
      id = s"Skill:${f._1.entryName}",
      description = "Improves skill",
      triggersOn = skillTriggerOn.map(_.entryName),
      triggersOff = skillTriggerOff.map(_.entryName),
      bonusType = bonusType.entryName
    )
    val eb = EffectParameterBuilder()
      .toggleOffValue(skillTriggerOff*)
      .toggleOnValue(skillTriggerOn*)
      .addBonusType(bonusType)
      .build
    SkillEffect(f._1, f._2, bonusType, src, categories, eb.modifiers, effectDetail)
  }
  def bonusType: BonusType
  val skillTriggerOn: Seq[TriggerEvent] = Seq(TriggerEvent.Passive)
  val skillTriggerOff: Seq[TriggerEvent] = Seq(TriggerEvent.Never)
  val affectedSkills: List[(Skill, Int)]
  private val src = this

  abstract override def features: Seq[Feature[?]] =
    super.features ++ skillChance
}
