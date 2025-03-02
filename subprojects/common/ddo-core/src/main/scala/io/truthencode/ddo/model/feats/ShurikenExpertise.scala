/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ShurikenExpertise.scala
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
import io.truthencode.ddo.model.feats.GeneralFeat.ExoticWeaponProficiency
import io.truthencode.ddo.model.item.weapon.WeaponCategory
import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite._

/**
 * Shuriken Expertise.PNG Shuriken Expertise Passive You are skilled with the use of the shuriken,
 * and have a chance to throw an additional one per throw. (Percent chance to throw an additional
 * shuriken is equal to your Dexterity.) This is also a racial feat given to all Drow Elf at level
 * 1, regardless of class. * Dexterity 13 MustContainAtLeastOne of: Proficiency: Shuriken or
 * Half-Elf Dilettante: Monk AND Dex 13 Or: Drow
 *
 * @todo
 *   Apply multi-conditional logic dor Shuriken Expertise
 */
protected[feats] trait ShurikenExpertise
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with RaceRequisiteImpl with Passive
  with AttributeRequisiteImpl with RequiresAllOfAttribute with RequiresAnyOfFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def anyOfFeats: Seq[Feat] =
    exotic :+ RacialFeat.HalfElfDilettanteMonk

  private def exotic = GeneralFeat.exoticWeaponProficiencies.collect {
    case x: ExoticWeaponProficiency if x.weapon.headOption.contains(WeaponCategory.Shuriken) =>
      x
  }

  override def grantsToRace: Seq[(Race, Int)] = List((Race.DrowElf, 1))
}
