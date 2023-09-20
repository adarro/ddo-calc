package io.truthencode.ddo.support.slots

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.enumeration.{BitSupport, BitWise}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

trait EquipmentSlot extends WearLocation {
  override def displaySource: String = entryName
}

/**
 * Constrains the places items can be used.
 */
sealed trait WearLocation extends EnumEntry with BitWise with DisplayName with FriendlyDisplay {

  override lazy val bitValue: Int = bitValues(this)
  private lazy val bitValues = WearLocation.valuesToIndex.map { x =>
    x._1 -> toBitMask(x._2)
  }

  override protected def nameSource: String = entryName.splitByCase
//    abstract override def displaySource: String = super.displaySource
}

/**
 * Distinct values for location slots.
 */
object WearLocation extends Enum[WearLocation] with BitSupport {

  override type T = WearLocation
  override lazy val bitValues: Map[WearLocation, Int] = valuesToIndex.map { x =>
    val wl = x._1
    val v = x._2
    wl -> Math.pow(2.0, v).toInt
  }
  val values: immutable.IndexedSeq[WearLocation] = findValues

  /**
   * Headwear such as Helmets
   */
  case object Head extends EquipmentSlot {
    override def displaySource: String = entryName
  }

  /**
   * Necklaces
   */
  case object Neck extends EquipmentSlot

  /**
   * Cloaks etc
   */
  case object Back extends EquipmentSlot {
    override def displaySource: String = "Cloak"
  }

  /**
   * Includes Armbands / bracers etc
   */
  case object Wrist extends EquipmentSlot

  /**
   * One of two Ring locations
   */
  case object FirstFinger extends Finger

  /**
   * One of two ring locations
   */
  case object SecondFinger extends Finger

  /**
   * Armor / cloth robes for wizards etc
   */
  case object Body extends EquipmentSlot

  /**
   * Footwear such as boots etc
   */
  case object Feet extends EquipmentSlot

  /**
   * Belt items
   */
  case object Belt extends EquipmentSlot

  /**
   * Eyewear such as goggles / glasses
   */
  case object Goggles extends EquipmentSlot

  /**
   * Gloves and other over the hand items
   */
  case object Gloves extends EquipmentSlot

  case object MainHand extends HeldItem

  /**
   * OffHand holds shield, Orbs, rune arms etc. and will be unavailable when using two handed
   * weapons or bows.
   */
  case object OffHand extends HeldItem

  case object TwoHand extends HeldItem

  /**
   * Trinkets such as Voice of the Master
   */
  case object Trinket extends EquipmentSlot

  case object HeadDecoration extends EquipmentSlot with Cosmetic

  case object BodyDecoration extends EquipmentSlot with Cosmetic

  case object Ammo extends EquipmentSlot

  case object Quiver extends EquipmentSlot
}
