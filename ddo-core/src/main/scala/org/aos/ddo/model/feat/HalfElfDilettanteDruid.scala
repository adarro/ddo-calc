package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/** Druid.png
  * Half-Elf Dilettante: Druid	Passive	Able to use wands and scrolls as if you were a level one druid.
  * Half-Elf
  * 13 Wisdom */
trait HalfElfDilettanteDruid extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresAllOfRace {
  self: Feat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))
}
