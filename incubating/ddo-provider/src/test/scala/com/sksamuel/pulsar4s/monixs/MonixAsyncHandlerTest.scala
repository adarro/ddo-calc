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
// https://raw.githubusercontent.com/CleverCloud/pulsar4s/master/pulsar4s-monix/src/test/scala/com/sksamuel/pulsar4s/monixs/MonixAsyncHandlerTest.scala
package com.sksamuel.pulsar4s.monixs

import com.sksamuel.pulsar4s._
import io.truthencode.ddo.subscription.TestContainers
import io.truthencode.ddo.test.tags.IntegrationTest
import org.apache.pulsar.client.api.Schema
import org.scalatest.{BeforeAndAfterAll, Ignore}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.util.UUID
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * This test simply validates library use and environment. It is currently failing when ran with
 * other tests using the Pulsar TestContainer. It passes running stand-alone and with an actual
 * (non-testcontainers.org) pulsar environment (i.e. full docker or assumed production server)
 */
@Ignore
class MonixAsyncHandlerTest extends AnyFunSuite with Matchers with BeforeAndAfterAll {

  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global

  implicit val schema: Schema[String] = Schema.STRING

  override protected def beforeAll(): Unit = TestContainers.pulsarTestContainer.start()

  private lazy val client: PulsarAsyncClient = PulsarClient(
    PulsarClientConfig(
      serviceUrl = TestContainers.pulsarServiceUrl,
      enableTransaction = Some(true)
    ))
  val topic: Topic = Topic("persistent://sample/standalone/ns1/monix_" + UUID.randomUUID())

  override def afterAll(): Unit = {
    client.close()
    TestContainers.pulsarTestContainer.stop()
  }

  test("async producer should use monix", IntegrationTest) {
    val producer = client.producer(ProducerConfig(topic))
    val t = producer.sendAsync("wibble")
    val f = t.runToFuture
    Await.result(f, Duration.Inf) should not be null
    producer.close()
  }

  test("async consumer should use monix", IntegrationTest) {
    val consumer = client.consumer(
      ConsumerConfig(
        topics = Seq(topic),
        subscriptionName = Subscription("mysub_" + UUID.randomUUID())))
    consumer.seekEarliest()
    val t = consumer.receiveAsync
    val f = t.runToFuture
    new String(Await.result(f, Duration.Inf).data) shouldBe "wibble"
    consumer.close()
  }

  test("async consumer getMessageById should use monix", IntegrationTest) {
    val consumer = client.consumer(
      ConsumerConfig(
        topics = Seq(topic),
        subscriptionName = Subscription("mysub_" + UUID.randomUUID())))
    consumer.seekEarliest()
    val receive = consumer.receiveAsync
    val valueFuture = receive.runToFuture
    val value = Await.result(valueFuture, Duration.Inf)
    val t = consumer.getLastMessageIdAsync
    val rFuture = t.runToFuture
    val r = Await.result(rFuture, Duration.Inf)
    r.entryId shouldBe value.messageId.entryId
    r.partitionIndex shouldBe value.messageId.partitionIndex
    consumer.close()
  }

  ignore(
    "producer and consumer can execute a transaction using cats (Transactions not enabled)",
    IntegrationTest) {
    val producer = client.producer(ProducerConfig(topic, sendTimeout = Some(Duration.Zero)))
    val consumer = client.consumer(
      ConsumerConfig(
        topics = Seq(topic),
        subscriptionName = Subscription("mysub_" + UUID.randomUUID)))
    consumer.seekEarliest()
    val msgIdIO = client.transaction.withTimeout(1.second).runWith { implicit txn =>
      for {
        msg <- consumer.receiveAsync
        msgId <- producer.tx.sendAsync(msg.value + "_test")
        _ <- consumer.tx.acknowledgeAsync(msg.messageId)
      } yield msgId
    }
    Await.result(msgIdIO.runToFuture, Duration.Inf)
    consumer.close()
  }
}
