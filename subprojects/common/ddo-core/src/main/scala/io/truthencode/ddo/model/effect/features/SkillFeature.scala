/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.model.stats.BasicStat

/**
 * Affects One or more skills by the specified values
 */
trait SkillFeature extends Features {
  self: SourceInfo =>
  val bonusType: BonusType

  val affectedSkills: List[(Skill,Int)]
  private val src = this

  private lazy val skillChance = affectedSkills.map {f=>
    Feature.SkillEffect(f._1,f._2,bonusType,this)
//    val s = f._1
//    val i = f._2
//      new PartModifier[Int, Skill] with ParameterModifier[Int, BonusType] {
//
//          lazy override protected[this] val partToModify: Skill =
//              s
//
//          lazy override protected[this] val parameterToModify: BonusType =
//              bonusType
//
//          override val source: SourceInfo = src
//          override val value: Int = i
//
//      }
  }


  abstract override def features: List[Feature[_]] =
    super.features ++ skillChance
}
