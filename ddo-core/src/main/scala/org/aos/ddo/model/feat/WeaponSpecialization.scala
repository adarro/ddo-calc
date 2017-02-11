package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.item.weapon.WeaponClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass, RequiresAllOfFeat}

/** Weapon Specialization Passive Feats
  * Icon 	Feat 	Type 	Description 	Prerequisites
  * Icon Feat Weapon Specialization.png
  * Weapon Specialization 	Passive 	Provides a +2 bonus to damage rolls with the chosen weapon type and +2 stacking Melee Power or Ranged Power. This feat can be taken multiple times, once for each weapon type.
  */
protected[feat] trait WeaponSpecialization extends FeatRequisiteImpl with Passive with RequiresAllOfClass {

  self: Feat with WeaponClass with RequiresAllOfFeat =>

  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 4))
}
