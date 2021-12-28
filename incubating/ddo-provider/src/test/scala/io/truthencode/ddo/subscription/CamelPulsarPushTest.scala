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

import com.sksamuel.pulsar4s.{PulsarClient => SClient, PulsarClientConfig, Topic}
import org.apache.camel.component.pulsar.{PulsarComponent, PulsarComponentConfigurer}
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.{CamelExecutionException, ExchangePattern}
import org.apache.pulsar.client.api.PulsarClient
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import wvlet.log.LogSupport

class CamelPulsarPushTest extends AnyFunSpec with Matchers with LogSupport {

  val attr = "Dexterity"
  val topicId = genAttrPrefix(attr)
  val topic = Topic(topicId)
  val rString = "pulsar:" + genAttrPrefix(attr)
  val serviceUrl = "pulsar://localhost:6650"
  describe("Pulsar") {

    it("should Receive from Camel") {

      val context = new DefaultCamelContext
      val n = new PulsarComponent(context)
      val pcc = PulsarClient.builder().serviceUrl(serviceUrl).build()
      val pcr = new PulsarComponentConfigurer
      pcr.configure(context, n, "serviceUrl", serviceUrl, true)
      n.setPulsarClient(pcc)

      context.addComponent("pulsar", n)
      context.start()
      info(s"camel using $rString")

      for (i <- 0 until 10) {
        val template = context.createProducerTemplate
        try {
          template.sendBody(rString, ExchangePattern.InOnly, "Test Message: " + i)
        } catch {
          case e: CamelExecutionException =>
            error(s"failed to send: error ${e.getMessage}", e)
            fail(e)
        } finally {
          template.close()
        }
      }
      context.stop()
    }
    ignore("Should receive from Pulsar Client (Monix)") {
      val client = SClient(PulsarClientConfig(serviceUrl))
      val dexProvider = AbilityProvider(attr, client, topic)
      val sneakProvider = SkillProvider("Sneak", dexProvider, client, topic)
      sneakProvider.subscribe()
      dexProvider.update(42)
    }
  }
}
