package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.item.weapon.WeaponClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass, RequiresAllOfFeat}

/** Icon Feat Greater Weapon Focus.png
  * Greater Weapon Focus 	Passive 	Provides an additional +1 bonus to attack rolls with the chosen weapon type and additional +2 stacking Melee Power or Ranged Power. This feat stacks with Weapon Focus.
  * */
protected[feat] trait GreaterWeaponFocus extends FeatRequisiteImpl with Passive with RequiresAllOfClass {

  self: WeaponClass with Feat with RequiresAllOfFeat =>

  override def anyOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Fighter, 8))
}
