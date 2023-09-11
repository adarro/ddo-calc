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
import io.truthencode.ddo.model.favor.FavorPatron
import io.truthencode.ddo.support.requisite.RequirementImplicits.patronToReq

/**
 * Created by adarr on 2/11/2017.
 */
trait PatronRequisite {

  def anyOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()

  def allOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()

  def noneOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()
}

trait FreePatron extends PatronRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfPatron extends PatronRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfPatron.collect(patronToReq)
  }
}

trait RequiresAllOfPatron extends PatronRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfPatron.collect(patronToReq)
  }
}

trait RequiresNoneOfPatron extends PatronRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfPatron.collect(patronToReq)
  }
}
