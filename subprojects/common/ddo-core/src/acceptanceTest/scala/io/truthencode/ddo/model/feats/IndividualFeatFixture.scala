/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: IndividualFeatFixture.scala
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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.DisplayHelper
import io.truthencode.ddo.model.feats.classes.ChargeSupport
import io.truthencode.ddo.support.charges.Chargeable
import io.truthencode.ddo.support.requisite._

import java.util
import scala.beans.BeanProperty
import scala.jdk.CollectionConverters._

class IndividualFeatFixture extends DisplayHelper with ChargeSupport with LazyLogging {
  override val displayEnum: E = Feat
  @BeanProperty
  var feat: String = scala.compiletime.uninitialized

  def setFeatId(id: String): Unit = {
    feat = id
  }
  type F = E & FeatType & Requisite & Inclusion
  def valueWithRequirements: Seq[ValueWithRequirements] =
    displayEnum.withNameInsensitiveOption(feat) match {
      case Some(f) =>
        f match {
          case x: (RequisiteExpression & RequisiteType & Inclusion) =>
            x.prerequisites.map { pr =>
              ValueWithRequirements(x, pr.reqType, pr.incl, pr.groupKey, pr.req)
            }
        }
      case None => Nil
    }

  lazy val sortish: SemiOrderedRequirements = SemiOrderedRequirements(valueWithRequirements*)
  def verifyPrerequisites(): util.Collection[String] = {
    sortish.displayPrerequisites.asJavaCollection
  }

  def verifyBonusSelections(): util.Collection[String] = {
    sortish.displayBonusSelections.asJavaCollection
  }

  def verifyGrantSelections(): util.Collection[String] = {
    sortish.displayGranted.asJavaCollection
  }

  case class FeatResult(id: String) {
    val f: Feat = Feat.withName(id)
    val active: String = f match {
      case x: ActiveFeat => "Active"
      case _ => "Passive"
    }
  }
  def fr: FeatResult = FeatResult(feat)
  def isActive: String = fr.active

  def javaSafeChargeInfo(): ChargeInfo = {
    fr.f match {
      case x: Chargeable => readChargeInfo(x)
      case _ =>
        logger.warn("failed to get charge info")
        null
    }

  }
}
