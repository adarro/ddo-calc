package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.LordOfBlades
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 4/7/2017.
  */
trait BladeswornTransformation
    extends FeatRequisiteImpl
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with EberronReligionWarforged
    with LordOfBlades
    with TheLordOfBladesFeatBase
    with Active { self: DeityFeat with Requisite with RequisiteType =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheLordOfBlades)

}
