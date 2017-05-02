package org.aos.ddo.model.religions

import org.aos.ddo.model.item.weapon.{FavoredWeapon, WeaponCategory}
import org.aos.ddo.model.worlds.Eberron

/**
  * Created by adarr on 4/9/2017.
  */
trait SovereignHost extends Eberron with FavoredWeapon  {
  override val favoredWeapon: WeaponCategory = WeaponCategory.Longsword
}