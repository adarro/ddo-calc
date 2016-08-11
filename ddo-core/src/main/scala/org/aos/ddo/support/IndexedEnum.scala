package org.aos.ddo.support

import enumeratum.{Enum, EnumEntry}

/**
  * Created by adarr on 8/11/2016.
  */
trait IndexedEnum[T <: EnumEntry] extends Enum[T] {

  lazy val indexed: IndexedSeq[T] = values.toIndexedSeq

}
