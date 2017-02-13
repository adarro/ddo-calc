package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettanteWizard.bmp
  * Half-Elf Dilettante: Wizard	Passive	Able to use wands and scrolls as if you were a level one wizard.
  * Half-Elf
  * 13 Intelligence
  */
protected[feat] trait HalfElfDilettanteWizard extends FeatRequisiteImpl with HalfElfDilettante with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Intelligence, 13))
}
