package org.aos.ddo.model.feats

import org.aos.ddo.model.item.weapon.WeaponCategory

/**
  * Created by adarr on 2/12/2017.
  */
protected[feats] trait WeaponProficiency {
  def weaponCategory: WeaponCategory
}
