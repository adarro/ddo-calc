package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfFeat
}

import scala.collection.immutable

/**
  * Icon Feat Precise Shot.png
  * Precise Shot
  * Passive with Offensive Ranged Stance
  * Your targeted ranged attacks will now pass through friends and foes alike, to strike your target.
  * (No damage will be done other than to your target.)
  *
  * This feat also grants you the Offensive Ranged Stance 'Archer's Focus', which lets you deal progressively more damage while standing still.
  *
  * Point Blank Shot
  */
trait PreciseShot
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with GrantsToClass { self: GeneralFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 4))

  override def allOfFeats: immutable.Seq[Feat] =
    List(GeneralFeat.PointBlankShot)
}
