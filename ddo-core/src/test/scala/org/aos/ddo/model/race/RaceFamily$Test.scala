package org.aos.ddo.model.race

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 3/17/2017.
  */
class RaceFamily$Test extends FunSpec with Matchers {

  describe("RaceFamily$Test") {

    it("should provide at least one family for each race") {

      Race.values foreach { race =>
        val families = for {
          family <- RaceFamily.values
          if family.includedRaces contains race
        } yield family
        families should not be empty
      }
    }
  }
}
