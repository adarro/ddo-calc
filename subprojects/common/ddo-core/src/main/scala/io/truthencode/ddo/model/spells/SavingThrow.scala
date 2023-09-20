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
package io.truthencode.ddo.model.spells

import io.truthencode.ddo.model.save.Save
import io.truthencode.ddo.support.naming.FriendlyDisplay

/**
 * Base trait for determining saves against spell. Saving Throw Difficulty Class
 *
 * @note
 *   A saving throw against your spell has a DC of 10 + (the level of the spell) + (your bonus for
 *   the relevant Ability) + (any relevant items and or buffs). (Intelligence for a Wizard, Charisma
 *   for a Sorcerer or Bard, and Wisdom for a Cleric, Favored Soul, Paladin, or Ranger.) A spell's
 *   level can vary depending on your class. Always use the spell level applicable to your class.
 */
sealed trait SpellSave extends Save with FriendlyDisplay

trait SpellSaveImpl extends SpellSave {
  self: SavingThrowResults =>
}

trait ReflexSave extends SpellSaveImpl {
  self: SavingThrowResults =>
  override protected def nameSource: String = "Ref"
}

trait FortitudeSave extends SpellSaveImpl {
  self: SavingThrowResults =>
  override protected def nameSource: String = "Fort"
}

trait WillSave extends SpellSaveImpl {
  self: SavingThrowResults =>
  override protected def nameSource: String = "Will"
}

object Sample {
  val rs: ReflexSave with SavingThrowResults = new ReflexSave with SavingThrowResults {
    override def savingThrowResult: SavingThrowResult = SavingThrowResult.None
  }

}

case class SavingThrow(saveType: List[SpellSave], savingThrowResult: SavingThrowResult)
  extends SpellSaveImpl with SavingThrowResults {

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = "bob"
}
