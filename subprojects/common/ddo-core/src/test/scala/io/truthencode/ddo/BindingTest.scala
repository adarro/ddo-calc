/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BindingTest.scala
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
import io.truthencode.ddo.support.StringUtils.{randomAlphanumericString, Extensions}
import io.truthencode.ddo.support.matching.{WordMatchStrategies, WordMatchStrategy}
import org.scalactic.Equality
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

class BindingTest extends AnyFunSpec with Matchers with MockitoSugar with LazyLogging {
  final val Unbound = "Unbound"
  final val possibleText: List[String] = List(
    "Bound To Character on Equip",
    "Bound To Account on Equip",
    "Bound To Character on Acquire",
    "Bound To Account on Acquire",
    "Bound To Account on Equip",
    Unbound,
    "Bound To Character",
    "Bound To Account"
  )
  final val numCharacters = 10
  final val randWords = for _ <- 1 to 5 yield randomAlphanumericString(numCharacters)
  final val bindMap = possibleText.map { words =>
    (
      words,
      if words.equalsIgnoreCase(Unbound) then Unbound else words.wordsToAcronym.getOrElse(Unbound))
  }.toMap.map { case (k, v) => v -> k }
  final val abbr = possibleText.map { words =>
    if words.equalsIgnoreCase(Unbound) then Unbound else words.wordsToAcronym.getOrElse(Unbound)
  }
  final val checks: List[String] = List(
    "BindsToAccount",
    "Unbound",
    "BindsToCharacter",
    "BindsToCharacterOnEquip",
    "BindsToAccountOnEquip",
    "BindsToCharacterOnAcquire",
    "BindsToCharacterOnEquip"
  )
  describe("Binding Status") {
    they("should include unbound, account and character") {
      BindingStatus.values.foreach { x => checks.contains(x.entryName) }
    }

    they("should recognize both 'bound' and 'binds'") {
      implicit val strategy: WordMatchStrategy = WordMatchStrategies.FullLowerCaseWordStrategy
      possibleText.filterNot { x => x.equalsIgnoreCase(Unbound.toLowerCase()) }.foreach { x =>
        BindingFlags.fromWords(Option.apply(x)) should not be empty
        BindingFlags.fromWords(Option.apply(x.replace("Bound", "Binds"))) should not be empty
      }
    }

    it("should have a default value") {
      BindingFlags.hasDefault should be(true)
    }

    it("should have a default value of BindingFlags.Unbound") {
      val unbound = BindingFlags.Unbound
      val binding = BindingFlags.default
      BindingFlags.isDefault(unbound) should be(true)

      binding shouldEqual Some(unbound)
    }

    it("should default to 'None' if any non-matching text supplied") {
      (randWords :+ "\t").foreach { words =>
        logger.info(s"Testing words: $words")
        val rslt = BindingFlags.fromWords(Option(words))
        rslt shouldEqual None
      }
    }

    it("should equal None if None is supplied") {
      val none: Option[String] = None
      val rslt = BindingFlags.fromWords(none)
      logger.info(s"Validating $rslt equals None")
      rslt shouldEqual None
    }
  }
  describe("Binding Flags") {
    they("can create an instance from acronyms with [Option] or raw") {
      val words = possibleText.filter { x => x.equals(Unbound) }
      words.foreach { x =>
        implicit val strategy: WordMatchStrategy = WordMatchStrategies.FullLowerCaseWordStrategy
        logger.info(s"using acronym from $words")
        BindingFlags.fromWords(x) should not be empty
        BindingFlags.fromWords(Option(x)) should not be empty
      }
    }

    they("should produce an instance from the full (case insensitive) words") {
      abbr.foreach { name =>
        logger.info(s"Testing with case insensitive full name $name")
        BindingFlags.withNameInsensitiveOption(name.toUpperCase()) should not be empty
        noException should be thrownBy BindingFlags.withNameInsensitive(name.toUpperCase())
      }
    }

    they("should produce an instance from the full (case sensitive) words") {
      abbr.foreach { name =>
        BindingFlags.withNameOption(name) should not be empty
        noException should be thrownBy BindingFlags.withName(name)
      }
    }
    they("should match Abbreviation with the name") {
      implicit val caseInsensitiveEquality: Equality[String] = (self: String, b: Any) =>
        b match {
          case other: String => self.equalsIgnoreCase(other)
          case _ => false
        }
      bindMap.foreach { case (k, v) =>
        val flag = BindingFlags.withName(k).toFullWord
        logger.info(s"validating $flag == $v")
        flag shouldEqual v

      }
    }
  }
}
