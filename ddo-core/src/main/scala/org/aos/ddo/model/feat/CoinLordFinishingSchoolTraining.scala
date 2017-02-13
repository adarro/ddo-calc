package org.aos.ddo.model.feat

import org.aos.ddo.model.favor.FavorPatron
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfPatron}

/**
  * Grants +2 bonus to your Diplomacy and Intimidate
  */
protected[feat] trait CoinLordFinishingSchoolTraining extends FeatRequisiteImpl with Passive with RequiresAllOfPatron {
  override def allOfPatron: Seq[(FavorPatron, Int)] = List((FavorPatron.TheCoinLords, 400))
}
