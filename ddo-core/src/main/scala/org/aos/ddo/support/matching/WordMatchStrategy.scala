package org.aos.ddo.support.matching

/**
  * Base trait used for determining Matches by full words.
  */
trait WordMatchStrategy {
  self: CaseMatchStrategy =>
  val stringMatchOption: StringMatchOption
}