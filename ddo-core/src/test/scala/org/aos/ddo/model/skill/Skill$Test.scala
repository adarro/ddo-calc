package org.aos.ddo.model.skill

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 2/6/2017.
  */
class Skill$Test extends FunSpec with Matchers {

  describe("Skill$Test") {

    ignore("should withNameInsensitiveOption") {

    }

    it("should values") {
      val v = Skill.values
      noException should be thrownBy Skill
        .values
    }

  }
}
