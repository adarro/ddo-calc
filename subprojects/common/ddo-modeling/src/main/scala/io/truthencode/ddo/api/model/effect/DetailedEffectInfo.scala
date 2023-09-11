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
package io.truthencode.ddo.api.model.effect

trait DetailedEffectInfo {

  /**
   * This will need to uniquely identify the effect
   */
  val id: String

  /**
   * A specific description of the effect This should include the affected value, such as "Armor
   * Class by 3%"
   */
  val description: String

  /**
   * When this effect applies. Should correspond to some state chance such as full health,
   * unconscious, on critical hit, on miss, or Always on, etc.
   */
  val triggersOn: Seq[String]

  /**
   * When this effect does not apply. Could be, never or some duration or any of the list from
   * triggersOn
   */
  val triggersOff: Seq[String]

  /**
   * The type of bonus determines stacking rules.
   */
  val bonusType: String
}
