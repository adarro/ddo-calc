/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RomanNumeralManglingTest.scala
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
package io.truthencode.ddo.support.naming

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.RomanNumeral
import io.truthencode.ddo.support.StringUtils.Extensions
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class RomanNumeralManglingTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("Roman Numeral Suffixes") {
    they("should survive bidirectional conversion") {
      val numbers = List(1, 2, 5, 11)
      numbers.foreach { n =>
        val rn = RomanNumeral.toRoman(n)
        val someString = s"Ability $rn"
        val replaced = someString.replaceRomanNumerals
        val displayed = replaced.replaceNumbersWithRomanNumerals
        logger.debug(s"we have $rn $someString rp: $replaced dp $displayed")

        someString shouldEqual (displayed)
      }
    }

  }

}
