/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedTwoWeaponFighting.scala
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
 * Icon Feat Improved Two Weapon Fighting.png Improved Two Weapon Fighting Passive
 *
 * This feat increases the chance to proc an off-hand attack by 20% (includes unarmed Monk),
 * bringing the total chance to 60%.
 *
 * Two Weapon Fighting Dexterity 17, Base Attack Bonus +6
 */
trait ImprovedTwoWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with ClassRequisiteImpl with Passive
  with RequiresAllOfFeat with AttributeRequisiteImpl with RequiresAllOfAttribute with RequiresBaB
  with GrantsToClass with FighterBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB: Int = 6

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.TwoWeaponFighting)

  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 17))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 6))
}
