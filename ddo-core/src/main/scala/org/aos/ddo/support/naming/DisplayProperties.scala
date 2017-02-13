package org.aos.ddo.support.naming

/**
  * Used to display text in an optionally more friendly way.
  */
trait DisplayProperties {
  /**
    * Source string to Manipulate
    * @return
    */
  def displaySource : String

  def displayText : String = displaySource
}
