package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * The dodge mechanic works as a miss chance - a simple percentile chance to completely avoid physical attacks. You will see a \"Dodge\" hover over your character anytime it helps you dodge an attack.
 */
case class Dodge()

object Dodge {
  /**
   * Creates the codec for converting Dodge from and to JSON.
   */
  implicit val decoder: Decoder[Dodge] = deriveDecoder
  implicit val encoder: ObjectEncoder[Dodge] = deriveEncoder
}
