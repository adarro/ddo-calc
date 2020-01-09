package org.aos.ddo.model.classes

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.SearchPrefix

import scala.collection.immutable

sealed trait EpicCharacterClass extends CharacterClass with EnumEntry

object EpicCharacterClass extends Enum[EpicCharacterClass] {
  private val generateLevels = {
    (21 to 30).map { x => {
      val y = x - 20
      epicLevel(y)
    }
    }
  }

  override def values: immutable.IndexedSeq[EpicCharacterClass] = generateLevels
}

case class epicLevel(level: Int) extends EpicCharacterClass {
  override def entryName: String = s"Level$level"
}