package org.aos.ddo.model.spells


/**
  * Base stackable trait used for determining saving throws.
  */
trait SavingThrows[T <: SpellSave] {
  self: SavingThrowResults =>
  def savingThrows: Seq[SpellSave]
}

trait SavingThrowsImpl[T <: SpellSave] extends SavingThrows[T] {
  self: SavingThrowResults =>
  def savingThrows: Seq[SpellSave] = IndexedSeq.apply()
}


