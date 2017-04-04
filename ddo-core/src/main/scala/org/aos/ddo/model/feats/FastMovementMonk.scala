package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.naming.PostText
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass, RequiresAllOfClass}

/**
  * [[http://ddowiki.com/page/Fast_Movement_(monk_class_feat)]]
  * A Monk gains a cumulative 5% bonus to run speed while centered for every three Monk levels.
  * This bonus stacks with other run speed bonuses.
  * If the monk would become uncentered (by equipping armor, or wielding non-monk weapons, etc) then the bonuses are temporarily lost.
  */
protected[feats] trait FastMovementMonk
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with PostText
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
  override def nameSource: String = "Fast Movement"
  override def postText: Option[String] = Some("Monk Class Feat")

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
}
