package org.aos.ddo.model.feats

/**
  * Used to determine if a given feat can be taken multiple times
  * Feats that do not posses this trait can only be taken once.
  * Feats which have this trait but do not specify a value [Option.None] are considered unlimited.
  * i.e. Toughness can be taken as many times as you wish.
  */
trait StackableFeat {
  /**
    * Determines the maximum times this feat may be taken.
    */
  val maxCount : Option[Int] = None
  /**
    * Convenience method to determine if a value is defined.
    */
  lazy val isLimited : Boolean = maxCount.nonEmpty
}
