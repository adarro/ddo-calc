package org.aos.ddo.support.matching

/**
  * Strategies ignoring or preserving case.
  */
trait IgnoreCaseStrategy extends CaseMatchStrategy {
  self: StringMatchBounds =>
  override val caseMatchOption: CaseMatchOption = CaseMatchOption.IgnoreCase
}
