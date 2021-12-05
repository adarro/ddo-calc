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
package io.truthencode.toad.actor

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Actors {
  val config = ConfigFactory.load()
  val actorSystem = ActorSystem("openshift", config.getConfig("staticFiles").withFallback(config))

  // val handlerConfig = MyStaticHandlerConfig(actorSystem)

//  val staticContentHandlerRouter = actorSystem.actorOf(Props(new StaticContentHandler(handlerConfig))
//    .withRouter(FromConfig()).withDispatcher("my-pinned-dispatcher"), "static-file-router")
//  object MyStaticHandlerConfig extends ExtensionId[StaticContentHandlerConfig] with ExtensionIdProvider {
//    override def lookup = MyStaticHandlerConfig
//    override def createExtension(system: ExtendedActorSystem) =
//      new StaticContentHandlerConfig(system.settings.config, "my-static-content-handler")
//  }
}
