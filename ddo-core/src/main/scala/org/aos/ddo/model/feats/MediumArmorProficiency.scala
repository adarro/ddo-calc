package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass._
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfFeat
}

/** Icon Feat Medium Armor Proficiency.png
  * Medium Armor Proficiency Passive
  * You are proficient with medium armor, and do not suffer armor penalties to your attack rolls when wearing medium armor.
  * You also gain 4 + 2/3rds of your base attack bonus in physical resistance when wearing medium armor.
  *
  * Light Armor Proficiency
  * */
protected[feats] trait MediumArmorProficiency
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with GrantsToClass
    with Passive
    with RequiresAllOfFeat { self: GeneralFeat =>
  private def firstLevelClasses =
    List(Barbarian, Cleric, FavoredSoul, Fighter, Paladin).map((_, 1))
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.LightArmorProficiency)
  override def grantToClass: Seq[(CharacterClass, Int)] = firstLevelClasses

}
