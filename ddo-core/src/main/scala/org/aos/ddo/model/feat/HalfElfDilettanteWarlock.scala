package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * Warlock.png
  * Half-Elf Dilettante: Warlock
  * Passive	Able to use wands and scrolls as if you were a level one warlock.
  * Toggle: You deal 1D4 extra Fire damage with attacks and spells.
  * This trigger at most once every two seconds. This toggle is exclusive with Warlock Pact toggles.
  * Half-Elf
  * 13 Charisma
  */
protected[feat] trait HalfElfDilettanteWarlock extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
