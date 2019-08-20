package org.aos.ddo.model.feats

import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FeatTest extends FunSpec with Matchers {

  describe("FeatTest") {
    it("should findValues") {
      noException shouldBe thrownBy(Feat.values)
    }
  }
}
