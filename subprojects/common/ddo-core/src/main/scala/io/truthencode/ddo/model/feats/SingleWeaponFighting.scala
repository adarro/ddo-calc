/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SingleWeaponFighting.scala
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

import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAnyOfSkill,
  SkillRequisiteImpl
}

/**
 * Icon Feat Single Weapon Fighting.png Single Weapon Fighting Passive While Single Weapon Fighting,
 * you gain +10% Combat Style bonus to attack speed and 2 Melee Power.
 *
 * Single Weapon Fighting: Requires fighting with a single one-handed weapon, and wielding only an
 * orb, runearm, or nothing in your off hand.
 *
 * Note: Actually increases base number of attacks per second from 1.4 to 1.6.[Unverified] 2 ranks
 * of Balance
 */
protected[feats] trait SingleWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with SkillRequisiteImpl with Passive
  with RequiresAnyOfSkill with FighterBonusFeat with MartialArtsFeat {
  self: GeneralFeat =>
  override def oneOfSkill: Seq[(Skill, Int)] = List((Skill.Balance, 2))
}
