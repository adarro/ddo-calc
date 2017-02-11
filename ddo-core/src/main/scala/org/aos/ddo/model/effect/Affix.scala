package org.aos.ddo.model.effect

/**
  * Flag used to denote a modifier to the base object.
  *
  * @example Fire Ward can be prefixed with 'Greater'.
  */
trait Affix

case class Affixes(prefix: String, sPrefix: String, suffix: String) extends Prefix with SecondaryPrefix with Suffix
