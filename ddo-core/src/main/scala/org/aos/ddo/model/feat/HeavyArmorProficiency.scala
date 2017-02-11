package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Heavy Armor Proficiency.png
  * Heavy Armor Proficiency 	Passive 	You are proficient with heavy armor, and do not suffer armor penalties to your attack rolls when wearing heavy armor.
  * You also gain 6 + your base attack bonus in physical resistance when wearing heavy armor.
  * *
  * Medium Armor Proficiency
  */
protected[feat] trait HeavyArmorProficiency extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.MediumArmorProficiency)
}
