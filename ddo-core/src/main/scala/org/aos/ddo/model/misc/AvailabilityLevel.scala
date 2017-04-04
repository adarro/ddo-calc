package org.aos.ddo.model.misc

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.Abbreviation

import scala.collection.immutable.IndexedSeq

/**
  * Created by adarr on 1/28/2017.
  */
sealed trait AvailabilityLevel extends EnumEntry

object AvailabilityLevel extends Enum[AvailabilityLevel] {
  case object FreeToPlay extends AvailabilityLevel with Abbreviation {
    /** The short form of the word
      */
    override val abbr: String = "F2P"

    /** Expands the abbr to its full value
      */
    override def toFullWord: String = this.toString
  }
  case object Favor extends AvailabilityLevel
  case object Premium extends AvailabilityLevel
  case object VIP extends AvailabilityLevel
  case object Iconic extends AvailabilityLevel
  override def values: IndexedSeq[AvailabilityLevel] = findValues
}