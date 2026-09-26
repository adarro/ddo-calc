/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: FeatRequisite.scala
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

import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.support.requisite.RequirementImplicits.{featToReq, FeatImplicits}

import scala.language.{implicitConversions, postfixOps}
import scala.languageFeature.higherKinds

/**
 * Created by adarr on 1/29/2017.
 */
sealed trait FeatRequisite {
  self: Requisite =>
  def anyOfFeats: Seq[Feat] // = IndexedSeq.apply()

  def allOfFeats: Seq[Feat] // = IndexedSeq.apply()

  def noneOfFeats: Seq[Feat] // = IndexedSeq.apply()
}

trait FeatRequisiteImpl extends MustContainImpl[Requirement] with FeatRequisite {
  self: Requisite & RequisiteType =>
  def anyOfFeats: Seq[Feat] = IndexedSeq.apply()

  def allOfFeats: Seq[Feat] = IndexedSeq.apply()

  def noneOfFeats: Seq[Feat] = IndexedSeq.apply()
}

object FeatRequisite {
  def stringToClass(classId: String*): Seq[Feat] = {
    for
      cls <- classId
      cOpt <- Feat.withNameInsensitiveOption(cls)
    yield cOpt
  }
}

/**
 * A free feat has no prerequisites and can be taken by any class at any level.
 */
trait FreeFeat extends FeatRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfFeat extends FeatRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfFeats.map(_.toReq)
  }
}

trait RequiresAllOfFeat extends FeatRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfFeats.map(_.toReq)
  }
}

trait RequiresNoneOfFeat extends FeatRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfFeats.map(_.toReq)
  }
}
