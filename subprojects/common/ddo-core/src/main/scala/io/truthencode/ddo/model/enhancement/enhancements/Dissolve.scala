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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierFive

trait Dissolve extends ApothecaryTierFive with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    """Target enemy at touch range that is Fascinated, Sleeping, Dancing, Dazed, Tripped, Mesmerized, Paralyzed, Petrified, or Stunned must make a Fortitude save (d20 + your Heal skill) or die instantly. (20 second cooldown)""".stripMargin
  )

  override def apCostPerRank: Int = 2

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

}
