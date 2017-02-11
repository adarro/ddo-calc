package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * Icon Feat Precise Shot.png
  * Precise Shot 	Passive with Offensive Ranged Stance 	Your targeted ranged attacks will now pass through friends and foes alike, to strike your target. (No damage will be done other than to your target.) This feat also grants you the Offensive Ranged Stance 'Archer's Focus', which lets you deal progressively more damage while standing still.
  * *
  * Point Blank Shot
  */
trait PreciseShot extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: Feat =>
  override def allOfFeats = List(Feat.PointBlankShot)
}
