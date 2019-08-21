/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.support

import org.aos.ddo.model.effect.Suffix
import org.aos.ddo.model.effect.Prefix

trait RomanNumeral {
  type Self <: RomanNumeral
  val symbol: String
  lazy val symbolValue: Int = RomanNumeral.fromRoman(symbol)
  def roman(rn: String): Self
}

object RomanNumeral {

  // from [[https://www.rosettacode.org/wiki/Roman_numerals/Decode#Scala rosettacode]]
  /**
    * Translates Roman Numerals to Int
    * [[https://www.rosettacode.org/wiki/Roman_numerals/Decode#Scala rosettacode]]
    */
  def fromRoman(s: String): Int = {
    val numerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)

    s.toUpperCase.map(numerals).foldLeft((0, 0)) {
      case ((sum, last), curr) => (sum + curr + (if (last < curr) -2 * last else 0), curr)
    }._1
  }
  /**
    * Translates Int into Roman numerals
    * [[https://www.rosettacode.org/wiki/Roman_numerals/Decode#Scala rosettacode]]
    */
  def toRoman(num: Int): String = {
      def convert(value: Int, table: List[(Int, String)]): String = table.headOption match {
        case None                  => ""
        case Some((arabic, roman)) => roman * (value / arabic) + convert(value % arabic, table.tail)
      }
    convert(num, table)
  }
  private val table = List(
    (1000, "M"), (900, "CM"),
    (500, "D"), (400, "CD"),
    (100, "C"), (90, "XC"),
    (50, "L"), (40, "XL"),
    (10, "X"), (9, "IX"),
    (5, "V"), (4, "IV"),
    (1, "I"))
  // scalastyle:off regex
  /**
    * // A small test
    * def test( roman:String ) = println( roman + " => " + fromRoman( roman ) )
    *
    * test("MCMXC")
    * test("MMVIII")
    * test("MDCLXVI")
    *
    * def testR( arabic:Int ) = println( arabic + " => " + toRoman( arabic ) )
    *
    * testR(1990)
    * testR(2008)
    * testR(1666)
    *
    */
  // scalastyle:on
}

trait RomanSuffix extends RomanNumeral with Suffix

trait RomanPrefix extends RomanNumeral with Prefix
