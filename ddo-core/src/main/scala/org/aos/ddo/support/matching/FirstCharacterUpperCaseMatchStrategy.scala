package org.aos.ddo.support.matching

/**
  * Strategy based on the Case of the First Character being Uppercase
  */
trait FirstCharacterUpperCaseMatchStrategy extends UpperCaseStrategy with StringMatch with FirstCharacter