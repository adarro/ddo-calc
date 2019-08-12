package org.aos.ddo.model.spells

import java.time.Duration

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.effect.{Effect, EffectList}
import org.aos.ddo.model.misc.CoolDown
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.mockito.MockitoSugar

class SpellBuilderTest extends FunSpec with Matchers with MockitoSugar {
  private final val sampleDuration = 5L
  private final val sampleLevelCap = 32
  private val coolDown = mock[Option[Duration]]
  private val casterLevel = mock[CasterWithLevel]
  private val spellTarget =  mock[SpellTarget]
  private val casterLevelCap = mock[CasterLevelCap]
  private val effectList = mock[EffectList]
  private val spellInfo = mock[SpellInfo]
  describe("Spell Builder") {
    it("should support cool downs") {
      val builder = new SpellBuilder()
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(32)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        //.build
    }
  }

}
