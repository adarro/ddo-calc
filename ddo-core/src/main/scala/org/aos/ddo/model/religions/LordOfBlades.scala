package org.aos.ddo.model.religions

import org.aos.ddo.model.item.weapon.{FavoredWeapon, WeaponCategory}
import org.aos.ddo.model.worlds.ForgottenRealms

/**
  * Created by adarr on 4/9/2017.
  */
trait LordOfBlades extends ForgottenRealms with FavoredWeapon {
  override val favoredWeapon: WeaponCategory = WeaponCategory.Greatsword
}