package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass.{Cleric, FavoredSoul, Paladin}
import org.aos.ddo.model.item.weapon.FavoredWeapon
import org.aos.ddo.support.requisite.{RaceRequisiteImpl, RequiresAllOfClass, RequiresAllOfFeat}

/**
  * This trait may be renamed if someone has a suggestion for a better name for the 3rd tier / 6th level ability
  */
trait DeityUniqueLevelBase
    extends ReligionFeatBase
      with RaceRequisiteImpl
      with RequiresAllOfClass
    with UniqueLevel { self: FavoredWeapon with RequiresAllOfFeat =>
  List(Cleric, Paladin, FavoredSoul).map((_, 6))

}
