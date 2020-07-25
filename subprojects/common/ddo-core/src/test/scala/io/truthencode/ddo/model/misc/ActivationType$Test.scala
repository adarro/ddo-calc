/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.misc

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.activation.ActivationType
import io.truthencode.ddo.activation.ActivationType.Passive
import io.truthencode.ddo.model.effect.TriggerEvent
import org.scalatest.{FunSpec, Matchers}

import scala.languageFeature.postfixOps

/**
  * Created by adarr on 1/28/2017.
  */
class ActivationType$Test extends FunSpec with Matchers with LazyLogging {

  describe("ActivationType$Test") {
    it("should generate case classes") {
      val act = ActivationType.Active(TriggerEvent.OnAttackRoll)
      act.entryName should be("OnAttackRoll")
    }
    it("should withNameLowercaseOnly") {
      ActivationType.withNameLowercaseOnly("passive") should be(Passive)
    }

    it("should withNameInsensitiveOption") {}

    it("should withNameInsensitive") {}

    it("should withNameUppercaseOnly") {
      logger.info("here is some logging")
    }

    it("should generated known values") {

      noException should be thrownBy ActivationType.values
      ActivationType.values should not be empty
      ActivationType.values.foreach(x => logger.info(x.entryName))
    }

    it("should indexOf") {}

    it("should namesToValuesMap") {}

    it("should upperCaseNameValuesToMap") {}

    it("should valuesToIndex") {}

    it("should withNameUppercaseOnlyOption") {}

    it("should withNameLowercaseOnlyOption") {}

    it("should findValues") {}

    it("should lowerCaseNamesToValuesMap") {}

    it("should withName") {}

    it("should withNameOption") {}

  }
}
