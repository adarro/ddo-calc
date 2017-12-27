package org.aos.ddo.etl.affix

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Created by adarr on 5/23/2017.
  */
sealed trait Modifier extends EnumEntry

object Modifier extends Enum[Modifier] {
  case object Insightful extends Modifier
  override def values: immutable.IndexedSeq[Modifier] = findValues
}
