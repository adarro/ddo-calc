package org.aos.ddo.model.feat

import org.aos.ddo.model.item.weapon.WeaponCategory

/**
  * Created by adarr on 2/12/2017.
  */
protected[feat] trait WeaponProficiency {
  def weaponCategory: WeaponCategory
}
