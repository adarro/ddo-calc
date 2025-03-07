/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EffectTest.scala
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
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.model.effect.features.Features
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EffectTest extends AnyFunSpec with Matchers {
  describe("A general effect") {
    it("should be able to be instantiated") {
      val e = new Effect with Features {
        override def features: List[Feature[?]] = List()
      }
    }
  }
}
