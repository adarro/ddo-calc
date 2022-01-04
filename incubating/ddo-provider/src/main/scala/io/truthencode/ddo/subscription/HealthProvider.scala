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
import io.truthencode.ddo.model.protocol.{ChangeType, ChangeValueInt}
import org.apache.pulsar.client.api.Schema

import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.annotation.{PostConstruct, PreDestroy}
import scala.util.{Failure, Success}

/**
 * Provides value for an Ability Score (i.e. Strength / Wisdom)
 *
 * @param name
 *   Name / ID of Ability
 */
case class HealthProvider(name: String, override val client: PulsarAsyncClient, topic: Topic)
  extends PulsarEnabled with AtomicIntChangeSupport {
  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global

  implicit val schema: Schema[ChangeValueInt] = Schema.AVRO(classOf[ChangeValueInt])

  @PostConstruct
  def startUp(): Unit = {
    logger.info(s"Started up Health Provider $name")
    addPropertyChangeListener(listener = valueChangeListener)

  }

  @PreDestroy
  def shutDown(): Unit = {
    logger.info(s"Shutting down health provider $name")
    client.close()
  }

  private lazy val valueChangeListener = new PropertyChangeListener {
    override def propertyChange(evt: PropertyChangeEvent): Unit = {
      val n = evt.getPropertyName
      val ov = evt.getOldValue.asInstanceOf[Int]
      val nv = evt.getNewValue.asInstanceOf[Int]
      val ct = if (ov < nv) ChangeType.INCREASE else ChangeType.DECREASE
      val change = ChangeValueInt(n, nv, ov, ct)
      changeNotify(change)

    }
  }

  def producer: Producer[ChangeValueInt] = client.producer(ProducerConfig(topic))

  private def changeNotify(change: ChangeValueInt): Unit = {
    val t = producer.sendAsync(change)
    val f: Unit = t.runToFuture.onComplete { fx =>
      fx match {
        case Success(x) => logger.info(s"$name Message with ID ${x.entryId} successfully sent")
        case Failure(e) => logger.error(s"Message failed Error ${e.getMessage}", e)
      }
      producer.close()
    }
  }
}
