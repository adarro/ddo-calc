package org.aos.ddo
/** Contains Damage information based on Dice
  *
  * @example
  * Given 3[1d4] + 2
  * weaponModifier : 3
  * dice : 1d4
  * extra : 2
  */
trait DamageInfo {
  /** Multiplies the dice by the specified number
    *
    * @example weaponModifier 3[1d4] = 1[3d4]
    */
  val weaponModifier: Int
  /** Number of sides of the dice
    *
    * 6 represents a 6 sided die
    */
  val dice: String
  /** Adds or subtracts to the value of the dice
    *
    * 1d8 + 3, 3 is the extra
    */
  val extra: Int
  /** List of damage types such as Slash, Pierce, Magic, Good etc
    */
  val damageType: List[String]
}
