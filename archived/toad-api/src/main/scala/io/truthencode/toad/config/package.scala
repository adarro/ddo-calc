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
package io.truthencode.toad

import com.typesafe.config._
import configs.Result.{Failure, Success}
import configs.syntax._
import io.truthencode.toad.actor.Actors
import io.truthencode.toad.verticle.VertxService
import io.truthencode.toad.verticle.VertxService.getClass
import io.vertx.core.json.JsonObject
import org.fusesource.scalate.TemplateEngine
import org.slf4j.{Logger, LoggerFactory}

import scala.language.implicitConversions

/**
 * Stores and / or retrieves general configuration information used by the sub-systems. Also used as
 * a base reference for Implicit objects.
 */
package object config {

  /**
   * Implicit services such as the template engine, Default Akka Actor system, Vertx main instance
   * etc.
   */
  object Implicits {
    implicit lazy val engine = new TemplateEngine
    implicit lazy val system = Actors.actorSystem
    // Clustering orchestration for Vertx / Camel
    // implicit val mgr = new HazelcastClusterManager
    implicit lazy val vertx = VertxService.startVertx()

  }

  /**
   * Common convenience utilities for java Null / JSon Stringify etc
   */
  object CommonImplicits {
    implicit def jsonToString(j: JsonObject): String = j.encodePrettily()

    implicit def configToJsonObject(cfg: ConfigValue): JsonObject =
      new JsonObject(cfg.render(ConfigRenderOptions.concise()))

    implicit def toOpt[T](x: T): Option[T] = Option[T](x)

    implicit class ConfigValeUtil(c: ConfigValue) {
      def asJson: JsonObject = configToJsonObject(c)
    }

    implicit class ConfigObjectUtil(c: ConfigObject) {
      def asJson: JsonObject = new JsonObject(c.render(ConfigRenderOptions.concise()))

      def asJson(k: String): JsonObject = c.toConfig.getValue(k)
    }

  }
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  val cfg = ConfigFactory.load("defaults")
  val (serverIp, serverPort, hostName) =
    cfg.get[ServerInfo]("server-info") match {
      case Success(x) => (x.ip, x.port, s"${x.hostName}:${x.port}")
      case Failure(x) =>
        logger.error("Error reading Server configuration", x.configException)
        ("127.0.0.1", "8080", "localhost:8080")
    }
}
