package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Two Weapon Defense.png
  * Two Weapon Defense 	Passive 	Grants you a +1 bonus to your AC and 5 PRR when you wield two weapons. ( This bonus does not apply when fighting unarmed/with handwraps.) In DDO, this is not implemented as a shield bonus and stacks with the Shield spell.
  * *
  * Two Weapon Fighting
  * */
trait TwoWeaponDefense extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.TwoWeaponFighting)
}
