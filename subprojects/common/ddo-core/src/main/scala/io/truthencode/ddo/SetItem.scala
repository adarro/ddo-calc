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

import io.truthencode.ddo.model.effect.Effect
import io.truthencode.ddo.model.item.Item

/**
 * An Item set provides bonuses when equipping some or all of the items in the set.
 */
trait SetItem {

  /**
   * Name of set, such as Anger's Wrath
   */
  val name: String

  /**
   * A collection of items that qualify for this set
   */
  val items: List[Item]

  /**
   * This will generally be a single bonus set, such as Anger's Wrath. However some sets, such as
   * the [[http://ddowiki.com/page/Named_item_sets#Might_of_the_Abishai Abashi's Might]] which gives
   * a small bonus for some of the items, and a bigger bonus for wearing all of the items.
   */
  val effects: Map[Int, List[Effect]]

}
