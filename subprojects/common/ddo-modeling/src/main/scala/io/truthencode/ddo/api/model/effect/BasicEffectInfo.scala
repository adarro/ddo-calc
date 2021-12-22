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

trait BasicEffectInfo {
  /**
   * The main name of the effect.
   *
   * Naming conventions The name should be concisely non-specific.
   * i.e. Prefer "ArmorClass" instead of "Deflection" or "Miss-Chance" Deflection is too specific as
   * there are several stacking and non-stacking types (Natural Armor, Shield) that all contribute
   * to your specific goal of increasing your armor class. Miss-Chance is to vague as it encompasses
   * everything from incorporeal, dodge, armor class, arrow-deflection etc.
   */
  def name: String

  /**
   * The General Description should be just that. This should not include specific values unless all
   * instances will share that value. I.e. a Dodge Effect might state it increases your miss-chance,
   * but omit any value such as 20%. Those values will be displayed in the effectText of a specific
   * implementation such as the Dodge Feat or Uncanny Dodge
   */
  val generalDescription: String

  /**
   * a list of Categories useful for menu / UI placement and also for searching / querying for
   * Miss-Chance or other desired effects.
   *
   * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to enable
   * quick and advanced searching for specific categories from general (Miss-Chance) to specific
   * (evasion). In addition, it may be useful for deep searching such as increasing Spot, which
   * should suggest not only +Spot items, but +Wisdom or eventually include a feat or enhancement
   * that allows the use of some other value as your spot score.
   */
  def categories: Seq[String]
}
