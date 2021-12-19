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
import io.truthencode.ddo.model.effect.features.Features
import io.truthencode.ddo.model.feats.GeneralFeat.ExoticWeaponProficiency
import io.truthencode.ddo.support.naming.DisplayProperties
import org.scalatest.funspec.AnyFunSpec

class WeaponProficiencyTest extends AnyFunSpec with LazyLogging {
  lazy val exotics: Seq[GeneralFeat with SubFeat with ExoticWeaponProficiency] = GeneralFeat.ExoticWeaponProficiency.subFeats.map {
      x: GeneralFeat with SubFeat with ExoticWeaponProficiency with DisplayProperties => x
  }
  describe("Weapon proficiency: Bastard Sword") {
    they("Exist") {

      exotics.foreach { e =>
          logger.info(s"entryName: ${e.displayText} | ${e.entryName} | Display Source ${e.displaySource} ") }
    }
  }
}
