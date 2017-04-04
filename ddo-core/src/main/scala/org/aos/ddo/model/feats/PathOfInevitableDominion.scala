package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * [[http://ddowiki.com/page/Path_of_Inevitable_Dominion]]
  * This philosophy is available at Monk level 3 by selecting the Fists of Darkness feat,
  *     which serves as the key to using any finishing moves of this path or qualifying for any special abilities connected to this path.
  * The Path of Inevitable Dominion philosophy gives five new Finishing Moves at
  *     level 3:
  *     Touch of Despair
  *     Pain Touch
  *     Falling Star Strike
  *     Freezing the Lifeblood
  *     Karmic Strike.
  *
  * On Monk level 10 and selection of the tier five Henshin Mystic enhancement Void Strike,
  *     a sixth finishing move
  *     Curse of the Void
  *     becomes available.
  * The Path of Inevitable Dominion is a prerequisite for the Ninjutsu and Touch of Death enhancements on the Ninja Spy tree.
  *
  * You may only choose one philosophy.
  * While players may see the moves from the Path of Harmonious Balance in their Feats list,
  *     the player cannot activate actions in this path without using a feat exchange to switch to the Fists of Light.
  *     @todo Need to add prohibits Harmonious Path
  */
protected[feats] trait PathOfInevitableDominion
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
}
