package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfRace,
  RequiresAttribute
}

/**
  * DilettanteWizard.bmp
  * Half-Elf Dilettante: Wizard	Passive	Able to use wands and scrolls as if you were a level one wizard.
  * Half-Elf
  * 13 Intelligence
  */
protected[feats] trait HalfElfDilettanteWizard
    extends FeatRequisiteImpl
    with HalfElfDilettantePreFix
    with Passive
    with RequiresAttribute
    with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Intelligence, 13))
}
