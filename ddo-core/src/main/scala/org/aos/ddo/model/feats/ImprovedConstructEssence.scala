package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{
  RequiresAllOfClass,
  RequiresAllOfFeat,
  RequiresNoneOfRace
}

/**
  * Created by adarr on 4/5/2017.
  */
trait ImprovedConstructEssence
    extends RequiresAllOfFeat
    with RequiresAllOfClass
    with RequiresNoneOfRace
    with Passive {
  override def allOfFeats: Seq[Feat] = List(ClassFeat.ConstructEssence)
}
