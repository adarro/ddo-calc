package org.aos.ddo.support.matching

/**
  * Denotes match or manipulation applies to first character of string only.
  */
trait FirstCharacter extends StringMatchBounds {
  override val stringMatchOption: StringMatchOption = StringMatchOption.FirstCharacter
}