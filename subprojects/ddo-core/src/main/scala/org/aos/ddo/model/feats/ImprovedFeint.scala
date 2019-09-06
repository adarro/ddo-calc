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

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfFeat}

/**
  * Icon Feat Improved Feint.png
  * Improved Feint -- Active - Special Attack
  * A tactical melee attack which Bluffs the enemy, enabling Sneak Attacks.
  *
  * Combat Expertise,
  * MustContainAtLeastOne of : Sneak Attack or Half-Elf Dilettante: Rogue
  *
  * @todo Implement MustContainAtLeastOneOf(Sneak Attack or Half-Elf Dilettante: Rogue)
  */
protected[feats] trait ImprovedFeint extends FeatRequisiteImpl
  with Active
  with RequiresAnyOfFeat
  with RequiresAllOfFeat
  with FighterBonusFeat {
  self: GeneralFeat =>
  override def anyOfFeats: Seq[Feat] = List(ClassFeat.SneakAttack)

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.CombatExpertise)
}
