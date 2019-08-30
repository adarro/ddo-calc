package org.aos.ddo.model.spells

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.model.spells.SpellElement.{WithName, WithSpellInfo}
import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SpellElementTest extends FunSpec with Matchers with LazyLogging {


  describe("Utility Functions") {
    it("Should extract an element by type correctly") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n: Iterable[WithName] = SpellElement.extract[WithName](elements.toList)
      n should contain(eName)
      //      val se: Seq[SpellElement] => SpellElement.Util[WithName] = SpellElement.Util[WithName]
      //      val se2: Seq[Nothing] = se.apply(elements).getStuff(elements)
      //      val results: Seq[SpellElement] => Seq[WithName] = elements.getStuff[WithName]
      //
      //      results shouldBe defined
      //      val n =results.apply(elements).headOption
      //       n shouldBe defined
      //
      //      n shouldBe defined
      //      n shouldEqual Some(eName.name)
      //  results.ma
    }

    it("Should extract an element by type correctly directly from the collection") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n: Iterable[WithName] = elements.extract[WithName]
      n should contain(eName)

    }

    it("Should gracefully fail to find an missing element by type") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)
      val n = SpellElement.extract[WithSpellInfo](elements.toList)
      n shouldNot  contain(eName)
    }
    it("Should gracefully fail to find an missing element by type from the collection") {
      val eName = UseSpellName(name = "Example Spell")

      val elements: Seq[SpellElement] = Seq(eName)

      val n = elements.extract[WithSpellInfo]
      n shouldNot  contain(eName)
    }
  }
}
