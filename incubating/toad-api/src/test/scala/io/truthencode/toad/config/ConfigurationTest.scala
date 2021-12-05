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
package io.truthencode.toad.config

import com.typesafe.config.ConfigFactory
import io.truthencode.toad.config.CommonImplicits._
import io.truthencode.toad.verticle.DeploymentOptionMerger
import io.vertx.core.DeploymentOptions
import io.vertx.core.json.JsonObject
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.jdk.CollectionConverters._

/**
 * Created by adarr on 7/19/2016.
 */
class ConfigurationTest extends AnyFunSpec with Matchers {
  describe("Typesafe Configuration") {
    it("should interpolate with Vert.x Configuration (Json)") {
      val key = "testwebserver"
      val control = new JsonObject().put(
        key,
        new JsonObject()
          .put("bind-address", "127.0.0.1")
          .put("bind-port", 8080)
          .put("webroot", "webroot")
          .put("ssl", true))
      val expected = control.getJsonObject(key)
      val actual: JsonObject = ConfigFactory.load("sample").getValue(key)
      actual shouldEqual expected
    }
    it("should add typesafeconfig to deploymentoptions") {
      val dOpts = new DeploymentOptions().mergeConfig()
      dOpts.getConfig should not be empty
    }

    it("should be filterable from java.util.Maps") {
      val key = "webserver"
      logger.info("config keys =>")
      //      cfg.entrySet().asScala foreach {
      //        f => logger.info(s"k:${f.getKey}")
      //      }
      val filterMap = cfg
        .entrySet()
        .asScala
        .filter { x => x.getKey == key }
        .map(entry => new JsonObject().put(entry.getKey, entry.getValue))

      val forComprehension =
        for { entry <- cfg.entrySet().asScala if entry.getKey.equals(key) } yield new JsonObject()
          .put(entry.getKey, entry.getValue)
      filterMap shouldEqual forComprehension
    }

  }

}
