package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._
import io.truthencode.model.Damage_Type

/**
 *
 * @param dice_modifier Multiplier for the dice
 * @param dice Dice expression in the form of nDm
 * @param extra Additional Damage after the dice
 * @param damage_type
 */
case class Damage(
  dice_modifier: Option[Int],
  dice: Option[String],
  extra: Option[Int],
  damage_type: Option[Damage_Type]
)

object Damage {
  /**
   * Creates the codec for converting Damage from and to JSON.
   */
  implicit val decoder: Decoder[Damage] = deriveDecoder
  implicit val encoder: ObjectEncoder[Damage] = deriveEncoder
}
