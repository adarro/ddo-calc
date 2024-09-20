package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class BonusTypeValidationTest extends AnyFunSpec with Matchers with LazyLogging {
  final val invalidBonusType = "SpontaneousErection" // Rogue 'Builder' :)
  describe("Bonus Type Validation") {
    they("Should support all valid bonus types") {
      bonusTypeNames.foreach { v =>
        validateBonusType(v).isSuccess shouldBe true
      }
    }
    they("should reject invalid bonus types") {
      validateBonusType(invalidBonusType).isFailure shouldBe true
    }
  }

}
