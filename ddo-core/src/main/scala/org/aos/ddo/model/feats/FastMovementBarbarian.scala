package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Barbarian
import org.aos.ddo.support.naming.PostText
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * [[http://ddowiki.com/page/Fast_Movement_(barbarian_class_feat)]]
  * The Fast Movement class feat allows a Barbarian to move 10% faster.
  */
protected[feats] trait FastMovementBarbarian
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with PostText
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Barbarian, 1))
  override def nameSource: String = "Fast Movement"
  override def postText: Option[String] = Some("Barbarian Class Feat")

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Barbarian, 1))
}
