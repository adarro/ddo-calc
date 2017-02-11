package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/** Icon Feat Shot On The Run.png
  * Shot on the Run 	Passive 	Negates the penalty to your attack roll for firing ranged weapon while moving. Also gives +3 ranged power.
  * *
  * Point Blank Shot, Mobility
  * Dexterity 13, Base Attack Bonus 4+
  * */
protected[feat] trait ShotOnTheRun extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresBaB {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def allOfFeats: Seq[Feat] = List(Feat.PointBlankShot, Feat.Mobility)

  override def requiresBaB = 4
}
