/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EtlWebClient.scala
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
package io.truthencode.ddo.etl.internal

import com.typesafe.scalalogging.LazyLogging
import io.smallrye.context.SmallRyeThreadContext
import io.smallrye.context.api.{ManagedExecutorConfig, NamedInstance, ThreadContextConfig}
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.infrastructure.Infrastructure
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.mutiny.core.Vertx
import io.vertx.mutiny.ext.web.client.{HttpResponse, WebClient as VxWebClient}
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.context.{ManagedExecutor, ThreadContext}

@ApplicationScoped
class EtlWebClient extends LazyLogging {
  @Inject var vertx: Vertx = null // Inject the managed io.vertx.mutiny.core.Vertx instance

  // Custom configured ManagedExecutor with name  // Custom configured ManagedExecutor with name
  @Inject
  @ManagedExecutorConfig(maxAsync = 2, maxQueued = 3, cleared = Array(ThreadContext.ALL_REMAINING))
  var sharedConfiguredExecutor: ManagedExecutor = null

  // Custom ThreadContext with a name    // Custom ThreadContext with a name
  @Inject
  @ThreadContextConfig(unchanged = Array(ThreadContext.ALL_REMAINING))
  var sharedConfiguredThreadContext: SmallRyeThreadContext = null

  val timeoutMillis = 5000
  @ConfigProperty(name = "quarkus.http.test-port", defaultValue = "8081")
  var port: String = null

  def foo = Infrastructure.getDefaultExecutor
  private var client: VxWebClient = null

  @PostConstruct def init(): Unit = {
    logger.debug("Initializing EtlWebClient")
    client = VxWebClient.create(
      vertx,
      new WebClientOptions()
        .setDefaultHost("localhost")
        .setDefaultPort({
          Option(port) match {
            case Some(x) => x.toInt
            case None => throw new IllegalArgumentException("Port not set")
          }
        })
    )
  }

  def call(path: String): Uni[String] = client.get(path).send.onItem.transform(_.bodyAsString)

}
