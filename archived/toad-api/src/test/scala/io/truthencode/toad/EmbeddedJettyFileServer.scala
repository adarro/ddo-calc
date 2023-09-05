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

import io.truthencode.toad.verticle.VertxService.getClass
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ResourceHandler
import org.slf4j.{Logger, LoggerFactory}

import java.nio.file.NoSuchFileException

object EmbeddedJettyFileServer {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  // scalastyle:off magic.number
  case class SimpleServer(baseDir: String = ".", port: Int = 9090, autoShutDown: Boolean = true) {
    // scalastyle:on
    val server = new Server(port)
    val resourceHandler = new ResourceHandler
    def start(): SimpleServer = {
      logger.warn("Starting Embedded Jetty")
      if (server.isStarted || server.isStarting || server.isStopping) {
        logger.warn(s"Start was requested, but server is ${server.getState} ")
      } else {
        resourceHandler.setDirAllowed(true)
        resourceHandler.setResourceBase(baseDir)
        server.setHandler(resourceHandler)
        server.setStopAtShutdown(autoShutDown)
        try {
          server.start()
          server.join()
        } catch {
          case nsf: NoSuchFileException =>
            logger.error("Failed to start Server", nsf)

          case ex: Exception => logger.error("Failed to start server", ex)
        }
      }

      logger.warn("Fluent return Start")
      this
    }

    def stop(): SimpleServer = {
      logger.warn("Stopping Embeded Jetty server")
      synchronized {
        logger.warn("inside synchronized block (STOP)")
        if (!server.isStopped) {
          server.stop()
        }
      }
      logger.warn("Fluent return Stop")
      this
    }
  }

}
