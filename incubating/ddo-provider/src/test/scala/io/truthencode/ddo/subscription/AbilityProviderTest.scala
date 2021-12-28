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

import com.sksamuel.pulsar4s.{PulsarAsyncClient, PulsarClient, PulsarClientConfig, Topic}
import org.mockito.scalatest.MockitoSugar
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.beans.{PropertyChangeEvent, PropertyChangeListener}

class AbilityProviderTest
  extends AnyFunSpec with BeforeAndAfterAll with MockitoSugar with Matchers {
  lazy val serviceUrl = "pulsar://localhost:6650"
  lazy val pcfg = PulsarClientConfig(serviceUrl)
  var pc: PulsarAsyncClient = _
  val nv = 5
  val changeValueKey = "IntValue"
  describe("An ability") {
    it("Should support atomic updates") {
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
    it("should support property change notifications") {
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
      ap.update(expectedValue)
      fired shouldBe true
    }
  }

  override protected def afterAll(): Unit = {
    pc.close()
  }

  override protected def beforeAll(): Unit = {

    pc = PulsarClient(pcfg)
  }
}
