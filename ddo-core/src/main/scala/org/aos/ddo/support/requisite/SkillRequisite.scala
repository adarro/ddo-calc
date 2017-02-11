package org.aos.ddo.support.requisite


import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite.RequirementImplicits.skillToReq

/**
  * Created by adarr on 1/30/2017.
  */
sealed trait SkillRequisite {
  self: Requisite =>
  def requiresSkill: Seq[(Skill, Int)] = IndexedSeq.apply()
}

trait FreeSkill extends SkillRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresSkill extends SkillRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    requiresSkill collect skillToReq
  }

  //  abstract override def prerequisites: Seq[RequisiteExpression] = super.prerequisites :+ this

}
