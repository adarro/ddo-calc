package org.aos.ddo.support.requisite

import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite.RequirementImplicits.skillToReq

/**
  * Created by adarr on 1/30/2017.
  */
sealed trait SkillRequisite { self: Requisite =>
  def oneOfSkill: Seq[(Skill, Int)]
  def allOfSkill: Seq[(Skill, Int)]
}

trait SkillRequisiteImpl
    extends MustContainImpl[Requirement]
    with SkillRequisite { self: Requisite with RequisiteType =>
  override def oneOfSkill: Seq[(Skill, Int)] = IndexedSeq.apply()
  override def allOfSkill: Seq[(Skill, Int)] = IndexedSeq.apply()

}

trait FreeSkill
    extends SkillRequisite
    with RequiresNone
    with RequiredExpression
    with Requisite

trait RequiresAnyOfSkill
    extends SkillRequisite
    with RequiresOneOf[Requirement]
    with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    oneOfSkill collect skillToReq
  }
}

trait RequiresAllOfSkill
    extends SkillRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfSkill collect skillToReq
  }
}
