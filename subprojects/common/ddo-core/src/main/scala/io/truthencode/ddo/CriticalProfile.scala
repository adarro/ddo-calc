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

/**
 * Holds range information used to determine when critical hits occur and the bonus to apply.
 */
trait CriticalProfile {

  /**
   * Lower bound
   */
  val min: Int

  /**
   * Upper bound
   */
  val max: Int

  /**
   * Bonus multiplier when a roll is between given range (inclusive)
   */
  val multiplier: Int

  /**
   * Creates a range using the given min / max (inclusive)
   */
  val toRange = Range(min, max).inclusive

  /**
   * Determines if a given roll would be considered 'Critical'
   */
  def isCritical(roll: Int): Boolean = toRange.contains(roll)
}
