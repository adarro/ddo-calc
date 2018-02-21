package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/**
  * DilettanteBarbarian.bmp
  * Half-Elf Dilettante: Barbarian
  * Passive
  * Damage Reduction 1/- (does not stack with Barbarian Damage Reduction).
  */
trait HalfElfDilettanteBarbarian extends FeatRequisiteImpl with HalfElfDilettantePreFix  with Passive with RequiresAttribute with RequiresAllOfRace {
  self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Constitution, 13))
}
