package org.aos.ddo.model.spells

/**
  * Represents the maximum caster level when determining a spell or abilities' effectiveness when scaling by level.
  */
trait CasterLevelCap {
  val baseLevelCap : Option[Int]
}
