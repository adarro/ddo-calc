package org.aos.ddo.support.requisite

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.RequirementImplicits.attrToReq

/**
  * Created by adarr on 2/3/2017.
  */
sealed trait AttributeRequisite {
  self: Requisite =>
  def requiresAttribute: Seq[(Attribute, Int)] = IndexedSeq.apply()
}

trait FreeAttribute extends AttributeRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAttribute extends AttributeRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    requiresAttribute collect attrToReq
  }

  //  abstract override def prerequisites: Seq[RequisiteExpression] = super.prerequisites :+ this

}
