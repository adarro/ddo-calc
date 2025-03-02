/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BonusTypeValidationTest.scala
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
package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class BonusTypeValidationTest extends AnyFunSpec with Matchers with LazyLogging {
  final val invalidBonusType = "SpontaneousErection" // Rogue 'Builder' :)
  describe("Bonus Type Validation") {
    they("Should support all valid bonus types") {
      bonusTypeNames.foreach { v =>
        validateBonusType(v).isSuccess shouldBe true
      }
    }
    they("should reject invalid bonus types") {
      validateBonusType(invalidBonusType).isFailure shouldBe true
    }
  }

}
