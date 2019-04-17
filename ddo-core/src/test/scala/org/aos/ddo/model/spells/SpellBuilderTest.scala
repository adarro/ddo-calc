package org.aos.ddo.model.spells

import org.scalatest.{FunSpec, Matchers}

class SpellBuilderTest extends FunSpec with Matchers{

  describe("Spell Builder") {
    it ("should support cool downs") {
      val builder = new BaseSpellBuilder("Bulls Strength");
    }
  }

}
