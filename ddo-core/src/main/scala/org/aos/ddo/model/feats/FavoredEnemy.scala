package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.model.compendium.types.MainType
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  *Activate this ability to lower an animal's or beast's aggression, effectively mesmerizing them.
  *
  * Target: Enemy animals and magical beasts
  * Duration: 5 Min
  * Save: Will
  */
protected[feats] trait FavoredEnemy
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with FreeFeat { self: MainType =>
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Ranger, 1))
}
