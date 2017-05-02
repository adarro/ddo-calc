package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass.{Cleric, FavoredSoul, Paladin}
import org.aos.ddo.model.item.weapon.FavoredWeapon
import org.aos.ddo.support.requisite.{RequiresAllOfClass, RequiresAllOfFeat}

/**
  * Created by adarr on 4/10/2017.
  */
trait ChildLevelBase
    extends ReligionFeatBase
    with Passive
    with RequiresAllOfClass
    with ChildOfLevel { self: FavoredWeapon with RequiresAllOfFeat =>
  List(Cleric, Paladin, FavoredSoul).map((_, 3))

}
