package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * Armor Class, also called AC, represents your chance to be missed by melee attacks - the higher your AC, the less you get hit. This chance is also influenced by the attackers attack bonus.
 */
case class ArmorClass()

object ArmorClass {
  /**
   * Creates the codec for converting ArmorClass from and to JSON.
   */
  implicit val decoder: Decoder[ArmorClass] = deriveDecoder
  implicit val encoder: ObjectEncoder[ArmorClass] = deriveEncoder
}
