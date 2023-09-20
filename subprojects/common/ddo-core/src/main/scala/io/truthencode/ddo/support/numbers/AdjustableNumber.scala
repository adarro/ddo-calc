package io.truthencode.ddo.support.numbers

import io.truthencode.ddo.{NonStacking, StacksWithAny, StacksWithUnique}

trait AdjustableNumber {

  val baseValue: Int

  val basePercent: Int

  def adjust(adjustment: Adjustment*): (Int, Int) = {

    val bases = adjustment.filter { _.kind == AdjustmentType.Base }
    val amt = adjustment.filter { _.kind == AdjustmentType.Value }
    val pct = adjustment.filter { _.kind == AdjustmentType.Percent }
// TODO: Implement
    val r: (Int, Int) = ???
    r
  }
}

object AdjustableNumber {
  val fnStackAny: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[StacksWithAny] =>
      x
  }

  /**
   * Stacks with others but not same. i.e. shield bonus stacks with any but other shield bonuses
   */
  val fnStackNone: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[NonStacking] =>
      x
  }

  /**
   * Stacks with others but not same. i.e. shield bonus stacks with any but other shield bonuses
   */
  val fnStackDifferent: PartialFunction[Adjustment, Adjustment] = {
    case x if x.bonusType.isInstanceOf[StacksWithUnique] =>
      x
  }
  def calculateSingleType(adjustment: Adjustment*): Unit = {
    val anyStacks = adjustment.collect(fnStackAny)
    val nonStacking = adjustment.collect(fnStackNone)
    val diffStack = adjustment.collect(fnStackDifferent)
    val fl = anyStacks.foldLeft(0)((x, y) => x + y.value)

  }
}
