/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StoreLocation.scala
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
import io.truthencode.ddo.support.slots.{EquipmentSlot, WearLocation}

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

trait Augment extends ItemEmbed {
  self: AugmentLocation =>
}

trait ColorAugment extends Augment {
  self: ChromaticAugmentLocation =>
  val generalAugment: ChromaticAugmentLocation
}

trait CelestialAugment extends Augment {
  self: CelestialAugmentLocation =>
  val celestialAugment: CelestialAugmentLocation
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
  lazy val Equipment: immutable.Seq[StoreLocation & ItemEquip] =
    StoreLocation.values.collect(fnEquipment)
  lazy val Augments: immutable.Seq[StoreLocation & Augment] =
    StoreLocation.values.collect(fnAugments)

  lazy val ChromaticAugments: immutable.Seq[StoreLocation & ColorAugment] =
    StoreLocation.values.collect(fnChromaticAugments)
  lazy val CelestialAugments: immutable.Seq[StoreLocation & CelestialAugment] =
    StoreLocation.values.collect(fnCelestialAugments)
  lazy val GuildAugments: immutable.Seq[StoreLocation & GuildAugment] =
    StoreLocation.values.collect(fnGuildAugments)
  lazy val Filigrees: immutable.Seq[StoreLocation & Filigree] =
    StoreLocation.values.collect(fnFiligrees)
  override lazy val bitValues: Map[StoreLocation, Int] = valuesToIndex.map { x =>
    val wl = x._1
    val v = x._2
    wl -> Math.pow(2.0, v).toInt
  }
  val values: immutable.IndexedSeq[StoreLocation] =
    findValues ++ generateEquipmentSlot ++ generateAugmentValues
  val fnEquipment: PartialFunction[StoreLocation, StoreLocation & ItemEquip] = {
    case x: ItemEquip =>
      x
  }
  val fnAugments: PartialFunction[StoreLocation, StoreLocation & Augment] = { case x: Augment =>
    x
  }
  val fnChromaticAugments: PartialFunction[StoreLocation, StoreLocation & ColorAugment] = {
    case x: ColorAugment =>
      x
  }
  val fnCelestialAugments: PartialFunction[StoreLocation, StoreLocation & CelestialAugment] = {
    case x: CelestialAugment =>
      x
  }
  val fnGuildAugments: PartialFunction[StoreLocation, StoreLocation & GuildAugment] = {
    case x: GuildAugment =>
      x
  }
  val fnFiligrees: PartialFunction[StoreLocation, StoreLocation & Filigree] = { case x: Filigree =>
    x
  }

  protected def generateAugmentValues: immutable.Seq[ItemEmbed & AugmentLocation] = {
    for aug <- AugmentLocation.values
    yield aug match {
      case x: ChromaticAugmentLocation => ChromaticAugmentLocationSlot(x)
      case x: CelestialAugmentLocation => CelestialAugmentLocationSlot(x)
      case x: GuildAugmentLocation => GuildSlotLocation(x)
      case x: FiligreeLocation => FiligreeSlotLocation(x)
    }
  }

  protected def generateEquipmentSlot: immutable.Seq[EquippedLocation] = {
    for s <- WearLocation.values yield EquippedLocation(s)
  }

  case class ChromaticAugmentLocationSlot(generalAugment: ChromaticAugmentLocation)
    extends ColorAugment with ChromaticAugmentLocation

  case class CelestialAugmentLocationSlot(celestialAugment: CelestialAugmentLocation)
    extends CelestialAugment with CelestialAugmentLocation

  case class GuildSlotLocation(guildAugment: GuildAugmentLocation)
    extends GuildAugment with GuildAugmentLocation

  case class FiligreeSlotLocation(filigreeLocation: FiligreeLocation)
    extends Filigree with FiligreeLocation

  case class EquippedLocation(wearLocation: WearLocation) extends ItemEquip with EquipmentSlot {
    override def displaySource: String = wearLocation.displaySource
  }

  case object Equipped extends Inventory, StoreLocation

  /**
   * Holds crafting ingredients and items such as heart seeds.
   */
  case object IngredientBag extends Bag, StoreLocation

  /**
   * Stores collectables and turn-ins that can be traded for equipment / potions etc
   */
  case object CollectableBag extends Bag, StoreLocation

  /**
   * Stores soul gems which are mainly used for crafting bane items
   */
  case object SoulGemBag extends Bag, StoreLocation

  /**
   * Stores Mount certificates
   * @note
   *   (Might be obsolete as of Update 49)
   */
  case object MountBag extends Bag, StoreLocation

  /**
   * Stores Item augments and filigrees when can be slotted into equipment augment slots and
   * sentient items.
   */
  case object AugmentBag extends Bag, StoreLocation

  case object Quiver extends StoreLocation

  /**
   * Item can be stored in Active Inventory. This should be true by default for most object unless
   * they are some invisible quest item or effect.
   */
  case object ActiveInventory extends Inventory, StoreLocation
}
