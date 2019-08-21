package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 *
 * @param min Lower bound for critical multiplier
 * @param max Upper bound for critical multiplier
 * @param multiplier Number to multiply the damage roll by
 */
case class Critical_Threat_Range(
  min: Option[Int],
  max: Option[Int],
  multiplier: Option[Int]
)

object Critical_Threat_Range {
  /**
   * Creates the codec for converting Critical_Threat_Range from and to JSON.
   */
  implicit val decoder: Decoder[Critical_Threat_Range] = deriveDecoder
  implicit val encoder: ObjectEncoder[Critical_Threat_Range] = deriveEncoder
}
