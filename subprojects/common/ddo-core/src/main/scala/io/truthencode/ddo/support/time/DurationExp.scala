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
package io.truthencode.ddo.support.time

import java.time.Duration

/**
 * Base trait to store and calculate Durations in DDO such as Casting Delay, CoolDowns etc.
 */
trait DurationExp {
  def toDuration: Option[Duration]

}

/**
 * Represents a simple duration such as 5 seconds
 */
trait SimpleDuration extends DurationExp {
  val duration: Duration
}

/**
 * Represents a level based modifier that may increase / decrease duration. Used to represent 3
 * seconds per level represent multiplier for each time a certain feat is acquired such as - 1
 * second per religious feat stepped progressive such as reduced at levels 1,4,8 16 and 20
 */
trait PerLevelDuration extends DurationExp {

  /**
   * Initial Duration such as 60 seconds minus 1 second per level, 60 is the base
   */
  val perLevelBase: Duration = Duration.ZERO

  /**
   * Indicates the amount to step by, i.e. 1 to 10 by step
   */
  val step: Double = 1
  val modifier: Double
}
