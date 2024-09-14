package io.truthencode.ddo.model.item

import io.truthencode.ddo.model.race.Race

trait CommonItemProperties {
  self: Item =>

  /**
   * requiredRaces - list of races the player or pet MUST be to naturally equip the item unless they
   * can bypass with a high enough UMD.
   */
  val requiredRaces: List[Race] = Nil

  /**
   * A list of races that a player MUST be to equip the item.
   */
  val absoluteRequiredRaces: List[Race] = Nil

  /**
   * A list of races the player MUST NOT be in order to wear.
   * @example
   *   Warforged can NOT wear plate armor.
   */
  val absoluteRestrictedRaces: List[Race] = Nil

  /**
   * Represents the Minimum level a player must be to equip this item.
   */
  val minimumLevel: Int

  /**
   * Represents the absolute minimum level of the item. This is usually due to augments or crafting
   * or other alterations to a base item.
   */
  val absoluteMinimumLevel: Option[Int]

}
