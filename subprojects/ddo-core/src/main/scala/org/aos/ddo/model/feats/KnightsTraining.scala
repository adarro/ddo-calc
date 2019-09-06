package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{ClassRequisiteImpl, FeatRequisiteImpl, GrantsToClass, RequiresAllOfFeat, RequiresAnyOfFeat, RequiresBaB}

protected[feats] trait KnightsTraining
  extends FeatRequisiteImpl
    // with ClassRequisiteImpl
    with Passive
    with RequiresBaB
    with FighterBonusFeat with GrantsToClass {
  self: GeneralFeat =>
  override def requiresBaB: Int = 4
  override val displayText = "Knight's Training"
}