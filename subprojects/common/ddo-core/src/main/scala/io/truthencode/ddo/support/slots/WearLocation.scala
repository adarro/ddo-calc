/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WearLocation.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
  case object Head extends EquipmentSlot, WearLocation {
    override def displaySource: String = entryName
  }

  /**
   * Necklaces
   */
  case object Neck extends EquipmentSlot, WearLocation

  /**
   * Cloaks etc
   */
  case object Back extends EquipmentSlot, WearLocation {
    override def displaySource: String = "Cloak"
  }

  /**
   * Includes Armbands / bracers etc
   */
  case object Wrist extends EquipmentSlot, WearLocation

  /**
   * One of two Ring locations
   */
  case object FirstFinger extends Finger, WearLocation

  /**
   * One of two ring locations
   */
  case object SecondFinger extends Finger, WearLocation

  /**
   * Armor / cloth robes for wizards etc
   */
  case object Body extends EquipmentSlot, WearLocation

  /**
   * Footwear such as boots etc
   */
  case object Feet extends EquipmentSlot, WearLocation

  /**
   * Belt items
   */
  case object Belt extends EquipmentSlot, WearLocation

  /**
   * Eyewear such as goggles / glasses
   */
  case object Goggles extends EquipmentSlot, WearLocation

  /**
   * Gloves and other over the hand items
   */
  case object Gloves extends EquipmentSlot, WearLocation

  case object MainHand extends HeldItem, WearLocation

  /**
   * OffHand holds shield, Orbs, rune arms etc. and will be unavailable when using two-handed
   * weapons or bows.
   */
  case object OffHand extends HeldItem, WearLocation

  case object TwoHand extends HeldItem, WearLocation

  /**
   * Trinkets such as Voice of the Master
   */
  case object Trinket extends EquipmentSlot, WearLocation

  case object HeadDecoration extends EquipmentSlot, Cosmetic, WearLocation

  case object BodyDecoration extends EquipmentSlot, Cosmetic, WearLocation

  case object Ammo extends EquipmentSlot, WearLocation

  case object Quiver extends EquipmentSlot, WearLocation
}
