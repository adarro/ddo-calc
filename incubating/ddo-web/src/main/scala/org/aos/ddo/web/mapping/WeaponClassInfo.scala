package org.aos.ddo.web.mapping

import org.aos.ddo.PhysicalDamageType
import org.aos.ddo.model.item.weapon.WeaponCategory

/**
  * Encapsulates Weapon Category and Damage Information
  *
  * @param category           The category such as Club or Bastard Sword
  * @param physicalDamageType The type of damage such as Bludgeon or Pierce
  *
  */
case class WeaponClassInfo(category: WeaponCategory,
                           physicalDamageType: PhysicalDamageType)