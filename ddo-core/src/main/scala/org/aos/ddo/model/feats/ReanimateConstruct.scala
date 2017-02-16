package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ReanimateConstruct extends FeatRequisiteImpl with Active with RequiresAllOfClass{
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer,1))
}
