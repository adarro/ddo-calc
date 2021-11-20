package io.truthencode.ddo.etl.affix

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Created by adarr on 5/23/2017.
  */
sealed trait EquipmentSlot extends EnumEntry

trait WeaponSlot extends EquipmentSlot
trait JewelerySlot extends EquipmentSlot
trait ArmorSlot extends EquipmentSlot
trait ClothingSlot extends EquipmentSlot

object EquipmentSlot extends Enum[EquipmentSlot] {
  case object Belt extends ClothingSlot
  case object Boot extends ClothingSlot
  case object Bracer extends JewelerySlot
  case object Cloak extends ClothingSlot
  case object Glove extends ClothingSlot
  case object Goggle extends JewelerySlot
  case object Head extends ClothingSlot
  case object Necklace extends JewelerySlot
  case object Finger extends JewelerySlot
  case object Trinket extends JewelerySlot
  case object Armor extends ArmorSlot
  // Weapons
  case object Melee extends WeaponSlot
  case object Ranged extends WeaponSlot
  case object RuneArm extends WeaponSlot
  case object Shield extends WeaponSlot
  override def values: immutable.IndexedSeq[EquipmentSlot] = findValues
}
