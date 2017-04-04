package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass._
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * This feat increases the maximum spell points by 80,
  * increases the spell critical chance by 5% and grants Echoes of Power if the spell points pool drops below 12.
  *
  * @@note granted Level 1 : Cleric, Druid, Favored Soul, Artificer, Sorcerer, Wizard, Warlock
  *     granted via enhancement for:
  *     Bard - third rank of Magical Studies from Spellsinger enhancements
  *     Ranger - third rank of Energy of the Wild from Arcane Archer enhancements
  *
  *     Any other class as a trainable feat
  */
protected[feats] trait MagicalTraining
    extends FeatRequisiteImpl
    with Passive
    with FreeFeat
    with ClassRequisiteImpl
    with GrantsToClass { self: GeneralFeat =>
  private def magicClasses =
    List(Cleric, Druid, FavoredSoul, Artificer, Sorcerer, Wizard, Warlock)
  override def grantToClass: Seq[(CharacterClass, Int)] =
    magicClasses.map((_, 1))
}
