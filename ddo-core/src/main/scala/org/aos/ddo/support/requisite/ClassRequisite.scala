package org.aos.ddo.support.requisite

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.RequirementImplicits.classToReq

/**
  * Created by adarr on 1/30/2017.
  */
trait ClassRequisite {
  def anyOfClass: Seq[(CharacterClass, Int)]

  def allOfClass: Seq[(CharacterClass, Int)]

  def noneOfClass: Seq[(CharacterClass, Int)]

  def grantToClass: Seq[(CharacterClass, Int)]
}

trait ClassRequisiteImpl extends ClassRequisite {
  override def anyOfClass: Seq[(CharacterClass, Int)] = Nil

  override def allOfClass: Seq[(CharacterClass, Int)] = Nil

  override def noneOfClass: Seq[(CharacterClass, Int)] = Nil

  override def grantToClass: Seq[(CharacterClass, Int)] = Nil
}

trait FreeClass
    extends ClassRequisite
    with RequiresNone
    with RequiredExpression
    with Requisite

trait RequiresAnyOfClass
    extends ClassRequisite
    with RequiresOneOf[Requirement]
    with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfClass collect classToReq
  }
}

trait RequiresAllOfClass
    extends ClassRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfClass collect classToReq
  }
}

trait RequiresNoneOfClass
    extends ClassRequisite
    with RequiresNoneOf[Requirement]
    with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfClass collect classToReq
  }
}

trait GrantsToClass
    extends ClassRequisite
    with GrantExpression
    with RequiresOneOf[Requirement]
    with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantToClass collect classToReq
  }
}
