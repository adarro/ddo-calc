package io.truthencode.ddo.model.attribute

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableFor2
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class AttributeTest
  extends AnyFunSpec with ScalaCheckPropertyChecks with Matchers with LazyLogging {
  val nameMap: TableFor2[String, String] =
    Table(
      ("abbr", "name"),
      ("STR", "Strength"),
      ("WIS", "Wisdom"),
      ("INT", "Intelligence"),
      ("CON", "Constitution"),
      ("DEX", "Dexterity"),
      ("CHA", "Charisma")
    )

  describe("An Attribute ") {
    it("Should support both abbreviations and full words") {
      forAll(nameMap) { (abbr: String, name: String) =>
        val atr = Attribute.withName(name)
        atr.abbr shouldEqual abbr
        atr.toFullWord shouldEqual name
      }
    }
  }
}
