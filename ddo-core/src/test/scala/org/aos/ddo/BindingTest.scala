/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package org.aos.ddo

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.support.StringUtils.{Extensions, randomAlphanumericString}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class BindingTest extends FunSpec with Matchers with MockitoSugar with LazyLogging {
  final val Unbound = "Unbound"
  final val possibleText: List[String] = List("Bound To Character on Equip",
    "Bound To Account on Equip",
    "Bound To Character on Acquire",
    "Bound To Account on Equip",
    Unbound,
    "Bound To Character",
    "Bound To Account")
  final val numCharacters = 10
  final val randWords = for {x <- 1 to 5} yield randomAlphanumericString(numCharacters)
  final val abbr = possibleText.map { words => if (words.equalsIgnoreCase(Unbound)) Unbound else words.wordsToAcronym.getOrElse(Unbound) }
  final val checks: List[String] = List("BindsToAccount",
    "Unbound", "BindsToCharacter", "BindsToCharacterOnEquip", "BindsToAccountOnEquip", "BindsToCharacterOnAcquire", "BindsToCharacterOnEquip")
  describe("Binding Status") {
    they("should include unbound, account and character") {
      BindingStatus.values.foreach { x => checks.contains(x) }
    }

    they("should recognize both 'bound' and 'binds'") {
      possibleText.filterNot { x => x.equalsIgnoreCase(Unbound.toLowerCase()) }.foreach { x =>
        BindingFlags.fromWords(Option.apply(x)) should not be empty
        BindingFlags.fromWords(Option.apply(x.replace("Bound", "Binds"))) should not be empty
      }
    }
    it("should have a default value") {
      BindingFlags.hasDefaultValue should be(true)
    }
    it("should have a default value of BindingFlags.Unbound") {
      val unbound = BindingFlags.Unbound
      val binding = BindingFlags.default
      BindingFlags.isDefaultValue(unbound) should be(true)

      binding shouldEqual Some(unbound)
    }
    it("should default to 'None' if any non-matching text supplied") {
      randWords.foreach { words =>
        logger.info(s"Testing words: $words")
        val rslt = BindingFlags.fromWords(Option(words))
        rslt shouldEqual None
      }
    }
  }
  describe("Binding Flags") {
    they("can create an instance from acronyns with [Option] or raw") {
      val words = possibleText.filter { x => x.equals(Unbound) }
      words.foreach { x =>
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
  }
}
