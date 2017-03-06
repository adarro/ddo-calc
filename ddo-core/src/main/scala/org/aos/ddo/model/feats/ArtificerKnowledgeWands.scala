package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerKnowledgeWands
    extends FeatRequisiteImpl
    with ArtificerKnowledgePrefix
    with Passive
    with RequiresAllOfClass {
  self: ClassFeat=>
  private lazy val levels = List(3,6, 9, 12,15)
  override def anyOfClass: Seq[(CharacterClass, Int)] = levels.map { x =>
    (Artificer, x)
  }
}
