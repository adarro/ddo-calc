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
package io.truthencode.ddo.support.naming

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement
import io.truthencode.ddo.model.feats.ClassFeat
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class SLAPrefixTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("SlA Prefix") {
    it("should prepend Enhancements with Spell-Like Ability:") {
      val e = ClassEnhancement.RapidCondensation
      val s = ClassEnhancement.SpellCriticalChancePositiveAndNegativeIII
      val sb = ClassEnhancement.SmokeBomb
      val ks = ClassFeat.KiStrikeMagic
      logger.info(s"evaluating ${e.entryName} and ${s.entryName}")
      e.displayText shouldEqual "Spell-Like Ability: Rapid Condensation"
      sb.displayText shouldEqual "Spell-Like Ability: Smoke Bomb"
      ks.displayText shouldEqual "Ki Strike: Magic"
    }
  }
}
