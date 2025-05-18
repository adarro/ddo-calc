/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GreetingVerticle.scala
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
package io.truthencode.ddo.etl.rest

import com.typesafe.scalalogging.LazyLogging
import io.vertx.ext.web.Router
import io.vertx.lang.scala.ImplicitConversions.vertxFutureToScalaFuture
import io.vertx.lang.scala.ScalaVerticle
import jakarta.enterprise.context.ApplicationScoped

import scala.concurrent.Future
import scala.language.implicitConversions

@ApplicationScoped
class GreetingVerticle extends ScalaVerticle, LazyLogging {

  override def asyncStart: Future[Unit] = {
    // Create a router to answer GET-requests to "/hello" with "world"
    val router = Router.router(vertx)
    router.get("/greeting").handler(_.response.end("world"))

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8666, "0.0.0.0")
      .mapEmpty[Unit]()
      .onSuccess(_ => logger.info("HttpVerticle started -> http://localhost:8666/hello"))
  }

}
