package org.aos.ddo.model.effect

trait Prefix extends Affix

trait HasPrefix extends Prefix {
  val prefix: String
}

trait SecondaryPrefix extends Affix

trait HasSecondaryPrefix extends SecondaryPrefix {
  /**
    * Secondary prefix
    */
  val sPrefix: String
}
