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

/**
 * Constrains the places items can be used.
 */
sealed trait StoreLocation extends EnumEntry with BitWise {

  override lazy val bitValue: Int = bitValues(this)
  private lazy val bitValues = StoreLocation.valuesToIndex.map { x =>
    x._1 -> toBitMask(x._2)
  }

}

/**
 * Active inventory
 */
trait Inventory extends StoreLocation

/**
 * storage location not on character such as a shared, character or TR bank
 */
trait Bank extends StoreLocation

/**
 * can be sotred in a bag or container in inventory such as an Ingredient bag or Cookie Jar
 */
trait Bag extends StoreLocation

/**
 * can be placed into an item such. Supports primarily augments and filigrees.
 */
trait ItemEmbed extends StoreLocation {
  self: AugmentLocation =>
}

trait GuildAugment extends ItemEmbed {
  self: GuildAugmentLocation =>
  val guildAugment: GuildAugmentLocation
}

trait Filigree extends ItemEmbed {
  self: FiligreeLocation =>
  val filigreeLocation: FiligreeLocation
}

trait ColorAugment extends ItemEmbed {
  self: GeneralAugmentLocation =>
  val generalAugment: GeneralAugmentLocation
}

/**
 * Object can be slotted onto character, such as a sword or helmet. Items with this value should
 * further be constrained with corresponding WearLocation.
 */
trait ItemEquip extends StoreLocation {
  self: WearLocation =>
  val wearLocation: WearLocation

}

/**
 * Distinct values for location slots.
 */
object StoreLocation extends Enum[StoreLocation] with BitSupport {

  override type T = StoreLocation

  /**
   * Object can be slotted onto character, such as a sword or helmet. Items with this value should
   * further be constrained with corresponding WearLocation.
   */
  lazy val Equipment: immutable.Seq[StoreLocation with ItemEquip] =
    StoreLocation.values.collect(fnEquipment)
  lazy val Augments: immutable.Seq[StoreLocation with ColorAugment] =
    StoreLocation.values.collect(fnAugments)
  lazy val GuildAugments: immutable.Seq[StoreLocation with GuildAugment] =
    StoreLocation.values.collect(fnGuildAugments)
  lazy val Filigrees: immutable.Seq[StoreLocation with Filigree] =
    StoreLocation.values.collect(fnFiligrees)
  override lazy val bitValues: Map[StoreLocation, Int] = valuesToIndex.map { x =>
    val wl = x._1
    val v = x._2
    wl -> Math.pow(2.0, v).toInt
  }
  val values: immutable.IndexedSeq[StoreLocation] =
    findValues ++ generateEquipmentSlot ++ generateAugmentValues
  val fnEquipment: PartialFunction[StoreLocation, StoreLocation with ItemEquip] = {
    case x: ItemEquip =>
      x
  }
  val fnAugments: PartialFunction[StoreLocation, StoreLocation with ColorAugment] = {
    case x: ColorAugment =>
      x
  }
  val fnGuildAugments: PartialFunction[StoreLocation, StoreLocation with GuildAugment] = {
    case x: GuildAugment =>
      x
  }
  val fnFiligrees: PartialFunction[StoreLocation, StoreLocation with Filigree] = {
    case x: Filigree =>
      x
  }

  protected def generateAugmentValues: immutable.Seq[ItemEmbed with AugmentLocation] = {
    for {
      aug <- AugmentLocation.values
    } yield aug match {
      case x: GeneralAugmentLocation => AugmentLocationSlot(x)
      case x: GuildAugmentLocation => GuildSlotLocation(x)
      case x: FiligreeLocation => FiligreeSlotLocation(x)
    }
  }

  protected def generateEquipmentSlot: immutable.Seq[EquippedLocation] = {
    for { s <- WearLocation.values } yield EquippedLocation(s)
  }

  case class AugmentLocationSlot(generalAugment: GeneralAugmentLocation)
    extends ColorAugment with GeneralAugmentLocation

  case class GuildSlotLocation(guildAugment: GuildAugmentLocation)
    extends GuildAugment with GuildAugmentLocation

  case class FiligreeSlotLocation(filigreeLocation: FiligreeLocation)
    extends Filigree with FiligreeLocation

  case class EquippedLocation(wearLocation: WearLocation) extends ItemEquip with EquipmentSlot

  case object Equipped extends Inventory

  /**
   * Holds crafting ingredients and items such as heart seeds.
   */
  case object IngredientBag extends Bag

  /**
   * Stores collectables and turn-ins that can be traded for equipment / potions etc
   */
  case object CollectableBag extends Bag

  /**
   * Stores soul gems which are mainly used for crafting bane items
   */
  case object SoulGemBag extends Bag

  /**
   * Stores Mount certificates
   * @note
   *   (My be obsolete as of Update 49)
   */
  case object MountBag extends Bag

  /**
   * Stores Item augments and filigrees when can be slotted into equipment augment slots and
   * sentient items.
   */
  case object AugmentBag extends Bag

  case object Quiver extends StoreLocation

  /**
   * Item can be stored in Active Inventory. This should be true by default for most object unless
   * they are some invisible quest item or effect.
   */
  case object ActiveInventory extends Inventory
}
