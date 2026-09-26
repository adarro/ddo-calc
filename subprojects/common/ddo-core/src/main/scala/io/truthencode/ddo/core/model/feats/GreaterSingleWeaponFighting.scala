/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GreaterSingleWeaponFighting.scala
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
import io.truthencode.ddo.support.requisite._

/**
 * Icon Feat Single Weapon Fighting.png Greater Single Weapon Fighting Passive Your Single-Weapon
 * Fighting bonus is increased to a +30% Combat Style bonus to attack speed and 2 additional Melee
 * Power (for a total of 6) and 50% more of your appropriate ability score to your damage (similar
 * to Two-Handed Fighting).
 *
 * Note: Further increases base amount of attacks per second from 1.8 to 2.0.[Unverified] Improved
 * Single Weapon Fighting 7 ranks of Balance Base Attack Bonus +11
 */
trait GreaterSingleWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with SkillRequisiteImpl with Passive
  with RequiresAllOfFeat with RequiresAnyOfSkill with RequiresBaB with FighterBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB = 11

  override def oneOfSkill: Seq[(Skill, Int)] = List((Skill.Balance, 7))

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.ImprovedSingleWeaponFighting)
}
