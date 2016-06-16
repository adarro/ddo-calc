package org.aos.ddo.enchantment
import enumeratum.{ Enum => SmartEnum, EnumEntry }
import org.aos.ddo.NoDefault

sealed trait Modifier extends EnumEntry
object Modifier extends SmartEnum[Modifier] with NoDefault[Modifier] {
  override val values: IndexedSeq[org.aos.ddo.enchantment.Modifier] = findValues
  case object Minor extends Modifier
  case object Lesser extends Modifier
  case object Greater extends Modifier
  case object Superior extends Modifier
  case object Epic extends Modifier
}
