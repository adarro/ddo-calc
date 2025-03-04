/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StringUtilsTest.scala
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

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.StringUtils.{Extensions, StringImprovements}
import io.truthencode.ddo.support.matching.{WordMatchStrategies, WordMatchStrategy}
import org.scalatest.OptionValues.*
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.collection.immutable.HashSet

class StringUtilsTest
  extends AnyFunSpec with TableDrivenPropertyChecks with Matchers with LazyLogging {
  final private val meaningOfLife = 42
  final private val ibm = List("I Believe Mom", "i borrow money", "IBetterMail")
  final private val nullString: String = null

  private val strategyResults = Table(
    ("Strategy", "Expected"),
    (WordMatchStrategies.FullUpperCaseWordStrategy, HashSet() ++ List("IBM")),
    (WordMatchStrategies.TitleCaseWordStrategy, HashSet() ++ List("IBM")),
    (WordMatchStrategies.FullLowerCaseWordStrategy, HashSet() ++ List("ibm")),
    (WordMatchStrategies.IgnoreCaseWordStrategy, HashSet() ++ List("IBM", "ibm"))
  )

  private val validRomanToNumbers =
    Table(
      ("givenVal", "expected"),
      ("Some Magical Ability IX", "Some Magical Ability 9"),
      ("Cool IV stuff", "Cool 4 stuff"),
      ("Mixed II 3", "Mixed 2 3"),
      ("Spaced IV II", "Spaced 4 2")
    )

  private val validNumbersToRoman = Table(
    ("givenVal", "expected"),
    ("Some Magical Ability 9", "Some Magical Ability IX"),
    ("Cool 4 stuff", "Cool IV stuff"),
    ("Mixed 2 3", "Mixed II III"),
    ("Spaced 4 2", "Spaced IV II"),
    ("Meaning 42", "Meaning XLII")
  )
  private val wordsWithSpaces: String = "I Believe Mom"
  private val wordsWithoutSpaces: String = "IBetterMail"
  private val controlChars: String = "\t\r\r"

  describe("toSanitizeOption") {
    it("should safely filter out non-alphanumeric and spaces") {
      controlChars.toSanitizeOption shouldBe empty
    }
    it("should safely wrap nulls") {
      noException shouldBe thrownBy(nullString.toSanitizeOption)
    }
  }
  describe("wordsToAcronym") {
    it("Should convert words with spaces into an acronym") {
      wordsWithSpaces.wordsToAcronym.value should equal("IBM")
    }

    it("Should convert words without spaces using Case into an acronym") {
      wordsWithoutSpaces.wordsToAcronym.value should equal("IBM")
    }
    it("should gracefully handle non-alpha input") {

      //      val wordList = List("I Believe Mom","i borrow money","IBetterMail","oracle","\t")
      //      val wMap = wordList.map(x => x.wordsToAcronym.value)
      "\t".wordsToAcronym shouldBe empty
    }
    it("should make all acronyms Upper Case when option is supplied") {
      forAll(strategyResults) { (s: WordMatchStrategy, hs: HashSet[String]) =>
        implicit val ws: WordMatchStrategy = s
        logger.info(s"Testing Strategy ${s.stringMatchOption} ${s.getClass.getSimpleName}")
        val bigBlue = ibm.map {
          {
            _.wordsToAcronym.value
          }
        }
        bigBlue should not contain None
        bigBlue.toSet should equal(hs)
      }
      //  bigBlue.toSet should equal(HashSet() ++ List("IBM"))
    }
  }

  private val wap = "WordsAndPhrases"

  describe("symbolsToWords") {
    it("should replace symbols in names") {
      val expected = wap
      val _givenVal = "Words&Phrases"
      _givenVal.symbolsToWords shouldEqual expected
    }
    it("should not alter when there are no symbols") {
      val original = wap
      val expected = original
      val _givenVal = "WordsAndPhrases".symbolsToWords
      _givenVal.symbolsToWords shouldEqual expected

    }
    it("should gracefully coexist with splitByCase") {
      val original = "Words&Phrases"
      val expected = "Words & Phrases"
      val _givenVal = original.splitByCase
      _givenVal.shouldEqual(expected)

    }
  }

  describe("Roman Numerals") {
    they("Can be translated to numbers") {
      forAll(validRomanToNumbers) { (givenVal, expected) =>
        val translations = givenVal.replaceRomanNumerals
        translations shouldEqual expected
      }
    }

    they("Can be translated from numbers") {
      forAll(validNumbersToRoman) { (givenVal, expected) =>
        val translations = givenVal.replaceNumbersWithRomanNumerals
        translations shouldEqual expected
      }
    }

    they("should be tolerated by splitByCase") {
      val c = "SomeSpell3".splitByCase
      val stp = 0
    }
  }

  describe("StringImprovements") {
    it("implicitly allows safe string to int conversions") {
      import io.truthencode.ddo.support.StringUtils.StringImprovements // scalastyle:off import.grouping due to implicit scoping
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
