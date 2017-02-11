package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB}

/**
  * Icon Feat Whirlwind Attack.png
  * Whirlwind Attack 	Active - Special Attack 	This feat attacks all enemies in a 360 degree arc around the character. This attack deals +4[W] damage.
  *
  * Dodge, Mobility, Spring Attack
  * Combat Expertise, Base Attack Bonus +4
  *
  * @note Only Combat Expertise and Spring Attack are required as feats. However, Spring Attack depends on Mobility with Depends on Dodge
  *       and are listed on the Wiki as such. Nested dependencies should be inferred and this list may remove all but explicit dependencies.
  */
protected[feat] trait WhirlwindAttack extends FeatRequisiteImpl with Active with RequiresAllOfFeat with RequiresBaB {
  self: Feat =>
  /**
    * The Minimum Required Base Attack Bonus
    *
    * @return Minimum value allowed
    */
  override def requiresBaB: Int = 4

  override def allOfFeats: Seq[Feat] = List(Feat.Dodge, Feat.Mobility, Feat.SpringAttack, Feat.CombatExpertise)
}
