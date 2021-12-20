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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.RequirementImplicits.attrToReq

/**
 * Created by adarr on 2/3/2017.
 */
sealed trait AttributeRequisite {
  self: Requisite =>
  def requiresAttribute: Seq[(Attribute, Int)] = IndexedSeq.apply()
}

trait FreeAttribute
  extends AttributeRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAttribute extends AttributeRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    requiresAttribute.collect(attrToReq)
  }

  //  abstract override def prerequisites: Seq[RequisiteExpression] = super.prerequisites :+ this

}
