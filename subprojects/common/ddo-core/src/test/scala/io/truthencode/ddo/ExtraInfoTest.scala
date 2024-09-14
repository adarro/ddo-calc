package io.truthencode.ddo

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.spells.SpellPower.Positive
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ExtraInfoTest
  extends AnyFunSpec with Matchers with LazyLogging with ScalaCheckPropertyChecks {
  final val Positive = "+"
  final val Negative = "-"
  describe("Extra Info should support ") {
    it("Signed / Unsigned Positive Numbers") {
      forAll { (n: Int) =>
        whenever(n > -1) {
          val ei = ExtraInfo(n)
          ei.symbol shouldEqual Positive
        }
      }
    }
    it("Signed Negative Numbers") {
      forAll { (n: Int) =>
        whenever(n < -1) {
          val ei = ExtraInfo(n)
          ei.symbol shouldEqual Negative
        }
      }
    }
  }

}
