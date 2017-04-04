package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerKnowledgePotions
    extends FeatRequisiteImpl
    with ArtificerKnowledgePrefix
    with Passive
    with GrantsToClass
    with RequiresAllOfClass { self: ClassFeat =>
  private lazy val levels = List(2, 5, 8, 11, 14)
  override def anyOfClass: Seq[(CharacterClass, Int)] = levels.map { x =>
    (Artificer, x)
  }
  override def grantToClass: Seq[(CharacterClass, Int)] =
    levels.map { x =>
      (Artificer, x)
    }
}
