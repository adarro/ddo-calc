package org.aos.ddo.model.race

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 3/17/2017.
  */
class Race$Test extends FunSpec with Matchers {

  describe("Race$Test") {

    it("should determine Family") {
      val elf = Race.HalfElf
      val family = List(RaceFamily.Elven, RaceFamily.Human)
      elf.families should contain atLeastOneElementOf family
    }

  }
}
