/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Dice.scala
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
package io.truthencode.ddo.support.dice

/**
 * Represents a Die with Sides and Number of Dice to roll
 *
 * @example
 *   2d6 would be 2 6 sided dice or 2 - 12
 */
trait Dice {

  /**
   * Number of Sides on the Die
   */
  val sides: Int
  val number: Int
  def roll: Int = {
    val rnd = scala.util.Random
    val rng = Range.inclusive(1, number)
    (for r <- rng yield rnd.nextInt(sides) + 1).sum
  }
}

object Dice {
  def apply(s: Int, n: Int): Dice = new Dice() {

    /**
     * Number of Sides on the Die
     */
    override val sides: Int = s
    override val number: Int = n
  }

  def parse(s: String): Unit = {
    val regx = """\[\d+d\d+]""".r
  }
}
