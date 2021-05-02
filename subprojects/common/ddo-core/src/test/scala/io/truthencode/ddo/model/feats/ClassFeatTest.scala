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
package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.spells.alchemical.{Primer, Reaction}
import org.scalatest.{FunSpec, Matchers}

class ClassFeatTest extends FunSpec with Matchers with LazyLogging {

  describe("Alchemists") {
    it("should have Alchemical Studies") {
      val cf = ClassFeat.values
      logger.info(s"found ${cf.size} values")
      val c: ClassFeat.AlchemicalStudies = ClassFeat.AlchemicalStudies(Reaction.Orchidium)
      cf should contain(c)
    }
  }
}
