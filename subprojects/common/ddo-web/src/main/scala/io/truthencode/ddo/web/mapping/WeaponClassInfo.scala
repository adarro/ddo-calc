package io.truthencode.ddo.web.mapping

import io.truthencode.ddo.model.item.weapon.WeaponCategory
import io.truthencode.ddo.model.meta.PhysicalDamageType

/**
 * Encapsulates Weapon Category and Damage Information
 *
 * @param category
 *   The category such as Club or Bastard Sword
 * @param physicalDamageType
 *   The type of damage such as Bludgeon or Pierce
 */
case class WeaponClassInfo(category: WeaponCategory, physicalDamageType: PhysicalDamageType)
