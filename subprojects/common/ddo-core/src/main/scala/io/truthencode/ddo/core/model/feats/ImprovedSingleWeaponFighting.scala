/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedSingleWeaponFighting.scala
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
 * Icon Feat Single Weapon Fighting.png Improved Single Weapon Fighting Passive Your Single-Weapon
 * Fighting bonus is increased to a +20% Combat Style bonus to attack speed and an additional 2
 * Melee Power (for a total of 4). You now apply 25% more of your appropriate ability score to your
 * damage instead of just your ability score (for instance, you add 1.25 times your Strength as
 * damage).
 *
 * Note: Further increases base amount of attacks per second from 1.6 to 1.8.[Unverified]
 *
 * Single Weapon Fighting 4 ranks of Balance Base Attack Bonus +6
 */
protected[feats] trait ImprovedSingleWeaponFighting
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with SkillRequisiteImpl with Passive
  with RequiresAllOfFeat with RequiresAnyOfSkill with RequiresBaB with FighterBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB: Int = 6

  override def oneOfSkill: Seq[(Skill, Int)] = List((Skill.Balance, 4))

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.SingleWeaponFighting)
}
