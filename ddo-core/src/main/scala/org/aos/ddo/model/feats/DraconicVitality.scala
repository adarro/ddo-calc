package org.aos.ddo.model.feats

import org.aos.ddo.model.favor.FavorPatron
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfPatron}

/**
  * Grants +2 bonus to your Diplomacy and Intimidate
  */
protected[feats] trait DraconicVitality extends FeatRequisiteImpl with Passive with RequiresAllOfPatron {
  override def allOfPatron: Seq[(FavorPatron, Int)] = List((FavorPatron.AgentsOfArgonnessen, 150))
}
