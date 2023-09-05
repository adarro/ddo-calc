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

import com.sksamuel.pulsar4s._
import com.sksamuel.pulsar4s.avro.avroSchema
import com.sksamuel.pulsar4s.monixs.MonixAsyncHandler
import io.truthencode.ddo.model.protocol.ChangeValueInt
import io.truthencode.ddo.test.tags.IntegrationTest
import org.mockito.scalatest.MockitoSugar
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.testcontainers.junit.jupiter.Testcontainers
import wvlet.log.LogSupport

import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import java.util.UUID
import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt, FiniteDuration}
import scala.language.postfixOps

@Testcontainers(disabledWithoutDocker = true)
class AbilityProviderTest
  extends AnyFunSpec with BeforeAndAfterAll with MockitoSugar with Matchers with LogSupport {
  import monix.execution.Scheduler.Implicits.global
  lazy val serviceUrl: String =
    TestContainers.pulsarTestContainer.getPulsarBrokerUrl // "pulsar://localhost:6650"
  lazy val pcfg: PulsarClientConfig = PulsarClientConfig(serviceUrl)
  lazy val pc: PulsarAsyncClient = PulsarClient(pcfg)
  val nv = 5
  val changeValueKey = "IntValue"

  val maxTimeOut: FiniteDuration = Duration.create(15, TimeUnit.SECONDS)
  describe("An ability") {
    it("Should support atomic updates", IntegrationTest) {
      val s = "Strength"
      val topic = Topic(genAttrPrefix(s))
      val ap = AbilityProvider(s, pc, topic)
      val expectedValue = 5
      val expectedPrevValue = ap.readCurrentValue

//      val listener = mock[PropertyChangeListener]
//      ap.addPropertyChangeListener("IntValue", listener)
      ap.update(nv)
      ap.readCurrentValue shouldEqual expectedValue
      ap.readPreviousValue shouldEqual expectedPrevValue
      //  verify(listener,times(1))

    }
    it("should support property change notifications", IntegrationTest) {
      val s = "Dexterity"
      val topic = Topic(genAttrPrefix(s))
      val ap = AbilityProvider(s, pc, topic)
      val expectedValue = 5

      val expectedPrevValue = ap.readCurrentValue
      // Adding to verify listener was added and called.
      var fired = false
      val listener = new PropertyChangeListener {
        override def propertyChange(evt: PropertyChangeEvent): Unit = {
          val env = evt.getNewValue.asInstanceOf[Int]
          val eov = evt.getOldValue.asInstanceOf[Int]
          val eName = evt.getPropertyName
          env shouldEqual expectedValue
          eov shouldEqual expectedPrevValue
          eName shouldEqual changeValueKey
          fired = true

        }
      }
      ap.addPropertyChangeListener(changeValueKey, listener)
      val result = ap.update(expectedValue)
      Await.result(Await.result(result, 15.seconds).runToFuture, 15.seconds)

      fired shouldBe true
    }
  }

  it("should send notification messages") {
//    implicit val schema: Schema[ChangeValueInt] = Schema.AVRO(classOf[ChangeValueInt])
    import MonixAsyncHandler._
    import monix.execution.Scheduler.Implicits.global
    val expectedValue = 42
    val s = "Wisdom"
    val topic = Topic(genAttrPrefix(s))
    val ap = AbilityProvider(s, pc, topic)
    val inboundSubscription: String = s"${s}-change-handler_${UUID.randomUUID()}"
    val client = PulsarClient(pcfg)
    val consumer = client.consumer[ChangeValueInt](
      ConsumerConfig(topics = Seq(topic), subscriptionName = Subscription(inboundSubscription)))
    consumer.seekEarliest()
    val t = consumer.receiveAsync
    val result = ap.update(expectedValue)
    Await.result(Await.result(result, 15.seconds).runToFuture, 15.seconds)
    ap.readCurrentValue shouldEqual expectedValue
    val f = t.runToFuture
    val result2 = Await.result(f, maxTimeOut)
    result2.value.currentValue shouldBe expectedValue
    consumer.close()
  }

  override protected def afterAll(): Unit = {
    TestContainers.pulsarTestContainer.stop()
    pc.close()
  }

  override protected def beforeAll(): Unit = {
    TestContainers.pulsarTestContainer.start()

//    pc = PulsarClient(pcfg)
  }
}
