package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Created by adarr on 3/17/2017.
  */
trait UnarmedStrike
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass {

  private val levels = 4 to 20 by 4

  /**
    * Monks are granted this feat on 1st, 4,8,12,16 and 20th levels
    * @return Monk Levels
    */
  private def monkLevels = Seq((Monk, 1)) ++ levels.map((Monk, _))
  override def grantToClass: Seq[(CharacterClass, Int)] = monkLevels

  override def allOfClass: Seq[(CharacterClass, Int)] = monkLevels
}
