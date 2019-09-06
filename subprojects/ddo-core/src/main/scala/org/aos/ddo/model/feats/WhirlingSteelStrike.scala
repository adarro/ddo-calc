package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{ClassRequisiteImpl, FeatRequisiteImpl, RequiresAllOfClass}

trait WhirlingSteelStrike extends FeatRequisiteImpl
  with ClassRequisiteImpl
  with Passive
  with RequiresAllOfClass
  with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[Feat] = Seq(Feat.withName("Weapon Focus: Slashing"))

  override def anyOfFeats: Seq[Feat] = Seq(Feat.withName("Proficiency: Longswords"), DeityFeat.FollowerOfTheSovereignHost, RacialFeat.HalfElfDilettanteFighter)

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Monk, 1))
}
