package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{ClassRequisite, FeatRequisiteImpl, FreeFeat}

/**
  * This feat negates the penalties from using bucklers, and small and Large Shields.
  */
protected[feats] trait ShieldProficiency extends FeatRequisiteImpl with ClassRequisite with Passive with FreeFeat {
  self: GeneralFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] =  List((CharacterClass.Barbarian,1))
}
