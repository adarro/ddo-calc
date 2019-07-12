package org.aos.ddo.model.spells

import java.time.Duration

import org.aos.ddo.model.classes.CharacterClass
import org.scalatest.{FunSpec, Matchers}

class SpellBuilderTest extends FunSpec with Matchers{

  describe("Spell Builder") {
    it ("should support cool downs") {
      val builder = new SpellBuilder().addCoolDown(Some(Duration)).addCasterClass(Seq(new CasterWithLevel {
        override def characterClass: CharacterClass = CharacterClass.Bard

        override def level: Int = 3
      })).build
    }
  }

}
