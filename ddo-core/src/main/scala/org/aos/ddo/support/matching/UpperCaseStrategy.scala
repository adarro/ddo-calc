package org.aos.ddo.support.matching

/**
  * Trait used to match using UpperCase
  */
trait UpperCaseStrategy extends CaseMatchStrategy {
  self: StringMatchBounds =>
  override val caseMatchOption: CaseMatchOption = CaseMatchOption.UpperCase
}
