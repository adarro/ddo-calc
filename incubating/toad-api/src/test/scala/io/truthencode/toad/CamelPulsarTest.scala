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
package io.truthencode.toad

import org.apache.camel.component.pulsar.PulsarComponent
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.{CamelExecutionException, ExchangePattern}
import org.apache.pulsar.client.api.PulsarClient
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funspec.AnyFunSpec
import org.apache.camel.main

class CamelPulsarTest extends AnyFunSpec with BeforeAndAfterAll {

    override protected def beforeAll(): Unit = super.beforeAll()

    final val attributeTopicPrefix = "non-persistent://sample/standalone/ns1/"

  def genAttrPrefix(t: String): String = s"$attributeTopicPrefix$t"

  val attr = "Dexterity"
  val topicId = genAttrPrefix(attr)
  // val topic = Topic(topicId)
  val rString = "pulsar:" + genAttrPrefix(attr)
  val serviceUrl = "pulsar://localhost:6650"

  describe("Pulsar") {
    it("should Receive from Camel") {

      val context = new DefaultCamelContext()
      val pc = new PulsarComponent(context)
      pc.setPulsarClient(PulsarClient.builder().serviceUrl(serviceUrl).build())
      //  pc.getConfiguration.setServiceUrl(serviceUrl)
      //    val cfg = pc.getConfiguration
//      cfg.setServiceUrl(serviceUrl)
//      pc.setConfiguration(cfg)
//      context.addComponent(pc.getDefaultName,pc)

      context.addComponent("Pulsar", pc)
      context.start()
      info(s"camel using $rString")

      for (i <- 0 until 10) {
        val template = context.createProducerTemplate
        try {
          template.sendBody(rString, ExchangePattern.InOnly, "Test Message: " + i)
        } catch {
          case e: CamelExecutionException =>
            System.out.println(s"failed to send: error ${e.getMessage}", e)
            fail(e)
        } finally {
          template.close()
        }

      }
      context.stop()
    }

  }
}
