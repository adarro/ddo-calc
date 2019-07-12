package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Monk, Ranger, Rogue}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
  * Created by adarr on 3/17/2017.
  */
trait Evasion
  extends FeatRequisiteImpl
      with Passive
      with GrantsToClass
      with FreeFeat {
    override def grantToClass: Seq[(CharacterClass, Int)] =
      List((Monk, 2),(Rogue, 2),(Ranger,9))

  }
