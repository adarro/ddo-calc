package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * Created by adarr on 3/17/2017.
  */
trait WholenessOfBody
  extends FeatRequisiteImpl
      with Passive
      with GrantsToClass
      with RequiresAllOfClass {
    override def grantToClass: Seq[(CharacterClass, Int)] =
      List((Monk, 7))

    override def allOfClass: Seq[(CharacterClass, Int)] =
      List((Monk, 7))
  }
