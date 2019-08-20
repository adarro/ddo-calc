package org.aos.ddo.support.matching

/**
  * Matches ignoring case or preserves case.
  */
trait FullIgnoreCaseMatchStrategy extends IgnoreCaseStrategy with StringMatch with FullWord
