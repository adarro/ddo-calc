package org.aos.ddo.support.matching

/**
  * Match strategy based on full word being or manipulated into lowercase.
  */
trait FullLowerCaseMatchStrategy extends LowerCaseStrategy with StringMatch with FullWord
