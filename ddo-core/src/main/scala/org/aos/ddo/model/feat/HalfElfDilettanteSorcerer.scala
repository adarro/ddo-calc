package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettanteSorcerer.bmp
  * Half-Elf Dilettante: Sorcerer	Passive	Able to use wands and scrolls as if you were a level one sorcerer.
  * Half-Elf
  * 13 Charisma
  */
protected[feat] trait HalfElfDilettanteSorcerer extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
