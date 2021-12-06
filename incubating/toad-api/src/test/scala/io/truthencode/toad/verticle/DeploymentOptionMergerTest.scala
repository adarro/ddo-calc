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
package io.truthencode.toad.verticle

import com.typesafe.config._
import io.vertx.core.DeploymentOptions
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class DeploymentOptionMergerTest extends AnyFunSpec with Matchers {

  trait DeploymentConfiguration {
    val dOptions = new DeploymentOptions()
    val cfgNone: Option[Config] = None
    val cfgDefault = Option(io.truthencode.toad.config.cfg)
  }

  describe("mergeConfig") {
    it("should add a default configuration to deployOptions") {
      new DeploymentConfiguration {
        val actual = dOptions.mergeConfig(cfgDefault)
        actual.getConfig should not be empty
      }
    }

    it("should add a default configuration to deployOptions if none supplied") {
      new DeploymentConfiguration {
        assume(Option(dOptions.getConfig).isEmpty)
        val actual = dOptions.mergeConfig(cfgNone)
        actual.getConfig should not be empty
      }
    }
  }
}
