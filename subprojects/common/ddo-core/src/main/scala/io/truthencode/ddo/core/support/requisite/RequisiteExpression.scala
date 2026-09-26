/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RequisiteExpression.scala
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

import io.truthencode.ddo.support.requisite.Requirement.GroupedRequirement

/**
 * Base stackable trait used to store an array of requirements along with logic to evaluate.
 */
sealed trait RequisiteExpression {
  self: RequisiteType & Inclusion =>

  /**
   * Array of Requirements which can be checked against a given source.
   *
   * @return
   */
  def prerequisites: Seq[RequirementSet[RequisiteType, Inclusion]] = Seq()
}

sealed trait SimpleRequisite extends RequisiteExpression {
  self: RequisiteType & Inclusion =>
}

trait RequiresNone extends SimpleRequisite with AnyOf {
  self: RequisiteType =>
}

sealed trait ComplexRequisite extends RequisiteExpression {
  self: RequisiteType & Inclusion =>
}

trait MustContainAtLeastOneOf[T <: Requirement] extends ComplexRequisite with AnyOf {
  self: RequisiteType =>
  // def oneOf: Seq[ReqSet[MustContainAtLeastOneOf[T], AnyOf]] = IndexedSeq()
//  val s = GroupedRequirement[T]("foo")
//  def oneOf: Seq[GroupedRequirement[T]]
  def oneOf: Seq[T]
}

trait MustContainAllOf[T <: Requirement] extends ComplexRequisite with AllOf {
  self: RequisiteType =>
  def allOf: Seq[T]
}

trait MustContainAllOfImpl[T <: Requirement] extends MustContainAllOf[T] {
  self: RequisiteType =>
  def allOf: Seq[T] = Seq()
}

trait MustContainAnyOfImpl[T <: Requirement] extends MustContainAtLeastOneOf[T] {
  self: RequisiteType =>
  def oneOf: Seq[T] = Seq()
}

trait MustContainNoneOfImpl[T <: Requirement] extends MustContainNoneOf[T] {
  self: RequisiteType =>
  def noneOf: Seq[T] = Seq()
}

trait MustContainNoneOf[T <: Requirement] extends ComplexRequisite with NoneOf {
  self: RequisiteType =>
  def noneOf: Seq[T]
}

trait MustContainImpl[T <: Requirement]
  extends MustContainAnyOfImpl[T] with MustContainNoneOfImpl[T] with MustContainAllOfImpl[T] {
  self: RequisiteType =>
}

trait RequiresOneOf[T <: Requirement] extends MustContainAtLeastOneOf[T] with Require {
  // val f: RequirementSet[RequiresOneOf[T], RequiresOneOf[T]] = RequirementSet(this, this, oneOf: _*)

  abstract override def prerequisites: Seq[RequirementSet[RequisiteType, Inclusion]] =
    super.prerequisites ++ makeSet

  private def makeSet: Seq[RequirementSet[RequisiteType, Inclusion]] = {
    val adapted = oneOf.map {
      case GroupedRequirement(x, t, r) => (t, x, r)
      case t: T => (defaultGroupKey, t, RequisiteType.Require)
    }.groupBy(_._1)
      .map { v =>
        RequirementSet(v._2.head._3, Inclusion.AnyOf, v._1, v._2.map(_._2)*)
      }
      .toSeq
    // RequirementSet(RequisiteType.Require, Inclusion.AnyOf, defaultGroupKey, oneOf: _*)
    adapted
  }
}

trait RequiresAllOf[T <: Requirement] extends MustContainAllOf[T] with Require {
  abstract override def prerequisites: Seq[RequirementSet[RequisiteType, Inclusion]] =
    super.prerequisites ++ makeSet

  private def makeSet: Seq[RequirementSet[RequisiteType, Inclusion]] = {
//      RequirementSet(RequisiteType.Require, Inclusion.AllOf, defaultGroupKey, allOf: _*)
    val adapted = allOf.map {
      case GroupedRequirement(x, t, r) => (t, x, r)
      case t: T => (defaultGroupKey, t, RequisiteType.Require)
    }.groupBy(_._1)
      .map { v =>
        RequirementSet(v._2.head._3, Inclusion.AllOf, v._1, v._2.map(_._2)*)
      }
      .toSeq
    // RequirementSet(RequisiteType.Require, Inclusion.AnyOf, defaultGroupKey, oneOf: _*)
    adapted
  }

}

trait RequiresNoneOf[T <: Requirement] extends MustContainNoneOf[T] with Require {
  abstract override def prerequisites: Seq[RequirementSet[RequisiteType, Inclusion]] =
    super.prerequisites :+ makeSet

  private def makeSet: RequirementSet[RequisiteType, Inclusion] =
    RequirementSet(RequisiteType.Require, Inclusion.NoneOf, defaultGroupKey, noneOf*)
}

/* Prohibiting 'One Of' does not make any sense in this context.
trait ProhibitsOneOf[+T <: Requirement] extends MustContainAtLeastOneOf[T] with Prohibit {
  private[this] def makeSet: RequirementSet = RequirementSet(this, this, oneOf)

  abstract override def prerequisites: Seq[RequirementSet] = super.prerequisites :+ makeSet
}
 */

/* Providing 'One Of' likely makes no sense in this context but may be needed??? Need to investigate
trait ProvidesOneOf[+T <: Requirement] extends MustContainAtLeastOneOf[T] with Grant {
  private[this] def makeSet: RequirementSet = RequirementSet(this, this, oneOf)

  abstract override def prerequisites: Seq[RequirementSet] = super.prerequisites :+ makeSet
}
 */
