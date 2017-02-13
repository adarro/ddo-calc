package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettantePaladin.bmp
  * Half-Elf Dilettante: Paladin	Passive	Can add up to 2 points of your Charisma bonus to all saves (does not stack with the Divine Grace ability). Able to use wands and scrolls as if you were a level one paladin.
  * Half-Elf,
  * 13 Charisma
  */
protected[feat] trait HalfElfDilettantePaladin extends FeatRequisiteImpl with HalfElfDilettante with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
