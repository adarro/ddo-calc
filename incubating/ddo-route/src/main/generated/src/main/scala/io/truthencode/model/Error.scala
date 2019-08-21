package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 *
 * @param code
 * @param message
 * @param fields
 */
case class Error(
  code: Option[Int],
  message: Option[String],
  fields: Option[String]
)

object Error {
  /**
   * Creates the codec for converting Error from and to JSON.
   */
  implicit val decoder: Decoder[Error] = deriveDecoder
  implicit val encoder: ObjectEncoder[Error] = deriveEncoder
}
