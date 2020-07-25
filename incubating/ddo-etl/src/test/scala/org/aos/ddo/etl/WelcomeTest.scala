package io.truthencode.ddo.etl

import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
 * Created by adarr on 5/5/2017.
 */
@RunWith(classOf[JUnitRunner]) class WelcomeTest extends FunSpec with Matchers {

  describe("testGreeting") {
    it("has a default greeting") {
      val w = Welcome()
      w.greeting shouldBe "Hello World!"
    }

  }

}
