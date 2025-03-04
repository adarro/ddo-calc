/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ManyshotTest.scala
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
import io.truthencode.ddo.model.effect.Feature.printFeature
import io.truthencode.ddo.model.effect.features.Features
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ManyshotTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("ManyShot") {
    it("has features") {
      import Features.FeatureExtractor

      val fm = Feat.featureSet
      val ms = fm.filter(_._1.equals(GeneralFeat.Manyshot))
      val msf = GeneralFeat.Manyshot

      msf.namedFeatures.foreach { nf => nf._2.foreach { f => logger.info(printFeature(f)) } }
    }
  }
}
