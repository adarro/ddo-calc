package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass._
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat
}

/**
  * This feat negates the penalties from using bucklers, and small and Large Shields.
  */
protected[feats] trait ShieldProficiency
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with FreeFeat { self: GeneralFeat =>
  private def firstLevelClasses =
    List(Barbarian, Cleric, FavoredSoul, Fighter, Paladin, Ranger, Rogue).map(
      (_, 1))
  override def grantToClass: Seq[(CharacterClass, Int)] = firstLevelClasses

}
