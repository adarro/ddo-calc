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

import io.truthencode.ddo.model.enhancement.enhancements.classbased.ApothecaryTierThree

trait PanaceaPoultice extends ApothecaryTierThree with ClassEnhancementImpl {

  override lazy val description: Option[String] = Some(
    "(Gildleaf SLA): Target ally at touch range takes the Panacea effect. (6/3/1 spell point, 12/8/6 second cooldown)."
  )

  override def apCostPerRank: Int = 1

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 3

}
