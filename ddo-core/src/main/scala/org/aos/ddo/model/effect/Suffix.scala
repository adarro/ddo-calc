package org.aos.ddo.model.effect

trait Suffix extends Effect with Affix

trait HasSuffix extends Suffix {
  val suffix: String
}
