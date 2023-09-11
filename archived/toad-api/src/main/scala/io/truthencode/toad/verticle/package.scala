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

import _root_.io.vertx.core.{DeploymentOptions, Handler}
import com.typesafe.config._
import io.truthencode.toad.config.CommonImplicits._
import io.truthencode.toad.config.cfg
import io.truthencode.toad.verticle.MergeOption._
import io.vertx.core.json.JsonObject
import org.slf4j.{Logger, LoggerFactory}

import scala.language.implicitConversions

/**
 * The verticle package contains VertX related utilities and Verticles as well as any Vertx specific
 * implicits.
 */
package object verticle {

  /**
   * Vert.x specific implicits mostly used for Java / Scala compatibility.
   */
  object Event2HandlerImplicits {

    /**
     * Maps java events to scala handlers used mostly to transliterate Vert.x Java lambdas to Scala.
     *
     * @example
     *   {{{
     *   // Java
     *   Vertx.clusteredVertx(options, evt -> {
     *   if (evt.succeeded()) //do something with vertx
     *       Vertx vertx = evt.result();
     *   else
     *       log.error("insightful message",res.cause());
     *   }
     *   // Scala
     *   import io.truthencode.toad.verticle.Event2HandlerImplicits._
     *   Vertx.clusteredVertx(options, (evt: AsyncResult[Vertx]) => {
     *   if (evt.succeeded()) // do something with vertx
     *     val v = evt.result()
     *   else
     *     log.error("insightful message",res.cause)
     *     }
     *   }}}
     * @note
     *   also works where for AsyncResult[Unit]
     * @param event
     *   function to fire
     * @tparam T
     *   generally a parameterized asynchronous event
     * @tparam S
     *   payload of the T
     * @return
     *   a mapped handler
     */
    implicit def asyncToHandler[T, S](event: (T) => S): Handler[T] = new Handler[T] {
      override def handle(dEvent: T): Unit = event(dEvent)
    }
    implicit def functionToHandler[A](f: A => Unit): Handler[A] = new Handler[A] {
      override def handle(event: A): Unit = {
        f(event)
      }
    }
  }

  implicit class DeploymentOptionMerger(helper: DeploymentOptions) {
    val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
    def mergeConfig(
      opt: Option[Config] = None,
      mergeOption: MergeOption = MergeOption.MERGE): DeploymentOptions = {
      val json: JsonObject = opt match {
        case Some(x) => x.root
        case None => cfg.root()
      }

      mergeOption match {
        case REPLACE => helper.setConfig(json)
        case MERGE =>
          Option(helper.getConfig) match {
            case Some(x) => helper.setConfig(helper.getConfig.mergeIn(json))
            case _ =>
              logger.warn(
                "Specified option to merge existing config will be a REPLACE operation because the base configuration is null")
              helper.setConfig(json)
          }
      }
    }
  }

}
