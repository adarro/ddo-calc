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

import enumeratum.{Enum => SmartEnum, EnumEntry}

/**
 * should indicate the base value (in platinum type denominations) This should probably be expanded
 * to a general DDO Denomination to support the concept of cost. i.e. plat would simply be the
 * listed item price (which is generally quite useless and uninteresting) but Astral Shards or
 * Turbine points could be noted if the item is available from the DDO Store etc.
 */
sealed abstract class MonetaryValue extends EnumEntry

/**
 * should indicate the base value (in platinum type denominations) This should probably be expanded
 * to a general DDO Denomination to support the concept of cost. i.e. plat would simply be the
 * listed item price (which is generally quite useless and uninteresting) but Astral Shards or
 * Turbine points could be noted if the item is available from the DDO Store etc.
 */
object MonetaryValue extends SmartEnum[MonetaryValue] {
  val values = findValues

  case class Coins(plat: Int = 0, gold: Int = 0, silver: Int = 0, copper: Int = 0)
    extends MonetaryValue

  case class AstralShards(amount: Int) extends MonetaryValue

  case class TurbinePoints(amount: Int) extends MonetaryValue
}
