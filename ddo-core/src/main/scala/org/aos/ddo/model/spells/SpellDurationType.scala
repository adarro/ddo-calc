package org.aos.ddo.model.spells

import java.time.Duration

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Represents the duration of a spell
  */
sealed trait SpellDurationType

/**
  * Effect occurs immediately with no lasted or repeated effect.
  */
trait Instant extends SpellDurationType

/**
  * Effect lasts until an external event such as resting at a shrine or quaffing a remove curse removes it.
  */
trait Permanent extends SpellDurationType

/**
  * Effect lasts until the specified time.
  */
trait Expires extends SpellDurationType {
  val duration:Duration
}

case class ExpiringDuration(duration: Duration) extends Expires
object SpellDurationType {
  def apply(duration:Duration): SpellDurationType = ExpiringDuration(duration)

}

