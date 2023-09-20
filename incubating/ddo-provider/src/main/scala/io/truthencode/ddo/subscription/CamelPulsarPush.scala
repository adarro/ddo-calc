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

import io.truthencode.ddo.subscription.http.AdminWeb
import org.apache.camel.CamelContext
import wvlet.log.LogSupport

import javax.annotation.PostConstruct

case class CamelPulsarPush(context: CamelContext, skillProvider: SkillProvider, serviceUrl: String)
  extends LogSupport {
  @PostConstruct
  def pushOnStart(): Unit = {
    if (!context.isAutoStartup && !context.isStarted) {
      info("Manually starting camel")
      context.start()
    }
    routeSomething()
  }

//  @PostConstruct
//  def startDaemon(): Unit = {
//    info("CP Staring Web Admin Daemon")
//    adminWeb.startServer()
//    info("leaving CPP startDaemon")
//
//  }

  def routeSomething(): Unit = {
    for (i <- 0 until 10) {

      val rString = s"pulsar:${skillProvider.topic.name}"
      val template = context.createProducerTemplate
      template.sendBody(rString, "Test Message: " + i)
    }
  }
}
