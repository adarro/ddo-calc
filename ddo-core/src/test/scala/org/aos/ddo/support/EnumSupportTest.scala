package org.aos.ddo.support

// import com.sun.javaws.exceptions.InvalidArgumentException
import enumeratum.EnumEntry
import org.aos.ddo.model.alignment.MoralAxis
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.OptionValues._
import scala.util.{Failure, Success, Try}
import scala.collection.JavaConverters._
class EnumSupportTest extends FunSpec with Matchers {

  describe("EnumSupportTest") {

    describe("tryEntryFromString") {
      it("should extract an enum value from a qualified Enum class name") {
        val t = "org.aos.ddo.model.alignment.MoralAxis$"
        EnumSupport.tryEntryFromString(t, "Good").value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should extract an enum value from a qualified Enum's EnumEntry Trait class name") {
        val t = "org.aos.ddo.model.alignment.MoralAxis"
        EnumSupport.tryEntryFromString(t, "Good").value should be(
          MoralAxis.Good)
        // EnumSupport.tryEnumFromString(t) shouldBe 'success
      }
      it("should fail gracefully (Option.NONE) if the value is not found") {
        val t = "org.aos.ddo.model.alignment.MoralAxis$"
        EnumSupport.tryEntryFromString(t, "Good").value should be(
          MoralAxis.Good)
      }
    }

  }
}
