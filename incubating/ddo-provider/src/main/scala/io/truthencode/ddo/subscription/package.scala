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
package io.truthencode.ddo

import com.sksamuel.pulsar4s.Topic

package object subscription {
  type Env = String
// Pulsar Topic / channels
  type TopicSTR = Topic
  type TopicCON = Topic
  type TopicDEX = Topic
  type TopicINT = Topic
  type TopicWIS = Topic
  type TopicCHA = Topic

  type ServiceUrl = String
  type StrengthProvider = AbilityProvider
  type WisdomProvider = AbilityProvider
  type DexterityProvider = AbilityProvider
  type IntelligenceProvider = AbilityProvider
  type ConstitutionProvider = AbilityProvider
  type CharismaProvider = AbilityProvider

  type SpotProvider = SkillProvider
  type MoveSilentlyProvider = SkillProvider
  type HideProvider = SkillProvider
  type BalanceProvider = SkillProvider
  type BluffProvider = SkillProvider
  type ConcentrationProvider = SkillProvider
  type DiplomacyProvider = SkillProvider
  type DisableDeviceProvider = SkillProvider
  type HaggleProvider = SkillProvider

  final val attributeTopicPrefix = "non-persistent://sample/standalone/ns1/"
  def genAttrPrefix(t: String): String = s"$attributeTopicPrefix$t"

}
