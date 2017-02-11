package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * Half-Elf Dilettante: Monk	Passive	Proficiency with the quarterstaff, kama, and shuriken, and can add up to 2 points of your Wisdom bonus to your Armor Class as long as you are Defensively Centered (unarmored and unencumbered, does not stack with the similar monk class ability).
  * Half-Elf
  * 13 Wisdom
   */
trait HalfElfDilettanteMonk extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))
}
