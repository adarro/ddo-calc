package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * A playable character class.
 * @param name name and identifier of the class.
 * @param skillPointModifier Base number of skill points gained for each level.  This number is multiplied by 4 for the first level.
 */
case class PlayerClass(
  name: Option[String],
  skillPointModifier: Option[Int]
)

object PlayerClass {
  /**
   * Creates the codec for converting PlayerClass from and to JSON.
   */
  implicit val decoder: Decoder[PlayerClass] = deriveDecoder
  implicit val encoder: ObjectEncoder[PlayerClass] = deriveEncoder
}
