package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * +5 Magical Resistance Rating
  * This feat can be granted multiple times.
  */
protected[feats] trait ParagonsAegis
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with GrantsToClass { self: ClassFeat =>
  private def artiLevels = 12 to 20 by 2
  override def grantToClass: Seq[(CharacterClass, Int)] =
    artiLevels.map((Warlock, _))

  override protected def nameSource: String = "Paragon's Aegis"
}
