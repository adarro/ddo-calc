package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Cleric, Fighter, Paladin}
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfFeat
}

/** Icon Feat Heavy Armor Proficiency.png
  * Heavy Armor Proficiency
  * Passive
  * You are proficient with heavy armor, and do not suffer armor penalties to your attack rolls when wearing heavy armor.
  * You also gain 6 + your base attack bonus in physical resistance when wearing heavy armor.
  * *
  * Medium Armor Proficiency
  */
protected[feats] trait HeavyArmorProficiency
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfFeat { self: GeneralFeat =>
  private def firstLevelClasses = List(Cleric, Fighter, Paladin).map((_, 1))
  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.MediumArmorProficiency)
  override def grantToClass: Seq[(CharacterClass, Int)] = firstLevelClasses

}
