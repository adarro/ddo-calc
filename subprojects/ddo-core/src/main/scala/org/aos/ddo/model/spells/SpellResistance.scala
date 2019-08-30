package org.aos.ddo.model.spells

trait SpellResistance {
  val sr: Option[Int]

  def hasSpellResistance: Boolean = sr.isDefined
}
