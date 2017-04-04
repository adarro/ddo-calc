package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Druid, Monk}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * Created by adarr on 3/17/2017.
  */
trait TimelessBody
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Druid, 15), (Monk, 17))

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Druid, 15), (Monk, 17))
}
