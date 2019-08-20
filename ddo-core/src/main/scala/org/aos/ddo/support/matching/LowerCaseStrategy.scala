package org.aos.ddo.support.matching

/**
  * Strategies based on LowerCase Matches
  */
trait LowerCaseStrategy extends CaseMatchStrategy {
  self: StringMatchBounds =>
  override val caseMatchOption: CaseMatchOption = CaseMatchOption.LowerCase
}
