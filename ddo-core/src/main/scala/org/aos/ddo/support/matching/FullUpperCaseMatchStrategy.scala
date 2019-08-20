package org.aos.ddo.support.matching

/**
  * Strategy based on Full word being or manipulated into full UPPERCASE
  */
trait FullUpperCaseMatchStrategy extends UpperCaseStrategy with StringMatch with FullWord
