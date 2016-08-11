package org.aos.ddo.enchantment

import com.typesafe.scalalogging.LazyLogging
import com.wix.accord.scalatest.ResultMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class GuardTest extends FunSpec with Matchers with ResultMatchers with LazyLogging {
  val rnIV = "IV"
  describe("A Guard Enchantment") {
    it("Can support a Roman Numeral 1 - 10") {
      val rn = List("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")

      for {x <- rn} {
        GuardModifier.allowedRoman(Option(x)) should not be empty
      }
    }

    it("Can support a Roman Numeral as a suffix") {
      noException should be thrownBy Some(GuardModifier(suffix = Some(rnIV)))
      //   noException should be thrownBy Guard(guard = Guards.AcidGuard, rn4)
    }

    it("Will reject an invalid suffix") {
      a[NoSuchElementException] should be thrownBy {
        Some(GuardModifier(suffix = Some("whateva")))
      }
    }

    it("May have a single prefix") {
      noException should be thrownBy Some(GuardModifier(prefix = Some(Modifier.Minor.entryName)))
    }

    it("Must reject a disallowed  prefix") {
      an[IllegalArgumentException] should be thrownBy {
        Some(GuardModifier(prefix = Some("x")))
        Some(GuardModifier(prefix = Some("m")))
      }
    }

    it("Supports a subset of prefixes") {
      val supported = List(Modifier.Minor, Modifier.Lesser, Modifier.Greater).map { x => Some(x.entryName) }
      supported.foreach { x => GuardModifier.filterModifiers(x) shouldBe defined }
    }

    it("Must Reject Other prefixes") {
      val supported = List(Modifier.Minor, Modifier.Lesser, Modifier.Greater)
      val unSupported = Modifier.values.diff(supported).map { x => Some(x.entryName) }
      unSupported.foreach { x => GuardModifier.filterModifiers(x) should not be defined }
    }

    it("Must reject a invalid  prefix") {
      an[IllegalArgumentException] should be thrownBy {
        Some(GuardModifier(prefix = Some("Super Uber Rock me Epic")))
      }
    }

    it("Must not have a secondary prefix") {
      an[IllegalArgumentException] should be thrownBy {
        GuardModifier(sPrefix = Some("Uber"))
      }
    }

    it("Must not have both a prefix AND a suffix") {
      a[IllegalArgumentException] should be thrownBy {
        Some(GuardModifier(prefix = Some(Modifier.Minor.entryName), suffix = Some(rnIV)))
      }
    }

    it("Supports Named Guards")(pending)
  }
}
