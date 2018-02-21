package org.aos.ddo.model.misc

import enumeratum.{Enum, EnumEntry}

/**
  * Basic enumeration used for testing / verifying behavior
  */
sealed trait Simple extends EnumEntry

 sealed trait values extends EnumEntry

object Simple extends Enum[Simple] {

  object Basic extends values with Simple

  override def values:  scala.collection.immutable.IndexedSeq[org.aos.ddo.model.misc.Simple]= findValues
}