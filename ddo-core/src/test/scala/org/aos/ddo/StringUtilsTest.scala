/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *         http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import org.aos.ddo.support.StringUtils.{ Extensions, StringImprovements }
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.OptionValues._

import com.typesafe.scalalogging.LazyLogging

class StringUtilsTest extends FunSpec with Matchers with LazyLogging {
  private val wordsWithSpaces: String = "I Believe Mom"
  private val wordsWithoutSpaces: String = "IBetterMail"
  final private val meaningOfLife = 42

  describe("wordsToAcronym") {
    it("Should convert words with spaces into an acronym") {
      wordsWithSpaces.wordsToAcronym.value should equal("IBM")
    }

    it("Should convert words without spaces using Case into an acronym") {
      wordsWithoutSpaces.wordsToAcronym.value should equal("IBM")
    }
  }

  describe("StringImprovements") {
    it("implicitly allows safe string to int conversions") {
      import org.aos.ddo.support.StringUtils.StringImprovements // scalastyle:off import.grouping due to implicit scoping
      val love: Option[Int] = "42".toIntOpt
      love should not be empty
      love should be(Some(meaningOfLife))
    }
    it("gracefully handles bad cast attempts") {
      val wilSmyth = "deadShot".toIntOpt
      wilSmyth should be(empty)
    }

  }
}
