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

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.model.feats.GeneralFeat.WeaponSpecialization
import org.aos.ddo.model.item.weapon.WeaponClass
import org.aos.ddo.support.requisite.{ClassRequisiteImpl, FeatRequisiteImpl, GrantsToClass, RequiresAllOfFeat, RequiresAnyOfFeat, RequiresBaB}


/**
  * Feat bow strength.png
  * Bow Strength
  * Passive
  * Strength bonus to damage is applied to Longbow and Shortbow.
  *
  * Point Blank Shot, Weapon Focus: Ranged Weapons
  * Base Attack Bonus +4
  * MustContainAtLeastOne of: Weapon Specialization: Ranged Weapons, Power Attack, Combat Expertise, Zen Archery
  **/
protected[feats] trait BowStrength
  extends FeatRequisiteImpl
    // with ClassRequisiteImpl
    with Passive
    with RequiresBaB
    with RequiresAllOfFeat
    with RequiresAnyOfFeat
    with FighterBonusFeat with GrantsToClass {
  self: GeneralFeat =>
  override def anyOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PowerAttack,
      GeneralFeat.CombatExpertise,
      GeneralFeat.ZenArchery) ++ specializations

  private def specializations = GeneralFeat.weaponSpecializationAny collect {
    case x: WeaponSpecialization if x.weaponClass == WeaponClass.Ranged => x
  }

  override def requiresBaB = 4

  override def allOfFeats: Seq[GeneralFeat] =
    focus :+ GeneralFeat.PointBlankShot

  private def focus = GeneralFeat.weaponFocusAny collect {
    case x: WeaponFocusBase if x.weaponClass == WeaponClass.Ranged => x
  }

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Ranger, 1))
}
