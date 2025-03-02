/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SkillRequisite.scala
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
package io.truthencode.ddo.support.requisite

import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite.RequirementImplicits.skillToReq

/**
 * Created by adarr on 1/30/2017.
 */
sealed trait SkillRequisite { self: Requisite =>
  def oneOfSkill: Seq[(Skill, Int)]
  def allOfSkill: Seq[(Skill, Int)]
}

trait SkillRequisiteImpl extends MustContainImpl[Requirement] with SkillRequisite {
  self: Requisite & RequisiteType =>
  override def oneOfSkill: Seq[(Skill, Int)] = IndexedSeq.apply()
  override def allOfSkill: Seq[(Skill, Int)] = IndexedSeq.apply()

}

trait FreeSkill extends SkillRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfSkill extends SkillRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    oneOfSkill.collect(skillToReq)
  }
}

trait RequiresAllOfSkill extends SkillRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfSkill.collect(skillToReq)
  }
}
