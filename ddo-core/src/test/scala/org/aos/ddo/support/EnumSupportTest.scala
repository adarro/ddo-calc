package org.aos.ddo.support

import org.aos.ddo.model.alignment.MoralAxis
import org.aos.ddo.support.EnumSupport.tryEntryFromString
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.OptionValues._
class EnumSupportTest extends FunSpec with Matchers {

  private final val good = "Good"
  describe("EnumSupportTest") {

    describe("tryEntryFromString") {
      it("should extract an enum value from a qualified Enum class name") {
        val t = "org.aos.ddo.model.alignment.MoralAxis$"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should extract an enum value from a qualified Enum's EnumEntry Trait class name") {
        val t = "org.aos.ddo.model.alignment.MoralAxis"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should fail gracefully (Option.NONE) if the value is not found") {
        val t = "org.aos.ddo.model.alignment.MoralAxis$"
        tryEntryFromString(t, good).value should be(
          MoralAxis.Good)
      }
    }

  }
}
