package org.aos.ddo.model.classes

import org.scalatest.{FunSpec, Matchers}

class EpicCharacterClassTest extends FunSpec with Matchers{
  describe("An Epic Character Class") {
    it("Has values from 1 to 10 representing effective level 21 to 30") {
      EpicCharacterClass.values.foreach{x :EnumEntry=>
        x.entryName should startWith ("Level")
        x.entryName should fullyMatch """Level\d+"""
      }
    }
  }

}
