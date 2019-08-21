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
import org.aos.ddo.model.classes.CharacterClass.{Cleric, Fighter, Paladin}
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfFeat
}

/** Icon Feat Heavy Armor Proficiency.png
  * Heavy Armor Proficiency
  * Passive
  * You are proficient with heavy armor, and do not suffer armor penalties to your attack rolls when wearing heavy armor.
  * You also gain 6 + your base attack bonus in physical resistance when wearing heavy armor.
  * *
  * Medium Armor Proficiency
  */
protected[feats] trait HeavyArmorProficiency
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfFeat { self: GeneralFeat =>
  private def firstLevelClasses = List(Cleric, Fighter, Paladin).map((_, 1))
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.MediumArmorProficiency)
  override def grantToClass: Seq[(CharacterClass, Int)] = firstLevelClasses

}
