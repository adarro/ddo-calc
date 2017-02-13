package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettanteRogue.bmp
  * Half-Elf Dilettante: Rogue	Passive	You deal +1d6 Sneak Attack damage (does not stack with the Rogue Sneak Attack ability).
  * Half-Elf,
  * 13 Dexterity
  */
protected[feat] trait HalfElfDilettanteRogue extends FeatRequisiteImpl with HalfElfDilettante with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))
}
