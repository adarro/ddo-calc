package io.truthencode.ddo.support.numbers

import io.truthencode.ddo.enhancement.BonusType

/**
 * Adjustment parameters for attempting to change the value of a given Adjustable Number.
 * @param value
 *   value to add / subtract or set
 * @param kind
 *   determines whether this operation will augment by value, percent or replace existing values
 * @param bonusType
 *   used to determine stacking rules
 * @param sourceId
 *   provides context for some stacking rules in addition to the ability to lookup / add remove
 *   stacks
 */
case class Adjustment(
  value: Int,
  kind: AdjustmentType,
  bonusType: BonusType,
  sourceId: Option[String] = None)
