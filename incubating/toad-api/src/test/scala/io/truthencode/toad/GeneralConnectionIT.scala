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

import io.lemonlabs.uri.typesafe.dsl.{stringToUriDsl, urlToUrlDsl}
import io.truthencode.toad.config.{serverIp, serverPort}
import io.truthencode.toad.verticle.{DeploymentOptionMerger, WebSSLCapableServerVerticle}
import io.vertx.core.DeploymentOptions
import io.vertx.core.http.HttpClientOptions
import io.vertx.ext.web.client.WebClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.slf4j.{Logger, LoggerFactory}

import scala.language.reflectiveCalls

class GeneralConnectionIT extends AnyFunSpec with Matchers {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  lazy val fixture = new {

    io.truthencode.toad.config.Bootstrap.init()
    lazy val root = s"http://$serverIp:$serverPort"

    def subPath(p: String, prependSlash: Boolean = false): String = {
      if (prependSlash)
        s"$root/p"
      else
        s"$root$p"
    }

    def subPath(p: String): String = subPath(p, prependSlash = false)

    lazy val api = subPath("/api")
    lazy val other = subPath("/web")
  }

  describe("non-API filter") {
    it("Should reject websocket calls on unauthorized paths")(pending)
  }

  describe("Vertx connections") {
    ignore("should populate a context") {
      val vert = io.truthencode.toad.config.Implicits.vertx
      val ctx = vert.getOrCreateContext()
      ctx.config() should not be empty
    }
    it("Should support SSL") {
      import io.truthencode.toad.config.Implicits._
      val f = fixture
      val sslVerticle = classOf[WebSSLCapableServerVerticle].getName
      logger.info("Launching SSL Verticle")
      vertx.deployVerticle(sslVerticle, new DeploymentOptions().mergeConfig())
      logger.info("Launched vert")
      val cOpts = new HttpClientOptions()
        .setDefaultPort(8080)
        .setTrustAll(true)
      val client = vertx.createHttpClient(cOpts)
      val webClient = WebClient.wrap(client)
      webClient
        .get(f.subPath("/"))
        .send()
        .onSuccess(response => {
          logger.info(response.bodyAsString())
        })
    }
  }

  describe("Non-existent resources") {
    it("Should return a NOT_IMPLEMENTED error") {
      // FIXME web routes should return 404 for not found and Not implemented for unsupported
      val f = fixture
      import f._
      val uri = other / "weapons" ? ("p1" -> "one") & ("p2" -> 2) & ("p3" -> true)
      val client = HttpClients.createDefault()
      val response = client.execute(new HttpGet(uri.toJavaURI))
      val returnCode = response.getStatusLine.getStatusCode
      returnCode should equal(404)
    }
  }
}
