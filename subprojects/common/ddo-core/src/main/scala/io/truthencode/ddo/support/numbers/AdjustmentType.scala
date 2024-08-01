package io.truthencode.ddo.support.numbers

import enumeratum.{Enum, EnumEntry}

sealed trait AdjustmentType extends EnumEntry

/**
 * Flags the adjustment to annotate the type of change affected.
 */
object AdjustmentType extends Enum[AdjustmentType] {
  override def values: IndexedSeq[AdjustmentType] = findValues

  /**
   * Changes (sets) the base value
   */
  case object Base extends AdjustmentType

  /**
   * Adjusts the value by a given number such as Magical Sheltering + 2
   */
  case object Value extends AdjustmentType

  /**
   * Adjusts the value by a percentage such as Max Hit Points + 5%
   */
  case object Percent extends AdjustmentType

}
