/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
