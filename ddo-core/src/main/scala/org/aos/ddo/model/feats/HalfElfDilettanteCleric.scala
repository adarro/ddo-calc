package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/** DilettanteCleric.bmp
  * Half-Elf Dilettante: Cleric	Passive	Able to use wands and scrolls as if you were a level one cleric.
  * Half-Elf
  * 13 Wisdom */
trait HalfElfDilettanteCleric extends FeatRequisiteImpl with HalfElfDilettante  with Passive with RequiresAttribute with RequiresAllOfRace {
  self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))
}
