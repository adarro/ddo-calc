package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * Ability Score, one of six Base stats that determines, enhances or decreases skills and abilities, modifications to attack and damage rolls etc.
 * @param id key for Ability
 * @param abbreviation Abbreviation for stat
 */
case class Ability(
  id: Option[String],
  abbreviation: Option[String]
)

object Ability {
  /**
   * Creates the codec for converting Ability from and to JSON.
   */
  implicit val decoder: Decoder[Ability] = deriveDecoder
  implicit val encoder: ObjectEncoder[Ability] = deriveEncoder
}
