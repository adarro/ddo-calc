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
package io.truthencode.ddo.support.requisite

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
  def prerequisites: Seq[RequirementSet[_, _]] = Seq()
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

  private[this] def makeSet: RequirementSet[RequiresOneOf[T], RequiresOneOf[T]] = RequirementSet(this, this, oneOf: _*)

  abstract override def prerequisites: Seq[RequirementSet[_, _]] = super.prerequisites :+ makeSet
}

trait RequiresAllOf[T <: Requirement] extends MustContainAllOf[T] with Require {
  private[this] def makeSet: RequirementSet[RequiresAllOf[T], RequiresAllOf[T]] = RequirementSet(this, this, allOf: _*)

  abstract override def prerequisites: Seq[RequirementSet[_, _]] = super.prerequisites :+ makeSet
}

trait RequiresNoneOf[T <: Requirement] extends MustContainNoneOf[T] with Require {
  private[this] def makeSet: RequirementSet[RequiresNoneOf[T], RequiresNoneOf[T]] =
    RequirementSet(this, this, noneOf: _*)

  abstract override def prerequisites: Seq[RequirementSet[_, _]] = super.prerequisites :+ makeSet
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
