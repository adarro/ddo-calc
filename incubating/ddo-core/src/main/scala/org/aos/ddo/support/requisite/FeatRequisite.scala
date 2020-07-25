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
package io.truthencode.ddo.support.requisite

import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.support.requisite.RequirementImplicits.featToReq

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
  self: Requisite with RequisiteType =>
  def anyOfFeats: Seq[Feat] = IndexedSeq.apply()

  def allOfFeats: Seq[Feat] = IndexedSeq.apply()

  def noneOfFeats: Seq[Feat] = IndexedSeq.apply()
}

object FeatRequisite {
  def stringToClass(classId: String*): Seq[Feat] = {
    for {cls <- classId
         cOpt <- Feat.withNameInsensitiveOption(cls)
    } yield cOpt
  }
}

trait FreeFeat extends FeatRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfFeat extends FeatRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfFeats collect featToReq
  }
}

trait RequiresAllOfFeat extends FeatRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfFeats collect featToReq
  }
}

trait RequiresNoneOfFeat extends FeatRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfFeats collect featToReq
  }
}


