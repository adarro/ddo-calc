package org.aos.ddo.model.compendium.monster

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Created by adarr on 3/25/2017.
  */
sealed trait SubRace extends EnumEntry

object SubRace extends Enum[SubRace] {
  override def values: immutable.IndexedSeq[SubRace] = findValues
}
