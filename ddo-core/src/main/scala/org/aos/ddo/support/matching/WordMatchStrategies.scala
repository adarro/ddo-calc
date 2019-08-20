package org.aos.ddo.support.matching

/**
  * Encapsulates word matching strategies for parsing text and acronyms
  */
object WordMatchStrategies {

  /**
    * Matches whole words with lowercase
    */
  case object FullLowerCaseWordStrategy extends WordMatchStrategy with FullLowerCaseMatchStrategy

  /**
    * Matches whole words with uppercase
    */
  case object FullUpperCaseWordStrategy extends WordMatchStrategy with FullUpperCaseMatchStrategy

  /**
    * Matches whole words using TitleCase
    */
  case object TitleCaseWordStrategy extends WordMatchStrategy with FullUpperCaseMatchStrategy

  /**
    * Matches whole words ignoring and implicitly preserving case
    */
  case object IgnoreCaseWordStrategy extends WordMatchStrategy with FullIgnoreCaseMatchStrategy
}
