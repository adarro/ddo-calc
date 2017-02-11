package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Medium Armor Proficiency.png
  * Medium Armor Proficiency Passive
  * You are proficient with medium armor, and do not suffer armor penalties to your attack rolls when wearing medium armor.
  * You also gain 4 + 2/3rds of your base attack bonus in physical resistance when wearing medium armor.
  *
  * Light Armor Proficiency
  * */
protected[feat] trait MediumArmorProficiency extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.LightArmorProficiency)
}
