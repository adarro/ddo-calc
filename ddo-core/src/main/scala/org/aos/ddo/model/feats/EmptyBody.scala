package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
  * Created by adarr on 3/17/2017.
  */
trait EmptyBody
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAnyOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Monk, 19))

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Monk, 19))
}
