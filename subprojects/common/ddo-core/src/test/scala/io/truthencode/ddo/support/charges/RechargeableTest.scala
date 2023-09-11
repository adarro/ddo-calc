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
package io.truthencode.ddo.support.charges

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.charges.Interval.OnTimer
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.time.Duration

class RechargeableTest extends AnyFunSpec with Matchers with LazyLogging {

  describe("A rechargeable item") {
    they("should handle 3 charges, you regain 1 charge every 12 seconds") {
      val expectedText = "should handle 3 charges, you regain 1 charge every 12 seconds"

      val expectedMaxCharges: Int = 3
      val expectedQuantity: Option[Int] = Some(1)
      val expectedInterval: Duration = Duration.ofSeconds(12)

      class Simple() {
        val charge: Rechargeable =
          TimedCharge(expectedQuantity, expectedMaxCharges, expectedInterval)
      }
      val s = new Simple()
      val resultMaxCharges = s.charge.maxCharges
      val resultQuantity = s.charge.quantity.getOrElse(-1)
      val d = (s.charge.rechargeType match {
        case Left(x) => x.apply()
        case Right(x) => x
      }) match { case OnTimer(x) => x }
      val resultTimeValue = d.toSeconds
      val resultText =
        s"should handle $resultMaxCharges charges, you regain $resultQuantity charge every $resultTimeValue seconds"
      resultText shouldEqual expectedText

    }
  }
}
