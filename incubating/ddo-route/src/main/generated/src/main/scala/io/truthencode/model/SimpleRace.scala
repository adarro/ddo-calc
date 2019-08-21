package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * A basic race type, which has one or more sub-races. For example, Elf includes Drow, Elf, Half-elf and Morninglords. This is used to see benificial effects as well as malignant ones.
 * @param id ID and name of race
 */
case class SimpleRace(id: Option[String])

object SimpleRace {
  /**
   * Creates the codec for converting SimpleRace from and to JSON.
   */
  implicit val decoder: Decoder[SimpleRace] = deriveDecoder
  implicit val encoder: ObjectEncoder[SimpleRace] = deriveEncoder
}
