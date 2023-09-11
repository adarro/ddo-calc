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
package io.truthencode.ddo.subscription

import com.sksamuel.pulsar4s.monixs.MonixAsyncHandler
import com.sksamuel.pulsar4s.{ConsumerConfig, PulsarAsyncClient, Subscription, Topic}
import org.apache.pulsar.client.api.Schema
import wvlet.log.LogSupport

import javax.annotation.PostConstruct
import scala.util.{Failure, Success}

/**
 * Provides current values for a specific skill. Requires change notification of relevant stat.
 *
 * @param name
 *   Name / ID of skill (i.e. Balance, Haggle)
 * @param abilityProvider
 *   source for dependent score.
 */
case class SkillProvider(
  name: String,
  abilityProvider: AbilityProvider,
  override val client: PulsarAsyncClient,
  topic: Topic)
  extends LogSupport with PulsarEnabled with AutoCloseable {

  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global
  implicit val schema: Schema[String] = Schema.STRING
  @PostConstruct
  def subscribe(): Unit = {
    info(s"$name skill would be subscribing to ${abilityProvider.name} events here")
    //  readStuff()
  }

  override def close(): Unit = {
    info(s"${name}Provider Closing")
    client.close()
    // likely more useful once we have something to close
  }

}
