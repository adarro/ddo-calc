package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.feats.SpecialFeat.FeatRespecToken
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class SpecialFeatTest extends AnyFunSpec with Matchers with LazyLogging  {
  describe("A Feat Respec Token") {
    it("should be retrieved from the base Feat Search") {
     Feat.values should contain(SpecialFeat.FeatRespecToken)
    }
  }
}
