package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._
import io.truthencode.model.Ability

/**
 * Specific skills such as haggling which are affected by a related ability score
 * @param name Name of the skill
 * @param skillType Determines whether a skill is active or passive
 * @param Ability
 */
case class Skill(
  name: Option[String],
  skillType: Option[String],
  Ability: Option[Ability]
)

object Skill {
  /**
   * Creates the codec for converting Skill from and to JSON.
   */
  implicit val decoder: Decoder[Skill] = deriveDecoder
  implicit val encoder: ObjectEncoder[Skill] = deriveEncoder
}
