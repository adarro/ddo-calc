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
  * Icon Feat Light Armor Proficiency.png
  * Light Armor Proficiency
  * Passive
  * You are proficient with light armor, and do not suffer armor penalties to your attack rolls when wearing light armor.
  * You also gain 2 + 1/2 of your base attack bonus in physical resistance when wearing light armor.
  *
  * None
  */
protected[feats] trait LightArmorProficiency
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with GrantsToClass
    with Passive
    with FreeFeat { self: GeneralFeat =>
  private def firstLevelClasses =
    List(Barbarian,
         Bard,
         Cleric,
         FavoredSoul,
         Fighter,
         Paladin,
         Ranger,
         Rogue,
         Warlock)
      .map((_, 1))
  override def grantToClass: Seq[(CharacterClass, Int)] = firstLevelClasses

}
