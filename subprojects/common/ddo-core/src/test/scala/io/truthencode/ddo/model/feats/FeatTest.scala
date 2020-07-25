/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.model.feats.ClassFeat.QuiveringPalm
import io.truthencode.ddo.model.feats.GeneralFeat.{
  ImprovedSunder,
  ImprovedTrip,
  Sap,
  StunningBlow,
  StunningFist,
  Sunder,
  Trip
}
import org.scalatest.{FunSpec, Matchers}

class FeatTest extends FunSpec with Matchers with LazyLogging {

  describe("Feats") {
    it("should contain all types of feats") {
      noException shouldBe thrownBy(Feat.values)
    }
    it("should automatically create proper display text / name") {
      val feats = Feat.values
      feats.foreach { x =>
        val idInfo = Map(
          "entryName" -> x.entryName,
          "displaySource" -> x.displaySource,
          "displayText" -> x.displayText
        )
        logger.debug(s"Id Info $idInfo")

      }
    }

    they("should include Tactical Feats") {
      val tactical = List(
        Trip,
        ImprovedTrip,
        Sunder,
        ImprovedSunder,
        Sap,
        StunningBlow,
        StunningFist,
        QuiveringPalm
      ) // .map(_.entryName).sorted
      val tacticalFeats = { Feat.values collect Feat.fnTacticalFeats } // .map(_.entryName).sorted
      tactical should contain only (tacticalFeats : _*)

    }
  }
}
