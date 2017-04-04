package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * [[http://ddowiki.com/page/Path_of_Harmonious_Balance]]
  * This philosophy is available at Monk level 3 by selecting the Fists of Light feat,
  * which serves as the key to using any finishing moves of this path or qualifying for any special abilities connected to this path
  * The Path of Harmonious Balance philosophy gives five new Finishing Moves at
  *     level 3:
  *     Healing Ki
  *     Aligning the Heavens
  *     Grasp the Earth Dragon
  *     Dance of Clouds
  *     Walk of the Sun.
  * On Monk level 10 and selection of the Void Strike attack enhancement
  * a sixth finishing move, Moment of Clarity, becomes available.
  * The Path of Harmonious Balance is a prerequisite for the Shintao Monk enhancements.
  * You may only choose one philosophy.
  * @note While players may see the moves from the Path of Inevitable Dominion in their Feats list,
  *       the player cannot activate actions in this path without using a feat exchange to switch to the Fists of Darkness.
  */
protected[feats] trait PathOfHarmoniousBalance
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
}
