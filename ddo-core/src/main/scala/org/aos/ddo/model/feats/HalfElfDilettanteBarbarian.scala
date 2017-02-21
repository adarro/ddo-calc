package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfRace, RequiresAttribute}

/** DilettanteArtificer.bmp	Half-Elf Dilettante: Artificer	Passive	You have watched the artificers of House Cannith work their trade. You gain proficiency with all crossbows, and Artificer Knowledge: Scrolls (You gain a +2 bonus to Use Magical Device checks when using scrolls, and their Caster Level is increased by one - this Caster Level increase is capped by your Intelligence modifier.) You are able to use wands and scrolls as if you are a level one artificer. For item use purposes you count as a level one artificer in addition to any other classes you possess (though this does not grant the ability to use Rune Arms).
  * Half-Elf
  * 13 Intelligence
  *DilettanteBarbarian.bmp
  * Half-Elf Dilettante: Barbarian	Passive	Damage Reduction 1/- (does not stack with Barbarian Damage Reduction).
  */
trait HalfElfDilettanteBarbarian extends FeatRequisiteImpl with HalfElfDilettantePreFix  with Passive with RequiresAttribute with RequiresAllOfRace {
  self: RacialFeat =>
  override def allOfRace: Seq[(Race, Int)] = List((Race.HalfElf, 1))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Constitution, 13))
}
