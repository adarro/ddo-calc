/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SuperiorWeaponFocusBase.scala
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
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
 * Icon Feat Superior Weapon Focus.png Superior Weapon Focus Passive Provides an additional +1 bonus
 * to attack rolls with the chosen weapon type and additional +2 stacking Melee Power or Ranged
 * Power.
 *
 * @todo
 *   add Level 16 Fighter req Level 16: Fighter, Greater Weapon Focus Greater Weapon Specialization
 *   in the same Specific Weapon Type
 */
protected[feats] trait SuperiorWeaponFocusBase
  extends FeatRequisiteImpl with ClassRequisiteImpl with BonusSelectableToClassFeatImpl with Passive
  with RequiresAllOfClass with FighterBonusFeat {
  self: GeneralFeat & RequiresAllOfFeat =>

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Fighter, 16))
}
