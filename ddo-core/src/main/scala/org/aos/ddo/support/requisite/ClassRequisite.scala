package org.aos.ddo.support.requisite

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.RequirementImplicits.classToReq
/**
  * Created by adarr on 1/30/2017.
  */
trait ClassRequisite {
  def anyOfClass: Seq[(CharacterClass, Int)] = IndexedSeq.apply()

  def allOfClass: Seq[(CharacterClass, Int)] = IndexedSeq.apply()

  def noneOfClass: Seq[(CharacterClass, Int)] = IndexedSeq.apply()
}


trait FreeClass extends ClassRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfClass extends ClassRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfClass collect classToReq
  }
}

trait RequiresAllOfClass extends ClassRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfClass collect classToReq
  }
}


trait RequiresNoneOfClass extends ClassRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfClass collect classToReq
  }
}