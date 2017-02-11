package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Mobility.png
  * Mobility
  * Passive
  * Increases the maximum dexterity bonus permitted by armor and tower shields by 2, and adds a +4 bonus to Armor Class while the character is tumbling.
  * You will also gain a 2% dodge bonus.
  * Dodge
  */
trait Mobility extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.Dodge)
}
