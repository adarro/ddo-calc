package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfRace,
  RequiresAttribute
}

/**
  * DilettanteRogue.bmp
  * Half-Elf Dilettante: Rogue	Passive	You deal +1d6 Sneak Attack damage (does not stack with the Rogue Sneak Attack ability).
  * Half-Elf,
  * 13 Dexterity
  */
protected[feats] trait HalfElfDilettanteRogue
    extends FeatRequisiteImpl
    with HalfElfDilettante
    with Passive
    with RequiresAttribute
    with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))
}
