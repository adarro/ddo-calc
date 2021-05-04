/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
package io.truthencode.ddo

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.enumeration.{BitSupport, BitWise}

import scala.collection.immutable

/** Constrains the places items can be used.
  */
sealed trait WearLocation extends EnumEntry with BitWise {

  private lazy val bitValues = WearLocation.valuesToIndex map { x =>
    x._1 -> toBitMask(x._2)
  }

  override lazy val bitValue: Int = bitValues(this)

}

trait EquipmentSlot extends WearLocation

/**
  * Rings etc
  */
trait Finger extends EquipmentSlot

/**
  * Includes items that can be held or wielded from swords to wands to shields and orbs / rune arms
  */
trait HeldItem extends EquipmentSlot

/** Distinct values for location slots.
  */
object WearLocation extends Enum[WearLocation] with BitSupport {

  /**
    * Headwear such as Helmets
    */
  case object Head extends EquipmentSlot

  /**
    * Necklaces
    */
  case object Neck extends EquipmentSlot

  /**
    * Cloaks etc
    */
  case object Back extends EquipmentSlot

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

  /** OffHand holds shield, Orbs, rune arms etc. and will be unavailable when
    * using two handed weapons or bows.
    */
  case object OffHand extends HeldItem

  case object TwoHand extends HeldItem

  /**
    * Trinkets such as Voice of the Master
    */
  case object Trinket extends EquipmentSlot

  case object HeadDecoration extends EquipmentSlot

  case object BodyDecoration extends EquipmentSlot

  case object Ammo extends EquipmentSlot

  case object Quiver extends EquipmentSlot

  val values: immutable.IndexedSeq[WearLocation] = findValues
  override type T = WearLocation

  override lazy val bitValues: Map[WearLocation, Int] = valuesToIndex.map { x =>
    val wl = x._1
    val v = x._2
    wl -> Math.pow(2.0, v).toInt
  }
}
