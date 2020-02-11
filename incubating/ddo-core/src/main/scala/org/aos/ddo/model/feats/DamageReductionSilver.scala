/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.FavoredSoul
import org.aos.ddo.model.religions.{Amaunator, SilverFlame, SovereignHost}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfFeat
}

import scala.collection.immutable

trait DamageReductionSilver
    extends FeatRequisiteImpl
    with ForgottenRealmsReligionNonWarforged
    with EberronReligionNonWarforged
    with DamageReductionLevelBase
    with RequiresAnyOfFeat
    with RequiresAllOfClass
    with SilverFlame
    with TheSilverFlameFeatBase
    with SovereignHost
    with TheSovereignHostFeatBase
    with Amaunator
    with AmaunatorFeatBase { self: DeityFeat =>

  override def nameSource: String = "Silver"

  override def allOfClass: immutable.Seq[(HeroicCharacterClass, Int)] =
    List((FavoredSoul, 20))

  override def anyOfFeats: Seq[Feat] =
    List(DeityFeat.BelovedOfTheSovereignHost,
         DeityFeat.BelovedOfAmaunator,
         DeityFeat.BelovedOfTheSilverFlame)
}
