package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Cleric, FavoredSoul, Paladin}
import org.aos.ddo.model.item.weapon.FavoredWeapon
import org.aos.ddo.support.requisite.RequiresAnyOfClass

/**
  * Base properties common to all follower Deity Feats
  */
trait FollowerBase
    extends Passive
    with FollowerOfLevel
    with RequiresAnyOfClass
    with ReligionFeatBase { self: FavoredWeapon =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List(Cleric, Paladin, FavoredSoul).map((_, 1))
}
