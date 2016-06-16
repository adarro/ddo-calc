package org.aos.ddo.effect

import org.aos.ddo.DamageInfo

/**
  * Represents the chance and amount of damage
  *
  * @note Example below is for the Elemental Guards
  * Minor = 50% chance of 1d4 damage
  * Lesser = 1d4 damage, base price modifier: +1
  * Roman Numeral suffix = 1d4 damage per level (Guard V = 5d4 damage)
  * No prefix or Roman Numerals (regular) = 1d8 damage, base price modifier: +2
  */
trait Magnitude {
  /**
    * Percent chance to occur
    */
  val chance: Int
  /**
    * Damage Dice
    */
  val damage: DamageInfo
  /**
    * Base Price Modifier
    */
  val bpm: Int
}
