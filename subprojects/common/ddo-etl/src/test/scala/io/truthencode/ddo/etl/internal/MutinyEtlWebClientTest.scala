/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: MutinyEtlWebClientTest.scala
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
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.context.SmallRyeThreadContext
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.junit.jupiter.api.{Tag, Test}

import java.time.Duration
import scala.util.Using

@QuarkusTest
class MutinyEtlWebClientTest extends LazyLogging {

  @Inject
  var client: EtlWebClient = null

//  @Inject
//  @NamedInstance("myExecutor") val sharedConfiguredExecutor: ManagedExecutor = null

  // Custom ThreadContext with a name    // Custom ThreadContext with a name
  @Inject
  var sharedConfiguredThreadContext: SmallRyeThreadContext = null

  @ConfigProperty(name = "timeoutMillis", defaultValue = "8082")
  var timeoutMillisString: String = null

  private def timeoutMillis = {
    val ex = new IllegalArgumentException(
      "Invalid port supplied, please set a valid port or omit for default")
    Option(timeoutMillisString) match
      case Some(value) =>
        Option(value.toLong) match
          case Some(value) => value
          case None => throw ex
      case None =>
        throw ex
  }

  @Tag("Unit")
  @Test
  def BasicCall(): Unit = {
    val path = "http://localhost:8080/q/health"
    val expected = "Hello Darkseid"
    Using(SmallRyeThreadContext.withThreadContext(sharedConfiguredThreadContext)) { ctx =>
      val uni = client.call(path) // .emitOn(Infrastructure)
      val resp = uni.subscribe().withSubscriber(UniAssertSubscriber.create())
      val theWait = resp.awaitItem(Duration.ofMillis(timeoutMillis))
      val ai = theWait.assertItem(expected)
      logger.error("bcall value check " + ai.toString)
      uni
        .subscribe()
        .withSubscriber(UniAssertSubscriber.create())
        .awaitItem(Duration.ofMillis(timeoutMillis))
        .assertFailed()

    }

  }
}
