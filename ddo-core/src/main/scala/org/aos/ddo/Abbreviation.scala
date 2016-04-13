package org.aos.ddo

/** Allows given object to have / use an abbreviation
  */
trait Abbreviation {
  /** The short form of the word
    */
  val abbr: String
  /** Expands the abbr to its full value
    */
  def toFullWord(): String
}
