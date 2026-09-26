/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EnhancementRequisite.scala
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

import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.support.requisite.RequirementImplicits.classEnhancementToReq

import scala.language.{implicitConversions, postfixOps}

/**
 * Basic support for handling Class enhancement specific requisites
 */
sealed trait ClassEnhancementRequisite {
  self: Requisite =>
  def anyOfClassEnhancements: Seq[ClassEnhancement] // = IndexedSeq.apply()

  def allOfClassEnhancements: Seq[ClassEnhancement] // = IndexedSeq.apply()

  def noneOfClassEnhancements: Seq[ClassEnhancement] // = IndexedSeq.apply()
}

trait ClassEnhancementRequisiteImpl
  extends MustContainImpl[Requirement] with ClassEnhancementRequisite {
  self: Requisite & RequisiteType =>
  def anyOfClassEnhancements: Seq[ClassEnhancement] = IndexedSeq.apply()

  def allOfClassEnhancements: Seq[ClassEnhancement] = IndexedSeq.apply()

  def noneOfClassEnhancements: Seq[ClassEnhancement] = IndexedSeq.apply()
}

object ClassEnhancementRequisite {

  def stringToClass(classId: String*): Seq[ClassEnhancement] = {
    for
      cls <- classId
      cOpt <- ClassEnhancement.withNameInsensitiveOption(cls)
    yield cOpt
  }
}

/**
 * A free ClassEnhancement has no prerequisites and can be taken by any class at any level.
 */
trait FreeClassEnhancement
  extends ClassEnhancementRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfClassEnhancement
  extends ClassEnhancementRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfClassEnhancements.collect(classEnhancementToReq)
  }
}

trait RequiresAllOfClassEnhancement
  extends ClassEnhancementRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfClassEnhancements.collect(classEnhancementToReq)
  }
}

trait RequiresNoneOfClassEnhancement
  extends ClassEnhancementRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfClassEnhancements.collect(classEnhancementToReq)
  }
}
