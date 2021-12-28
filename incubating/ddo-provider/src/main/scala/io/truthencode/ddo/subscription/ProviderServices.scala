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
import io.truthencode.ddo.subscription.config.CamelConfig
import io.truthencode.ddo.subscription.http.AdminWeb
import io.truthencode.ddo.subscription.service.StatusService
import wvlet.airframe.config._
import wvlet.airframe.{DesignWithContext, newDesign}
import wvlet.log.LogSupport

object ProviderServices extends App with LogSupport {

  type StrengthScore = String
  type WisdomScore = String
  type DexterityScore = String
  type IntelligenceScore = String
  type CharismaScore = String
  type ConstitutionScore = String
  /**
   * Monitors strength scores and modifiers.
   */

  case class Fruit(name: String)

  type Apple = Fruit
  type Banana = Fruit

  class TaggedBinding(apple: Apple, banana: Banana)

  def startEcho(): Unit = {
    val eServer = StatusService.start
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = eServer.destroy()
    })
  }
  /*
   We're wrapping the client config because the airframe surface config doesn't handle
    the authentication trait as a constructor value (Surface Reflection)
    Actual issue appears to be we have no concrete class so it  can't init to Option[Kind] = None
    This should go away once we actually specify an implementation and don't use it or do...
    Closer to production we'll do a better work-around either via typesafe config or possibly a case class facade?
   */
  case class MqServerConfig(serviceUrl: String) {
    val pulsarClientConfig: PulsarClientConfig = PulsarClientConfig(serviceUrl)
  }

  val coreDesign =
    newDesign

  val cfgDesign = coreDesign
    .withConfigPaths(Seq("config"))
    .withConfigEnv(env = "development", defaultEnv = "default")
    .bindConfigFromYaml[CamelConfig]("camel.yaml")
    .bindConfigFromYaml[MqServerConfig]("mqserver.yaml")
    .bind[PulsarAsyncClient]
    .toProvider { mq: MqServerConfig => PulsarClient(mq.pulsarClientConfig) }

  val attrTopics = cfgDesign
    .bind[StrengthScore]
    .toInstance("Strength")
    .bind[WisdomScore]
    .toInstance("Wisdom")
    .bind[IntelligenceScore]
    .toInstance("Intelligence")
    .bind[DexterityScore]
    .toInstance("Dexterity")
    .bind[CharismaScore]
    .toInstance("Charisma")
    .bind[ConstitutionScore]
    .toInstance("Constitution")
    .bind[TopicSTR]
    .toProvider { s: StrengthScore => Topic(genAttrPrefix(s)) }
    .bind[TopicDEX]
    .toProvider { s: DexterityScore => Topic(genAttrPrefix(s)) }
    .bind[TopicCON]
    .toProvider { s: ConstitutionScore => Topic(genAttrPrefix(s)) }
    .bind[TopicCHA]
    .toProvider { s: CharismaScore => Topic(genAttrPrefix(s)) }
    .bind[TopicWIS]
    .toProvider { s: WisdomScore => Topic(genAttrPrefix(s)) }
    .bind[TopicINT]
    .toInstance(Topic("attributeTopicPrefixIntelligence"))

  val attrDesign = attrTopics
    .bind[StrengthProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicSTR) => AbilityProvider("Strength", pc, t) }
    .bind[WisdomProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicWIS) => AbilityProvider("Wisdom", pc, t) }
    .bind[DexterityProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicDEX) => AbilityProvider("Dexterity", pc, t) }
    .bind[IntelligenceProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicINT) => AbilityProvider("Intelligence", pc, t) }
    .bind[ConstitutionProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicCON) => AbilityProvider("Constitution", pc, t) }
    .bind[CharismaProvider]
    .toProvider { (pc: PulsarAsyncClient, t: TopicCHA) => AbilityProvider("Charisma", pc, t) }
    .bind[MoveSilentlyProvider]
    .toProvider { (pc: PulsarAsyncClient, dp: DexterityProvider, t: TopicSTR) =>
      SkillProvider("MoveSilently", dp, pc, t)
    }
    .bind[HealthProvider]
    .toProvider { (pc: PulsarAsyncClient, t: Topic) => HealthProvider("Hitpoints", pc, t) }
    .bind[CamelRouting]
    .toSingleton
    .bind[AdminWeb]
    .toSingleton
    .bind[CamelPulsarPush]
    .toProvider { (cr: CamelRouting, mq: MqServerConfig, msp: MoveSilentlyProvider, aw: AdminWeb) =>
      CamelPulsarPush(cr.context, msp, mq.serviceUrl, aw)
    }

  //      .bind[SkillProvider]
//      .toSingleton
  //  .bind[TaggedBinding]
  // .toInstance(AbilityProvider("Strength"))

  val testDesign =
    attrDesign.bind[Env].toInstance("test")
  val productionDesign =
    coreDesign
      .bind[Env]
      .toInstance("production")
      .withProductionMode



  def runWithSession[T](design:DesignWithContext[T]): Unit = {
    design.withSession { session =>
        info("Starting Daemon")
        val app = session.build[CamelPulsarPush]
        info("returned from starting daemon")
    }
  }

    runWithSession(attrDesign)
//  attrDesign.build[CamelPulsarPush] { c =>
//    info("Starting Daemon")
//    c.startDaemon()
//    info("returned from starting daemon")
//  }
  //   System.console().readLine("Press the 'Any' key to continue, any other key to terminate")
}
