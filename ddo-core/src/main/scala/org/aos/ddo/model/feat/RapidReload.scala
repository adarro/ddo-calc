package org.aos.ddo.model.feat

import org.aos.ddo.model.item.weapon.WeaponCategory
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Rapid Reload.png
  * Rapid Reload 	Passive 	Allows Crossbows to be reloaded about 20% faster.
  * *
  * Proficiency: Light Crossbows
  * */
protected[feat] trait RapidReload extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.SimpleWeaponProficiency(WeaponCategory.LightCrossbow))
}
