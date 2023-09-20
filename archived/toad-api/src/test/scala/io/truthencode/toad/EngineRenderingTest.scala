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

import io.truthencode.toad.config.Implicits.engine
import io.truthencode.toad.config.{serverIp, serverPort}
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.slf4j.{Logger, LoggerFactory}

class EngineRenderingTest extends AnyFunSpec with Matchers {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  lazy val fixture = new {

    // val engine = engine
    lazy val api = s"http://$serverIp:$serverPort/api"
    lazy val other = s"http://$serverIp:$serverPort/web"
    lazy val host = s"$serverIp:$serverPort"
  }

  val testPort = 9090
  private def testServer = {
    val vertx = Vertx.vertx
    val webRoot: String = "webroot"
    val router = Router.router(vertx)
    router.route("/template/*").handler(StaticHandler.create(webRoot).setIndexPage("index.html"))
    vertx
      .createHttpServer(new HttpServerOptions().setPort(testPort))
      .requestHandler(router)
      .listen()
      .onSuccess { ar =>
        logger.info(s"Started Test Vertx service on ${ar.actualPort()}")
      }

  }

  describe("The template engine") {
    it("Should support and find jade") {

      logger.info("in initial jade test")
      val f = fixture

      logger.info(s"searching for scalate templates in ${engine.templateDirectories.mkString}")

      logger.warn(s"engine working directory ${engine.workingDirectory.getAbsolutePath}")
      val data = engine.layout("/template/chat2.html.jade")
      logger.info(data)
    }

    it("Should support and find jade variables") {
      import scala.language.reflectiveCalls
      val f = fixture

      val data = engine.layout("/template/chat2.jade", Map("host" -> f.host))
      logger.info(data)
      data shouldNot contain("mysite")
    }
  }
}
