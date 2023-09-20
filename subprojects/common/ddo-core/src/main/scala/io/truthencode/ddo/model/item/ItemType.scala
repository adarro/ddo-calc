package io.truthencode.ddo.model.item

import enumeratum.{Enum, EnumEntry}

sealed trait ItemType extends EnumEntry {
  val classifications: List[ItemClassification]
}

object ItemType extends Enum[ItemType] {
  override def values: IndexedSeq[ItemType] = findValues
}
