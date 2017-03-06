package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerCraftMastery extends FeatRequisiteImpl with Passive with RequiresAllOfClass{
  private lazy val levels = (1 to 20).filter(_ %2 == 0)
  override def anyOfClass: Seq[(CharacterClass, Int)] = levels.map { x =>
    (Artificer, x)}

}
