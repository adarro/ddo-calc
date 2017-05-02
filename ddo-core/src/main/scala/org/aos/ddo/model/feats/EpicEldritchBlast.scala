package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 4/6/2017.
  */
trait EpicEldritchBlast
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with RequiresAllOfClass
    with Passive
    with ClassRestricted {
  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((Warlock, 12))
}
