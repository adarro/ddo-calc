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
import io.truthencode.ddo.model.skill.UsingSkillSearchPrefix
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

/**
 * Testing name / identity and search prefixs, affixes and separators.
 */
class DisplayPropertiesTest extends AnyFunSpec with Matchers with LazyLogging {

  /**
   * A basic prefix that uses the text parameter value as thee display source with no additional
   * manipulation.
   * @param text
   *   main text source
   */
  case class SimplePrefix(text: String) extends DisplayName with Prefix {

    /**
     * Optional Prefix, used to separate sub-items such as Spell Critical Schools and also to
     * disambiguate certain entities such as Feat: precision.
     *
     * @return
     *   The Some("Prefixed") in this test
     */
    override def prefix: Option[String] = Some("Prefixed")

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = text
  }

  case class SkillSearchPrefix(text: String) extends DisplayName with UsingSkillSearchPrefix {

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = text

  }

  describe("A Basic name") {
    it("should have basic prefixes") {

      val exampleWithPrefix = SimplePrefix("example")
      exampleWithPrefix.displayText shouldEqual "Prefixed:example"
    }
  }
  describe("Search Prefixes") {
    they("should interopt with prefixes") {
      val skillSearchPrefix = SkillSearchPrefix("Test")
      skillSearchPrefix.displayText shouldEqual "Test"
      skillSearchPrefix.withPrefix shouldEqual "Skill:Test"

    }
  }
}
