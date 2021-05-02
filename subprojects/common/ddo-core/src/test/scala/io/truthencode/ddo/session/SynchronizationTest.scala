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
package io.truthencode.ddo.session

import org.scalatest.FunSpec
import io.truthencode.ddo.model.feats.Feat

import scala.collection.mutable

class SynchronizationTest extends FunSpec {
  describe("Give or Take") {
    it("should do both?") {

      val sampleSize = 15
      def portion: Int = sampleSize / 2 + (sampleSize / 3)
      assume(sampleSize > portion)
        val mL = mutable.IndexedSeq[Feat]()

      val list = Feat.values.take(sampleSize)
      val left = list.take(portion)
      val right = list.takeRight(portion)
      val common = left.intersect(right)
      println("we're here")
    }
  }
}
