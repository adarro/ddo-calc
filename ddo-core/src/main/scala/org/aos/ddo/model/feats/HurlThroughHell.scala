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
  * Exile one target enemy from this existence.
  * An enemy succeeding on a Will save vs DC(10 + Warlock level + Charisma Modifier) is instead paralyzed and helpless with fear for 6 seconds.
  */
protected[feats] trait HurlThroughHell
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override protected def nameSource: String = "Hurl through Hell"

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Warlock, 15))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFiend)
}
