package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class SimpleWeaponProficiencyTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("The Simple Weapon Proficiency Feat") {
    it("should grant proficiency with all simple weapons to certain classes") {
      val feats = GeneralFeat.simpleWeaponProficiencies
      val sFeats = GeneralFeat.SimpleWeaponProficiency.subFeats
      feats.size shouldEqual sFeats.size
      feats.foreach { f =>
        logger.debug(s"${f.entryName}")
      }
      // .size shouldBe 11

    }
  }
}
