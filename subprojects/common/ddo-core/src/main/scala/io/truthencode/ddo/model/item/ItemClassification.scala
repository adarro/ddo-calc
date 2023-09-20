package io.truthencode.ddo.model.item

import enumeratum.{Enum, EnumEntry}

/**
 * Classifies an item by types
 *
 * Can be used to denote concepts such as wearable, craftable etc.
 */
sealed trait ItemClassification extends EnumEntry

object ItemClassification extends Enum[ItemClassification] {
  override def values: IndexedSeq[ItemClassification] = findValues

  /**
   * Includes potions, cakes, food, one-use items etc
   */
  case object Consumable extends ItemClassification

  /**
   * Item can be equipped / worn.
   */
  case object Equipment extends ItemClassification

  /**
   * Ingredients include many items that can be used in crafting
   */
  case object Ingredient extends ItemClassification

  /**
   * Physical part of a spell component
   */
  case object MaterialComponent extends ItemClassification

}
