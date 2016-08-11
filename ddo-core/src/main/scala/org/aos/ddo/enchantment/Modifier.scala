package org.aos.ddo.enchantment

import enumeratum.{EnumEntry, Enum => SmartEnum}
import org.aos.ddo.NoDefault
import org.aos.ddo.support.IndexedEnum

sealed trait Modifier extends EnumEntry

object Modifier extends IndexedEnum[Modifier] with NoDefault[Modifier] {
  override val values = findValues
  case object Minor extends Modifier
  case object Lesser extends Modifier
  case object Greater extends Modifier
  case object Superior extends Modifier
  case object Epic extends Modifier
}
