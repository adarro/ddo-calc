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
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute, RequiresBaB}

/**
 * Icon Feat Weapon Finesse.png Weapon Finesse Passive This feat allows the character to apply their dexterity modifier
 * to hit instead of the strength modifier when making melee attacks with light weapons and rapiers. The strength
 * modifier is still used as a bonus to the damage roll. * Dexterity 13 Base Attack Bonus +1 *
 */
trait WeaponFinesse
  extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresBaB with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def requiresBaB: Int = 1
}
