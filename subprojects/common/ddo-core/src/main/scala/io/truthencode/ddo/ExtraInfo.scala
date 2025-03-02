/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ExtraInfo.scala
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
 * A basic integer used to add / subtract values.
 * @param symbol
 *   sign ( + / - )
 * @param value
 *   numerical value
 */
case class ExtraInfo(symbol: String, value: Int) {
  require(symbol.contentEquals("+") || symbol.contentEquals("-"))
  val toInt: Int = symbol match {
    case "+" => Math.abs(value)
    case "-" => -1 * value
  }

  override def toString: String = value match {
    case 0 => ""
    case _ => s" $symbol $value"
  }
}

object ExtraInfo {

  /**
   * Creates an ExtraInfo object, adding the symbol based on the value of the Int.
   * @param i
   *   Additional value to add.
   * @return
   *   signed ExtraInfo object
   */
  def apply(i: Int): ExtraInfo = {
    val sym =
      if i < 0 then {
        "-"
      } else {
        "+"
      }

    ExtraInfo(sym, i)
  }
}
