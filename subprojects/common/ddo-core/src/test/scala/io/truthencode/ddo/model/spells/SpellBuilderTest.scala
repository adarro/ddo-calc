/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.model.spells

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.effect.EffectList
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

import java.time.Duration

class SpellBuilderTest extends AnyFunSpec with Matchers with MockitoSugar with LazyLogging {
  final private val sampleDuration = 5L
  final private val sampleLevelCap = 32
  final private val SpellPointValue = 42
  private val coolDown = mock[Option[Duration]]
  private val casterLevel = mock[CasterWithLevel]
  private val spellTarget = mock[SpellTarget]
  private val casterLevelCap = mock[CasterLevelCap]
  private val effectList = mock[EffectList]
  private val spellInfo = mock[SpellInfo]
  describe("Spell Builder") {
    it("should compile if all elements are present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ should compile

    }
    it("should build if all elements are present") {
      noException shouldBe thrownBy {
        val s = SpellBuilder
          .apply()
          .addName("New Spell")
          .addSpellInfo(spellInfo)
          .addCasterClass(Seq(casterLevel))
          .addSpellTarget(List(spellTarget))
          .addSavingThrow(List.empty)
          .addSpellPoints(SpellPointValue)
          .addComponents(List.empty)
          .addLevelCap(casterLevelCap)
          .addEffect(effectList)
          .build
        logger.info(s"Created spell ${s.name} - ${s.entryName}")
      }
    }

    it("should build with Named constructor if all elements are present") {
      noException shouldBe thrownBy {
        val s = SpellBuilder
          .apply("New Spell")
          .addSpellInfo(spellInfo)
          .addCasterClass(Seq(casterLevel))
          .addSpellTarget(List(spellTarget))
          .addSavingThrow(List.empty)
          .addSpellPoints(SpellPointValue)
          .addComponents(List.empty)
          .addLevelCap(casterLevelCap)
          .addEffect(effectList)
          .build
        logger.info(s"Created spell ${s.name} - ${s.entryName}")
      }
    }

    it("should fail to compile if LevelCap is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addName("New Spell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }

    it("should fail to compile if spell info is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }

    it("should fail to compile if caster class is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck

    }

    it("should fail to compile if Saving throw is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }
    it("should fail to compile if SpellPoints is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }
    it("should fail to compile if Components is not present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }
    it("should fail to compile if Effects are not present") {
      """ SpellBuilder.apply("NewSpell")
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .build
        """ shouldNot typeCheck
    }
    it("should fail to compile if Name are not present") {
      """ SpellBuilder()
        .addSpellInfo(spellInfo)
        .addCasterClass(Seq(casterLevel))
        .addSpellTarget(List(spellTarget))
        .addSavingThrow(List.empty)
        .addSpellPoints(SpellPointValue)
        .addComponents(List.empty)
        .addLevelCap(casterLevelCap)
        .addEffect(effectList)
        .build
        """ shouldNot typeCheck
    }
  }

}
