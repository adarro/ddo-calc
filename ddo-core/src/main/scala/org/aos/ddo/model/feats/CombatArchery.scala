package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{RequiresAllOfFeat, RequiresAttribute}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait CombatArchery
    extends RequiresAllOfFeat
    with RequiresAttribute
    with Passive { self: EpicFeat =>
  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 21))

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.PointBlankShot)

}
