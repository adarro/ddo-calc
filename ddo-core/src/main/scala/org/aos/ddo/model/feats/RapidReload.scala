package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.model.item.weapon.WeaponCategory
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}
import org.aos.ddo.model.FeatConverters.featByWeaponProficiency

/** Icon Feat Rapid Reload.png
  * Rapid Reload
  * Passive Allows Crossbows to be reloaded about 20% faster.
  *
  * Proficiency: Light Crossbows
  * */
protected[feats] trait RapidReload
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Artificer, 1))

  override def allOfFeats: Seq[GeneralFeat] =
    List(WeaponCategory.LightCrossbow) collect featByWeaponProficiency
}
