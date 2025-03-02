/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TwoWeaponFighting.scala
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
import io.truthencode.ddo.support.requisite._

/**
 * Prerequisites Icon Feat Two Weapon Fighting.png Two Weapon Fighting Passive Reduces the to-hit
 * penalty when using two weapons at the same time. The penalty for your primary hand lessens by 2
 * and the one for your off-hand lessens by 6, resulting in -4/-4 (instead of -6/-10 without this
 * feat). If the off-hand weapon is light, both penalties decrease by another 2 points, down to
 * -2/-2 (instead of -4/-8 without this feat). Two Weapon Fighting also increases the chance to proc
 * an off-hand attack by 20% (applies to unarmed Monk), bringing the total chance to 40%.
 *
 * Dexterity 15
 */
trait TwoWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with ClassRequisiteImpl
  with GrantsToClass with AttributeRequisiteImpl with RequiresAllOfAttribute with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 15))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 2))
}
