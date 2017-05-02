package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 4/6/2017.
  */
trait VorpalStrikes
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with RequiresAllOfClass
    with Active
    with ClassRestricted {
  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((Monk, 12))

}
