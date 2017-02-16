package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfClass}

/** Icon Enhancement Instinctive Fighting.png
  * Natural Fighting 	Passive 	This feat improves the double strike chance of a druid in animal or magical beast form by 6%. It may be taken up to 3 times.
  *
  * Level 9: Druid
  *
  * @todo add can attain 3 times
  **/
trait NaturalFighting extends FeatRequisiteImpl with Passive with RequiresAllOfClass {
  self: GeneralFeat =>
  override def allOfClass: Seq[(CharacterClass, Int)] = List((CharacterClass.Druid, 9))
}
