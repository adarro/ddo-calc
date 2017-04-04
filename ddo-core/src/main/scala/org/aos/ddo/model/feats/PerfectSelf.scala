package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * You have transcended your former race and are now considered a Lawful Outsider.
  * You have gained Damage Reduction 10 / Epic.
  * Warforged retain most living construct traits.
  */
trait PerfectSelf
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((Monk, 20))
}
