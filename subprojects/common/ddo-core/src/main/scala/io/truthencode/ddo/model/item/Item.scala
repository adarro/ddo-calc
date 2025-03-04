/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Item.scala
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
package io.truthencode.ddo.model.item

import io.truthencode.ddo.MetaData
import io.truthencode.ddo.MonetaryValue.Coins

trait Item extends MetaData {

  /**
   * A Friendly, Kobold readable description or explanation of the item.
   */
  val description: Option[String]

  /**
   * Represents monetary in game amount. plat / gold / copper. Low Priority i.e. Base Value: 7pp 5gp
   * TODO: Base Value may need to be a double or something to better represent
   */
  val baseValue: Option[Coins]

  /**
   * Weight of item, in pounds i.e. Weight: 2 lbs
   */
  val weight: Option[Int]

  /**
   * Use Magical Device [[http://ddowiki.com/page/Use_Magic_Device UMD]] Skill level needed to
   * bypass certain restrictions such as using wands below level or out of class or bypassing race
   * restrictions.
   */
  val umd: Int
}
