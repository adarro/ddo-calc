package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 *
 * @param value
 */
case class Damage_Type(value: Option[String])

object Damage_Type {
  /**
   * Creates the codec for converting Damage_Type from and to JSON.
   */
  implicit val decoder: Decoder[Damage_Type] = deriveDecoder
  implicit val encoder: ObjectEncoder[Damage_Type] = deriveEncoder
}
