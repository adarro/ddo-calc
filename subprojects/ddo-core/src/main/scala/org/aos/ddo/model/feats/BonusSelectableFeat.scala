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
import org.aos.ddo.support.requisite.{ClassRequisite, Inclusion, Requisite, SelectableToClass}

/**
  * This trait indicates a particular feat can be selected in addition to the standard ones for a given class at a
  * given level.  Specifically it flags Wizard, Fighter, Monk or other classes that gain an extra feat at certain levels.
  */
trait BonusSelectableFeat {
  self: Feat =>

  val levels: Set[Int]

  def bonusCharacterClass: Seq[CharacterClass]

}

trait BonusSelectableFeatImpl extends BonusSelectableFeat with SelectableToClass {
  self: Feat with FeatType with Requisite with Inclusion =>
  override def bonusCharacterClass: Seq[CharacterClass] = Nil
 override def bonusSelectableToClass: Seq[(CharacterClass, Int)] = Nil
}
