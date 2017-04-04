package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Druid
import org.aos.ddo.support.requisite.{GrantsToClass, RequiresAllOfClass}

/**
  * Created by adarr on 3/17/2017.
  */
trait WildShapeDireBear
    extends WildShape
    with GrantsToClass
    with RequiresAllOfClass {
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Druid, 8), (Druid, 11))

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Druid, 8))
}
