package org.aos.ddo.support.requisite

import scala.collection.IndexedSeq

/**
  * Base stackable trait used to store an array of requirements along with logic to evaluate.
  */
sealed trait RequisiteExpression {
  self: RequisiteType with Inclusion =>
  /**
    * Array of Requirements which can be checked against a given source.
    *
    * @return
    */
  def prerequisites: Seq[RequirementSet[_, _]] = IndexedSeq()
}

object RequisiteExpression {

  implicit class Stuff(source: RequisiteExpression with RequisiteType with Inclusion) {
    // def foo = RequirementSet(source,source,source.)
  }

  implicit class GrantDsl(source: GrantOrRevokeExpression) {
    def grants(requisiteExpression: RequisiteExpression): GrantOrRevokeExpression = ???

    def grants(r: RequisiteExpression => RequisiteExpression): GrantOrRevokeExpression = ???

    def requires(requisiteExpression: RequisiteExpression): GrantOrRevokeExpression = ???

    def prohibits(requisiteExpression: RequisiteExpression): GrantOrRevokeExpression = ???
  }

  object Requires {

    implicit class Dsl(source: Require) {
      def and(exp: RequisiteExpression): ComplexRequisite = ???

      def or(exp: RequisiteExpression): ComplexRequisite = ???

      case class MustContainAtLeastOneOf(req: Requirement*) extends RequiresOneOf[Requirement] {
        override val oneOf: List[Requirement] = req.toList
      }

      case class MustContainAtLeastOne(req: Requirement) extends RequiresOneOf[Requirement] {
        override val oneOf: List[Requirement] = List(req)
      }

      def AllOf(exp: Requisite*): ComplexRequisite = ???

      def NoneOf(exp: Requisite*): ComplexRequisite = ???

      def Nothing: SimpleRequisite = ???
    }

  }

  implicit class Dsl(source: RequisiteExpression) {
    def and(exp: RequisiteExpression): ComplexRequisite = ???

    def or(exp: RequisiteExpression): ComplexRequisite = ???

    def OneOf(exp: Requisite*): ComplexRequisite = ???

    def AllOf(exp: Requisite*): ComplexRequisite = ???

    def NoneOf(exp: Requisite*): ComplexRequisite = ???

    def Nothing: SimpleRequisite = ???

  }

}

sealed trait SimpleRequisite extends RequisiteExpression {
  self: RequisiteType with Inclusion =>
}

trait RequiresNone extends SimpleRequisite with AnyOf {
  self: RequisiteType =>
}

sealed trait ComplexRequisite extends RequisiteExpression {
  self: RequisiteType with Inclusion =>
}

trait MustContainAtLeastOneOf[T <: Requirement] extends ComplexRequisite with AnyOf {
  self: RequisiteType =>
  // def oneOf: Seq[ReqSet[MustContainAtLeastOneOf[T], AnyOf]] = IndexedSeq()
  def oneOf: Seq[T]
}

trait MustContainAllOf[T <: Requirement] extends ComplexRequisite with AllOf {
  self: RequisiteType =>
  def allOf: Seq[T]
}

trait MustContainAllOfImpl[T <: Requirement] extends MustContainAllOf[T] {
  self: RequisiteType =>
  def allOf: Seq[T] = IndexedSeq()
}

trait MustContainAnyOfImpl[T <: Requirement] extends MustContainAtLeastOneOf[T] {
  self: RequisiteType =>
  def oneOf: Seq[T] = IndexedSeq()
}

trait MustContainNoneOfImpl[T <: Requirement] extends MustContainNoneOf[T] {
  self: RequisiteType =>
  def noneOf: Seq[T] = IndexedSeq()
}

trait MustContainNoneOf[T <: Requirement] extends ComplexRequisite with NoneOf {
  self: RequisiteType =>
  def noneOf: Seq[T]
}

trait MustContainImpl[T <: Requirement] extends MustContainAnyOfImpl[T] with MustContainNoneOfImpl[T] with MustContainAllOfImpl[T] {
  self : RequisiteType =>
}

trait RequiresOneOf[T <: Requirement] extends MustContainAtLeastOneOf[T] with Require {
  // val f: RequirementSet[RequiresOneOf[T], RequiresOneOf[T]] = RequirementSet(this, this, oneOf: _*)

  private[this] def makeSet: RequirementSet[RequiresOneOf[T], RequiresOneOf[T]] = RequirementSet(this, this, oneOf: _*)

  abstract override def prerequisites = super.prerequisites :+ makeSet
}

trait RequiresAllOf[T <: Requirement] extends MustContainAllOf[T] with Require {
  private[this] def makeSet: RequirementSet[RequiresAllOf[T], RequiresAllOf[T]] = RequirementSet(this, this, allOf: _*)

  abstract override def prerequisites = super.prerequisites :+ makeSet
}

trait RequiresNoneOf[T <: Requirement] extends MustContainNoneOf[T] with Require {
  private[this] def makeSet: RequirementSet[RequiresNoneOf[T], RequiresNoneOf[T]] = RequirementSet(this, this, noneOf: _*)

  abstract override def prerequisites = super.prerequisites :+ makeSet
}

/* Prohibiting 'One Of' does not make any sense in this context.
trait ProhibitsOneOf[+T <: Requirement] extends MustContainAtLeastOneOf[T] with Prohibit {
  private[this] def makeSet: RequirementSet = RequirementSet(this, this, oneOf)

  abstract override def prerequisites: Seq[RequirementSet] = super.prerequisites :+ makeSet
}
*/

/* Providing 'One Of' likely makes no sense in this context but may be needed??? Need to invistigate
trait ProvidesOneOf[+T <: Requirement] extends MustContainAtLeastOneOf[T] with Grant {
  private[this] def makeSet: RequirementSet = RequirementSet(this, this, oneOf)

  abstract override def prerequisites: Seq[RequirementSet] = super.prerequisites :+ makeSet
}
*/



