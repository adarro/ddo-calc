package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerCraftMastery
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass {
  private lazy val levels = (1 to 20).filter(_ % 2 == 0)
  override def allOfClass: Seq[(CharacterClass, Int)] = levels.map { x =>
    (Artificer, x)
  }
  override def grantToClass: Seq[(CharacterClass, Int)] =
    levels.map { x =>
      (Artificer, x)
    }

}
