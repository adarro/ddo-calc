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

//import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s.avro.avroSchema
import com.sksamuel.pulsar4s.monixs.MonixAsyncHandler
import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.protocol.{ChangeType, ChangeValueInt}

import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.annotation.{PostConstruct, PreDestroy}
import scala.util.{Failure, Success}

/**
 * Provides value for an Ability Score (i.e. Strength / Wisdom)
 *
 * @param name
 *   Name / ID of Ability
 */
case class AbilityProvider(name: String, override val client: PulsarAsyncClient, topic: Topic)
  extends PulsarEnabled with LazyLogging with AtomicIntChangeSupport {
  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global

  @PostConstruct
  def startUp(): Unit = {
    addPropertyChangeListener(listener = valueChangeListener)
    logger.info(s"Started up Ability Provider $name")
    // sendThings()
  }

  @PreDestroy
  def shutDown(): Unit = {
    client.close()
  }
  private lazy val valueChangeListener = new PropertyChangeListener {
    override def propertyChange(evt: PropertyChangeEvent): Unit = {
      val n = evt.getPropertyName
      val ov = evt.getOldValue.asInstanceOf[Int]
      val nv = evt.getNewValue.asInstanceOf[Int]
      val ct = if (ov < nv) ChangeType.INCREASE else ChangeType.DECREASE
      val change = ChangeValueInt(n, nv, ov, ct)
      //    sendThings(change)

    }
  }

  def producer: Producer[ChangeValueInt] = client.producer[ChangeValueInt](ProducerConfig(topic))

  lazy val inboundTopic: String = s"${name}-changes"
  lazy val inboundSubscription: String = s"${name}-change-handler"
  def readStuff(): Unit = {
    val consumer = client.consumer[ChangeValueInt](
      ConsumerConfig(topics = Seq(topic), subscriptionName = Subscription(inboundSubscription)))
    consumer.seekEarliest()
    val t = consumer.receiveAsync
    t.runToFuture.onComplete {
      case Success(x) => logger.info(s"Message with ID ${x.messageId} successfully received")
      case Failure(e) => logger.error(s"Error", e)
    }
  }
}
