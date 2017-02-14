package org.aos.ddo.model.misc

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.activation.ActivationType
import org.aos.ddo.activation.ActivationType.Passive
import org.aos.ddo.model.effect.TriggerEvent
import org.scalatest.{FunSpec, Matchers}

import scala.languageFeature.postfixOps

/**
  * Created by adarr on 1/28/2017.
  */
class ActivationType$Test extends FunSpec with Matchers with LazyLogging {

  describe("ActivationType$Test") {
    it("should generate case classes") {
      val act = ActivationType.Active(TriggerEvent.OnAttackRoll)
      act.entryName should be("OnAttackRoll")
    }
    it("should withNameLowercaseOnly") {
      ActivationType.withNameLowercaseOnly("passive") should be(Passive)
    }

    it("should withNameInsensitiveOption") {

    }

    it("should withNameInsensitive") {

    }

    it("should withNameUppercaseOnly") {
      logger.info("here is some logging")
    }

    it("should generated known values") {

      noException should be thrownBy ActivationType
        .values
      ActivationType.values should not be empty
      ActivationType.values.foreach(
        x => logger.info(x.entryName)

      )
      ActivationType.values.size should be(11)

      logger.info("can we see this?")
    }

    it("should indexOf") {

    }

    it("should namesToValuesMap") {

    }

    it("should upperCaseNameValuesToMap") {

    }

    it("should valuesToIndex") {

    }

    it("should withNameUppercaseOnlyOption") {

    }

    it("should withNameLowercaseOnlyOption") {

    }

    it("should findValues") {

    }

    it("should lowerCaseNamesToValuesMap") {

    }

    it("should withName") {

    }

    it("should withNameOption") {

    }

  }
}
