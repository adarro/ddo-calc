package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
  * Plunge one enemy's mind into an illusory realm. That enemy is dazed and cannot act for 20 seconds.
  * Each time this enemy is damaged, there's a 10% chance they break free.
  */
protected[feats] trait DarkDelirium
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Warlock, 6))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFey)
}
