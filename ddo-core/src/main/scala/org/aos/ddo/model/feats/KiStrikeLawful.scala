package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * [[http://ddowiki.com/page/Ki_Strike:_Magic]]
  * All of your unarmed melee attacks are empowered with ki and are considered magical for damage reduction purposes.
  */
protected[feats] trait KiStrikeLawful
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with KiStrikePrefix
    with RequiresAllOfClass { self: ClassFeat =>

  override protected def nameSource: String = "Lawful"

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Monk, 10))
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk, 10))
}
