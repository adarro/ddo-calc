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
package io.truthencode.ddo.subscription.http
import io.truthencode.ddo.subscription.service.StatusService
import wvlet.airframe.http.Router
import wvlet.airframe.http.finagle._
import wvlet.log.LogSupport

case class AdminWeb(port: Int = defaultHttpAdminPort) extends LogSupport {

  // Define API routes. This will read all @Endpoint annotations in MyApi
  // You can add more routes by using `.add[X]` method.
  val router = Router.add[MyApi]
  lazy val fServer = Finagle.server
    .withPort(port)
    .withRouter(router)
  val adminHttpServerId = "adminHttp"
  def startServer(): Unit = {
    info(s"Starting Admin server on port $port")
    fServer.start { server =>
      // Finagle http server will start here
      // To keep running the server, run `server.waitServerTermination`:
      StatusService.registerServer(adminHttpServerId, server)
      server.waitServerTermination
    }
    info("Leaving AW startServer")
  }
  // The server will terminate here

}
