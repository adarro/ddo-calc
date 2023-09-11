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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfFeat
}

import scala.collection.immutable

/**
 * Icon Feat Precise Shot.png Precise Shot Passive with Offensive Ranged Stance Your targeted ranged
 * attacks will now pass through friends and foes alike, to strike your target. (No damage will be
 * done other than to your target.)
 *
 * This feat also grants you the Offensive Ranged Stance 'Archer's Focus', which lets you deal
 * progressively more damage while standing still.
 *
 * Point Blank Shot
 */
trait PreciseShot
  extends FeatRequisiteImpl with ClassRequisiteImpl with Passive with RequiresAllOfFeat
  with GrantsToClass with FighterBonusFeat with ArtificerBonusFeat with AlchemistBonusFeat {
  self: GeneralFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 4))

  override def allOfFeats: immutable.Seq[Feat] =
    List(GeneralFeat.PointBlankShot)
}
