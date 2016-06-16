package org.aos.ddo.effect

trait Suffix extends Effects with Affix

trait HasSuffix extends Suffix {
  val suffix: String
}
