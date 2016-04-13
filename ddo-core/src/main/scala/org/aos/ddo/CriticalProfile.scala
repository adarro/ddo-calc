package org.aos.ddo
/** Holds range information used to determine when critical hits occur
  * and the bonus to apply.
  */
trait CriticalProfile {
  /** Lower bound
    */
  val min: Int
  /** Upper bound
    */
  val max: Int
  /** Bonus multiplier when a roll is between given range (inclusive)
    */
  val multiplier: Int
  /** Creates a range using the given min / max (inclusive)
    */
  val toRange = Range(min, max).inclusive
  /** Determines if a given roll would be considered 'Critical'
    */
  def isCritical(roll: Int): Boolean = toRange.contains(roll)
}
