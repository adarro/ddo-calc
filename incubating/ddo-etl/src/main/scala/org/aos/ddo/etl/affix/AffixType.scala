package io.truthencode.ddo.etl.affix

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Created by adarr on 5/23/2017.
  */
sealed trait AffixType extends EnumEntry

object AffixType extends Enum[AffixType] {
  case object Prefix extends AffixType
  case object Suffix extends AffixType
  case object Extra extends AffixType
  case object Named extends AffixType
  override def values: immutable.IndexedSeq[AffixType] = findValues
}
