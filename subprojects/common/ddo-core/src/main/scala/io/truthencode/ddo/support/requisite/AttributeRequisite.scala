/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AttributeRequisite.scala
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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.Requirement.GroupedRequirement
import io.truthencode.ddo.support.requisite.RequirementImplicits.AttributeImplicits

/**
 * Created by adarr on 2/3/2017.
 */
sealed trait AttributeRequisite {
  self: Requisite =>
  //  we currently don't have a gkNoneAttributes
  def gkAllAttributes: String
  def gkAnyAttributes: String
  def allOfAttributes: Seq[(Attribute, Int)]
  def anyOfAttributes: Seq[(Attribute, Int)]
  def noneOfAttributes: Seq[(Attribute, Int)]
}

trait AttributeRequisiteImpl extends MustContainImpl[Requirement] with AttributeRequisite {
  self: Requisite & RequisiteType =>
  def anyOfAttributes: Seq[(Attribute, Int)] = Nil
  override def gkAllAttributes: String = defaultGroupKey
  override def gkAnyAttributes: String = defaultGroupKey

  def allOfAttributes: Seq[(Attribute, Int)] = Nil

  def noneOfAttributes: Seq[(Attribute, Int)] = Nil
}

trait FreeAttribute
  extends AttributeRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfAttribute
  extends AttributeRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    allOfAttributes.map(_.toReq).map(GroupedRequirement(_, gkAnyAttributes, RequisiteType.Require))
  }

}

trait RequiresAllOfAttribute
  extends AttributeRequisite with RequiresAllOf[Requirement] with Requisite {
  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfAttributes.map(_.toReq).map(GroupedRequirement(_, gkAllAttributes, RequisiteType.Require))
  }
}
