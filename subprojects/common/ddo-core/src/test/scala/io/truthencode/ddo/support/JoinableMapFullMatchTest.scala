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
package io.truthencode.ddo.support

import io.truthencode.ddo.model.feats.Feat
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class JoinableMapFullMatchTest extends AnyFunSpec with Matchers with JoinAbleMap[String, Feat, Map[String, Feat]] {
  override implicit val joinOnKeys: Boolean = false

  override lazy val source = Feat.values.map { v =>
    v.displayText -> v
  }.toMap

  describe("Joins") {
    they("A Left Join B should equal B Right Join A and vice versa") {
      val f: Map[String, Feat] = Feat.values.map { v =>
        v.displayText -> v
      }.toMap

      // a LeftJoin
      leftJoinA should contain theSameElementsAs rightJoinB
      leftJoinB should contain theSameElementsAs rightJoinA
    }

  }
  describe("Left Joins") {
    they("should contain all of the unique left side plus any common elements of the right side") {
      leftJoinA should contain theSameElementsAs commonWithA
    }
  }
  describe("Right Joins") {
    they("should contall all the unique right side plus any common elements of the left side") {
      rightJoinA should contain theSameElementsAs commonWithB
    }
  }

}
