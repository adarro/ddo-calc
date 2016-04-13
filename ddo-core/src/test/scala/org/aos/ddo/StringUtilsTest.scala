package org.aos.ddo

import org.aos.ddo.support.StringUtils.{ wordsToAcronym, StringImprovements }
import org.scalatest.{ FunSpec, Matchers }

import com.typesafe.scalalogging.slf4j.LazyLogging

class StringUtilsTest extends FunSpec with Matchers with LazyLogging {
  private val wordsWithSpaces: String = "I Believe Mom"
  private val wordsWithoutSpaces: String = "IBetterMail"
  final private val MeaningOfLife = 42

  describe("wordsToAcronym") {
    it("Should convert words with spaces into an acronym") {
      wordsToAcronym(wordsWithSpaces) should not be empty
      wordsToAcronym(wordsWithSpaces) should equal(Some("IBM"))
    }

    it("Should convert words with without spaces using Case into an acronym") {
      wordsToAcronym(wordsWithoutSpaces) should not be empty
      wordsToAcronym(wordsWithoutSpaces) should equal(Some("IBM"))
    }

    it("Should be null safe (um...in case Java calls it ;)") {
      wordsToAcronym(null) should be(empty) // scalastyle:off null explicitly proving we're null safe, not recommending its use
    }
  }

  describe("StringImprovements") {
    it("implicitly allows safe string to int conversions") {
      import org.aos.ddo.support.StringUtils.StringImprovements // scalastyle:off import.grouping due to implicit scoping
      val x: Option[Int] = "42".toIntOpt
      x should not be empty
      x should be(Some(MeaningOfLife))
    }
    it("gracefully handles bad cast attempts") {
      val x = "carrots".toIntOpt
      x should be(empty)
    }

  }
}
