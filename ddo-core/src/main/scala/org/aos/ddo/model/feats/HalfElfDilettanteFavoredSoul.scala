package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAllOfRace,
  RequiresAttribute
}

/** DilettanteFavored.bmp
  * Half-Elf Dilettante: Favored Soul	Passive	Able to use wands and scrolls as if you were a level one favored soul.
  * Half-Elf
  * 13 Charisma */
trait HalfElfDilettanteFavoredSoul
    extends FeatRequisiteImpl
    with HalfElfDilettante
    with Passive
    with RequiresAttribute
    with RequiresAllOfRace { self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Charisma, 13))
}
