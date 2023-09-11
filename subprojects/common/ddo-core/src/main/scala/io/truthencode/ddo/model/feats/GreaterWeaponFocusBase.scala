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
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
 * Icon Feat Greater Weapon Focus.png Greater Weapon Focus Passive Provides an additional +1 bonus
 * to attack rolls with the chosen weapon type and additional +2 stacking Melee Power or Ranged
 * Power. This feat stacks with Weapon Focus.
 */
protected[feats] trait GreaterWeaponFocusBase
  extends FeatRequisiteImpl with ClassRequisiteImpl with Passive with RequiresAllOfClass
  with FighterBonusFeat {
  self: GeneralFeat with RequiresAllOfFeat =>

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Fighter, 8))
}
