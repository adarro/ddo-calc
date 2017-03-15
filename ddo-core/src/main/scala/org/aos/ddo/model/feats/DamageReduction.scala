package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Barbarian
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Damage_Reduction Damage Reduction]]
  * Damage Reduction (passive): Grants damage reduction 1/- to all unarmed, weapon, or natural attacks.
  * Note: this increases with level.
  */
protected[feats] trait DamageReduction
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 2))
}
