package org.aos.ddo.model.spells

import org.aos.ddo.model.save.Save
import org.aos.ddo.support.naming.FriendlyDisplay

/**
  * Base trait for determining saves against spell.
  * Saving Throw Difficulty Class
  *
  * @note A saving throw against your spell has a DC of 10 + (the level of the spell) +
  *       (your bonus for the relevant Ability) + (any relevant items and or buffs).
  *       (Intelligence for a Wizard, Charisma for a Sorcerer or Bard, and
  *       Wisdom for a Cleric, Favored Soul, Paladin, or Ranger.)
  *       A spell's level can vary depending on your class. Always use the spell level applicable to your class.
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


case class SavingThrow(saveType: List[SpellSave], savingThrowResult: SavingThrowResult) extends SpellSaveImpl with SavingThrowResults {
  /**
    * Sets or maps the source text for the DisplayName.
    *
    * @return Source text.
    */
  override protected def nameSource: String = "bob"
}



