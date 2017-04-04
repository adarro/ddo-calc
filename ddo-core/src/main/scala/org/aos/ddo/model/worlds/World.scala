package org.aos.ddo.model.worlds
import enumeratum.{Enum, EnumEntry}

/**
  * Represents the playable source worlds.
  */
sealed trait World extends EnumEntry

object World extends Enum[World] {
  case object Eberron extends World
  case object ForgottenRealms extends World
  override def values= findValues
}
