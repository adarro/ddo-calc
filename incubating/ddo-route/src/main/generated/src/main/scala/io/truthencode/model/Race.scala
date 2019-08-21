package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._
import io.truthencode.model.SimpleRace
import scala.collection.immutable.Seq

/**
 *
 * @param name Name of race (Used for general display)
 * @param id ID used as key for the Race
 * @param beneficialRaceType base simple race type. I.e a Morninglord is a Sun Elf, which is an elf for purposes of things that can effect or are affected by elves. Half elves are both human and elves for this purpose
 * @param detrimentalRaceType
 * @param availability Determines the subscription level required to unlock or use the race.
 */
case class Race(
  name: Option[String],
  id: Option[String],
  beneficialRaceType: Option[Seq[SimpleRace]],
  detrimentalRaceType: Option[SimpleRace],
  availability: Option[String]
)

object Race {
  /**
   * Creates the codec for converting Race from and to JSON.
   */
  implicit val decoder: Decoder[Race] = deriveDecoder
  implicit val encoder: ObjectEncoder[Race] = deriveEncoder
}
