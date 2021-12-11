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
import io.truthencode.ddo.model.effect.{DetailedEffect, EffectCategories, Feature, SourceInfo, TriggerEvent}
import io.truthencode.ddo.model.skill.Skill

import scala.collection.immutable

/**
 * Affects One or more skills by the specified values
 */
trait SkillFeature extends Features {
  self: SourceInfo =>
  val bonusType: BonusType

  val affectedSkills: List[(Skill, Int)]
  private val src = this

  private lazy val skillChance: immutable.Seq[Feature.SkillEffect] = affectedSkills.map { f =>
    val categories = Seq(EffectCategories.Skill).map(_.toString)
    val effectDetail = DetailedEffect(
      id = s"Skill:${f._1.entryName}",
      description = "Improves skill",
      categories = categories,
      triggersOn = TriggerEvent.Passive.toString,
      triggersOff = TriggerEvent.Never.toString
    )
    Feature.SkillEffect(f._1, f._2, bonusType, src, effectDetail)
  }

  abstract override def features: Seq[Feature[_]] =
    super.features ++ skillChance
}
