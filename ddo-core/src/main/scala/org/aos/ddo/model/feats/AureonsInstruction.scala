package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Aureon, Religion}
import org.aos.ddo.support.naming.DisplayProperties
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RaceRequisiteImpl,
  RequiresAllOfFeat
}

/**
  * Created by adarr on 4/7/2017.
  */
trait AureonsInstruction
    extends FeatRequisiteImpl
    with EberronReligionNonWarforged
    with DeityUniqueLevelBase
    with RequiresAllOfFeat
    with Aureon
    with AureonFeatBase
    with Active
    with DisplayProperties { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAureon)

  override def displayText: String = "Aureon's Instruction"
}
