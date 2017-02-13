package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettanteRanger.bmp
  * Half-Elf Dilettante: Ranger	Passive	Proficiency with all martial ranged weapons, and can add up to 2 points of your Strength bonus to bow damage. Able to use wands and scrolls as if you were a level one ranger.
  * Half-Elf
  * 13 Dexterity
  */
protected[feat] trait HalfElfDilettanteRanger extends FeatRequisiteImpl with HalfElfDilettante with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))
}
