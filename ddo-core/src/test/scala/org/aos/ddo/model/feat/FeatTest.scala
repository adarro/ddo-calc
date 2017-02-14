package org.aos.ddo.model.feat

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 2/7/2017.
  */
class FeatTest extends FunSpec with Matchers {
describe("Feats") {
  they("have discreet values") {
    noException should be thrownBy Feat.values
  }
}
}
