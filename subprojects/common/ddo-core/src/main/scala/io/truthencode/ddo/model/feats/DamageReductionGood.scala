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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.FavoredSoul
import io.truthencode.ddo.model.religions.Vulkoor
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionGood
  extends FeatRequisiteImpl with EberronReligionNonWarforged with DamageReductionLevelBase
  with RequiresAnyOfFeat with RequiresAllOfClass with Vulkoor with TheVulkoorFeatBase {
  self: DeityFeat =>

  override def nameSource: String = "Good"

  override def allOfClass: immutable.Seq[(HeroicCharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfVulkoor)
}
