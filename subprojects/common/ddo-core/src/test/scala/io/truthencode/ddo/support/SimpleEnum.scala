package io.truthencode.ddo.support

import enumeratum.{Enum, EnumEntry}
import scala.collection.immutable

sealed trait SimpleEnum extends EnumEntry

sealed trait Other extends SimpleEnum

object SimpleEnum extends Enum[SimpleEnum] {
//    class Derived extends SimpleEnum

  override def values: immutable.IndexedSeq[SimpleEnum] = findValues

  case object E1 extends Other

  case object E2 extends Other
}
