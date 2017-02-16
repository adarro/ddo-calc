package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.model.item.weapon.WeaponCategory
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass, RequiresAllOfFeat}

/** Icon Feat Rapid Reload.png
  * Rapid Reload 	Passive 	Allows Crossbows to be reloaded about 20% faster.
  * *
  * Proficiency: Light Crossbows
  * */
protected[feats] trait RapidReload extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAllOfClass {
  self: ClassFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer,1))

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.SimpleWeaponProficiency(WeaponCategory.LightCrossbow))
}
