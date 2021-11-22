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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite._

/**
 * Icon Feat Improved Precise Shot.png [[https://ddowiki.com/page/Improved_Precise_Shot Improved Precise Shot]] Active -
 * Ranged Combat Stance
 *
 * Your ranged attacks will now pass through friends and foes to damage all foes in your path, until they strike your
 * intended target.
 *
 * Point Blank Shot, Precise Shot, Dexterity 19, Base Attack Bonus +11
 *
 * Notes A ranger gets this feat for free at level 11, even if he does not meet the prerequisites. Artificers may select
 * this feat as one of their artificer bonus feats. Fighters may select this feat as one of their fighter bonus feats.
 * Tips:
 *
 * Offensive Ranged Stance: Incompatible with Archer's Focus. Many special attacks, even those that usually affect only
 * a single target, can affect all enemies in the path. The projectile continues beyond your selected target. Race
 * height matters. Tall races, such as Bladeforged, shoot their projectiles from a higher elevation. When targeting
 * smaller enemies, projectile travels downwards and often harmlessly flies above the head of nearby enemies. Targeting
 * is easier for smaller races, particularly Halflings.
 */
protected[feats] trait ImprovedPreciseShot
  extends FeatRequisiteImpl with ActiveFeat with RequiresAllOfFeat with RequiresAttribute with RequiresBaB
  with ClassRequisiteImpl with GrantsToClass with FighterBonusFeat with ArtificerBonusFeat with OffensiveRangedStance
  with DefaultCoolDown {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PointBlankShot, GeneralFeat.PreciseShot)

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 11

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 19))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 11))
}
