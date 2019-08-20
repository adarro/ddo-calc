package org.aos.ddo.support.matching

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Enumeration useful in determining whether to apply a condition to a character or full word.
  */
sealed trait StringMatchOption extends EnumEntry

/**
  * Holds character matching strategies when parsing strings.
  */
object StringMatchOption extends Enum[StringMatchOption] {

  override def values: immutable.IndexedSeq[StringMatchOption] = findValues

  /**
    * Applies to First Character
    */
  case object FirstCharacter extends StringMatchOption

  /**
    * Applies to each character in word.
    */
  case object FullWord extends StringMatchOption

  /**
    * Applies to each character
    */
  case object EachCharacter extends StringMatchOption
}
