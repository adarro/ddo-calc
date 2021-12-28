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

import com.twitter.util.Future
import io.truthencode.ddo.subscription.service.StatusService
import wvlet.airframe.http.finagle.FinagleServer
import wvlet.airframe.http.{Endpoint, HttpMessage, HttpMethod}
import wvlet.log.LogSupport

object MyApi {
  case class User(name: String)
  case class ServerCommand(cmd: String)
  case class NewUserRequest(name: String)
  case class ServerInfo(version: String, ua: Option[String])
}

// [Optional] Specify a common prefix for all endpoints
@Endpoint(path = "/v1")
trait MyApi extends LogSupport {
  import MyApi._

  @Endpoint(method = HttpMethod.GET, path = "/admin/:cmd")
  def serverCommand(request: ServerCommand): Unit = {
    if (request.cmd == "Stop") {
      StatusService.getService("adminHttp") match {
        case Some(x: FinagleServer) =>
          warn("Shutting down server")
          x.close()
        case _ => info(s"Received Server Command: ${request.cmd}")
      }
    }

  }

  // Binding http path parameters (e.g., :name) to method arguments
  @Endpoint(method = HttpMethod.GET, path = "/user/:name")
  def getUser(name: String): User = User(name)

  // Receive a JSON request body {"user":"leo"} to generate NewUserRequest instance
  @Endpoint(method = HttpMethod.POST, path = "/user")
  def createNewUser(request: NewUserRequest): User = User(request.name)

  // To read http request headers, add a method argument of HttpMessage.Request type
  @Endpoint(method = HttpMethod.GET, path = "/info")
  def getInfo(request: HttpMessage.Request): ServerInfo = {
    ServerInfo("1.0", request.userAgent)
  }

  // It is also possible to receive backend server specific Request type
  @Endpoint(method = HttpMethod.GET, path = "/info2")
  def getInfo(request: com.twitter.finagle.http.Request): ServerInfo = {
    ServerInfo("1.0", request.userAgent)
  }

  // Returning Future[X] is also possible.
  // This style is convenient when you need to call another service that returns Future response.
  @Endpoint(method = HttpMethod.GET, path = "/info_f")
  def getInfoFuture(request: HttpMessage.Request): Future[ServerInfo] = {
    Future.value(ServerInfo("1.0", request.userAgent))
  }

//    // It is also possible to return custom HTTP responses
//    @Endpoint(method = HttpMethod.GET, path = "/custom_response")
//    def customResponse: Response = {
//        val response = Reponse()
//        response.contentString = "hello airframe-http"
//        response
//    }

  import com.twitter.io.Reader
  // If you return a Reader, the response will be streamed (i.e., it uses less memory)
  @Endpoint(method = HttpMethod.GET, path = "/stream_response")
  def streamingResponse: Reader[User] = {
    Reader.fromSeq(Seq(User("leo"), User("yui")))
  }
}
