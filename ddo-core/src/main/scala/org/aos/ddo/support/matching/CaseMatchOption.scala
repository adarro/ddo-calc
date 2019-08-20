package org.aos.ddo.support.matching

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Enumeration useful in determining case comparison and or manipulation.
  */
sealed trait CaseMatchOption extends EnumEntry

/**
  * Holds strategies for case (in)sensitive matching against strings.
  */
object CaseMatchOption extends Enum[CaseMatchOption] {

  override def values: immutable.IndexedSeq[CaseMatchOption] = findValues

  /**
    * Compares or manipulates based on UpperCase
    */
  case object UpperCase extends CaseMatchOption
  /**
    * Compares or manipulates based on LowerCase
    */
  case object LowerCase extends CaseMatchOption
  /**
    * Compares or manipulates ignoring or preserving case
    */
  case object IgnoreCase extends CaseMatchOption

}


