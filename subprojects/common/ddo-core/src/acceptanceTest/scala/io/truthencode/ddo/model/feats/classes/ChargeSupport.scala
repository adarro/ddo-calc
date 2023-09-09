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
package io.truthencode.ddo.model.feats.classes

import io.truthencode.ddo.model.DisplayHelper
import io.truthencode.ddo.support.charges.Interval.OnTimer
import io.truthencode.ddo.support.charges.{Chargeable, Rechargeable}

import java.time.Duration
import java.util.Optional
import scala.beans.BeanProperty
import scala.jdk.OptionConverters._

/**
 * Helper classs to test Entities with Charges
 */
trait ChargeSupport {
  self: DisplayHelper =>
  case class ChargeInfo(
    @BeanProperty var maxCharges: Int,
    @BeanProperty var quantity: java.util.OptionalInt,
    @BeanProperty var interval: Optional[Duration]) {
    var safeInterval: Long = interval.toScala match {
      case Some(x) => x.toSeconds
      case None => -99L
    }
  }
  def readChargeInfo[T <: Chargeable](s: T): ChargeInfo = {
    val resultMaxCharges = s.charges.maxCharges

    val rechargeable = s.charges match {
      case x: Rechargeable => Some(x)
      case _ => None
    }
    val d = rechargeable.flatMap { s =>
      (s.rechargeType match {
        case Left(x) => x.apply()
        case Right(x) => x
      }) match {
        case OnTimer(x) => Some(x)
      }
    }
    val q = rechargeable match {
      case Some(x) => x.quantity
      case _ => None
    }
    val resultTimeValue =
      if (d.isDefined) { Some(d.get.toSeconds) }
      else { None }
    ChargeInfo(resultMaxCharges, q.toJavaPrimitive, d.toJava)
  }

}
