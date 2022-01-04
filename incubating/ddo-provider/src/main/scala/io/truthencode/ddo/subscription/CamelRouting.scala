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

import io.truthencode.ddo.subscription.ProviderServices.MqServerConfig
import io.truthencode.ddo.subscription.config.CamelConfig
import org.apache.camel.component.pulsar.{PulsarComponent, PulsarComponentConfigurer}
import org.apache.camel.impl.DefaultCamelContext
import org.apache.pulsar.client.api.PulsarClient
import wvlet.log.LogSupport

import javax.annotation.{PostConstruct, PreDestroy}

case class CamelRouting(camelConfig: CamelConfig, mq: MqServerConfig) extends LogSupport {
  lazy val context = new DefaultCamelContext()

  @PostConstruct
  def autoReallyStart(): Unit = {
    info(s"Auto start set to ${camelConfig.autostart}")
    val serviceUrl = mq.serviceUrl
    val n = new PulsarComponent(context)
    val pcc = PulsarClient.builder().serviceUrl(serviceUrl).build()
    val pcr = new PulsarComponentConfigurer
    pcr.configure(context, n, "serviceUrl", serviceUrl, true)
    n.setPulsarClient(pcc)

    context.addComponent("pulsar", n)
    if (camelConfig.autostart) context.start()

    context.start()
  }
  @PreDestroy
  def stop(): Unit = {
    if (context.isStarted && !context.isStopping) {
      info("Shutting down Camel")
      context.stop()
    }
  }
}
