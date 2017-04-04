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
  * You become invisible and charge forward.
  * During your escape, you move through monsters as if you were ethereal.
  * These effects last for six seconds.
  */
protected[feats] trait MistyEscape
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Warlock, 15))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFey)
}
