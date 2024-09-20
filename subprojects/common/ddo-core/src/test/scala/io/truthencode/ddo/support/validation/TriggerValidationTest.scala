package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class TriggerValidationTest
  extends AnyFunSpec with Matchers with LazyLogging with TableDrivenPropertyChecks {
  final val invalidTriggerOn = Seq("Every third Thursday when the moon is full")
  final val invalidTriggerOff = Seq("Prior Relationships")
  final val validTriggerOn = Seq("OnDamage")
  final val validTriggerOff = Seq("OnSpellCast")
  final val someValidTriggerOn = invalidTriggerOn ++ validTriggerOn
  describe("Trigger validation") {

    they("should validate based on names") {
      val trigs = validTriggerOn ++ validTriggerOff
      val trig = validateTriggers(trigs)
      trig.isSuccess shouldBe true
    }

    they("should reject invalid names") {
      val trigs = invalidTriggerOff ++ invalidTriggerOn
      val trig = validateTriggers(trigs)
      trig.isFailure shouldBe true
    }

    they("should gracefully filter out invalid names") {
      val trigs = someValidTriggerOn
      val expectedSize = validTriggerOn.size
      val trig = validateTriggers(trigs)
      trig.isSuccess shouldBe true
      trig.toOption.get should have size expectedSize
    }

  }
}
