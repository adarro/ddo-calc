package org.aos.ddo.model.spells

import org.aos.ddo.model.classes.CharacterClass

trait CasterWithLevel {
  def characterClass : CharacterClass
  def level:Int
}


trait CasterLevels {
  def casterLevels : Seq[CasterWithLevel]
}