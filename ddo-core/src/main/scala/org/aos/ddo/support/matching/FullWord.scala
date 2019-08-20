package org.aos.ddo.support.matching

/**
  * Indicates match or manipulate applies to full word.
  */
trait FullWord extends StringMatchBounds {
  override val stringMatchOption: StringMatchOption = StringMatchOption.FullWord
}
