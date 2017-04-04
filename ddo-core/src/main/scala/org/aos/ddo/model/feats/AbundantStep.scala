package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * At the cost of 10 Ki, a monk can use this ability to make horizontal leaps,
  *    closing to targets, traversing chasms, or zipping past enemies with less chance of being detected.
  * There is a cool-down of three seconds on this feat and can only be used on yourself.
  */
trait AbundantStep
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Monk, 12))

  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((Monk, 12))
}
