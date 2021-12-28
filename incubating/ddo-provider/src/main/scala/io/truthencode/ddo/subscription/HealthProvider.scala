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
import com.sksamuel.pulsar4s.{Producer, ProducerConfig, PulsarAsyncClient, Topic}
import org.apache.pulsar.client.api.Schema
import wvlet.log.LogSupport

import javax.annotation.{PostConstruct, PreDestroy}
import scala.util.{Failure, Success}

/**
 * Provides value for an Ability Score (i.e. Strength / Wisdom)
 *
 * @param name
 *   Name / ID of Ability
 */
case class HealthProvider(name: String, override val client: PulsarAsyncClient, topic: Topic)
  extends PulsarEnabled with LogSupport {
  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global

  implicit val schema: Schema[String] = Schema.STRING
  def readCurrentValue: () => Int = { () => 0 }

  @PostConstruct
  def startUp(): Unit = {
    info(s"Started up Health Provider $name")
    sendThings()
  }

  @PreDestroy
  def shutDown(): Unit = {
    client.close()
  }

  def producer: Producer[String] = client.producer(ProducerConfig(topic))

  def sendThings(): Unit = {
    val t = producer.sendAsync(s"Ability $name wibble!")
    t.runToFuture.onComplete { fx =>
      fx match {
        case Success(x) => info(s"Message with ID ${x.entryId} successfully sent")
        case Failure(e) => error(s"Error", e)
      }
      producer.close()
    }
  }
}
