package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Rogue
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 4/6/2017.
  */
trait ImprovedSneakAttack
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with RequiresAllOfClass
    with RequiresAttribute
    with Passive
    with ClassRestricted {
  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((Rogue, 12))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity,21))
}
