package org.aos.ddo.enumeration

import enumeratum.{Enum, EnumEntry}

/**
  * Created by adarr on 1/23/2017.
  */
trait Companionable {
  self: EnumEntry =>
  type E // <: Enum[_ <: EnumEntry]

  def companion: E

}
