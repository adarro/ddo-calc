package org.aos.ddo.effect

/**
  * Flag used to denote a modifier to the base object.
  *
  * @example Fire Ward can be prefixed with 'Greater'.
  */
trait Affix

case class Affixes(val prefix: String, val sPrefix: String, val suffix: String) extends Prefix with SecondaryPrefix with Suffix
