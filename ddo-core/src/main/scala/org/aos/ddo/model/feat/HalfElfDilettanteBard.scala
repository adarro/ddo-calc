package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  *DilettanteBard.bmp
  * Half-Elf Dilettante: Bard	Active	Can produce a Bardic Fascinate effect three times per rest that mesmerizes nearby enemies, with a Will DC based on a Perform check (or 1d20 + Charisma Modifier if untrained) to negate. Able to use wands and scrolls as if you were a level one bard.
  * Half-Elf
  * 13 Charisma */
trait HalfElfDilettanteBard extends FeatRequisiteImpl with HalfElfDilettante with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
