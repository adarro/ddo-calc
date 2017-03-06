package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerKnowledgeScrolls
    extends FeatRequisiteImpl
    with ArtificerKnowledgePrefix
    with Active
    with RequiresAnyOfClass { self: ClassFeat =>
  private lazy val levels = List(1, 4, 7, 10, 13)
  override def anyOfClass: Seq[(CharacterClass, Int)] = levels.map { x =>
    (Artificer, x)
  }
}
