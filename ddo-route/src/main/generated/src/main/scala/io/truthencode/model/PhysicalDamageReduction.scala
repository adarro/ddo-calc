package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._

/**
 * Physical Resistance Rating (abbreviated PRR) is a form of damage mitigation was that introduced with Update 14 and updated in update 23. It provides a percentage reduction in physical damage (bludgeoning, slashing, and piercing), and is granted through multiple sources (items, feats, enhancements) and stacks with itself.
 */
case class PhysicalDamageReduction()

object PhysicalDamageReduction {
  /**
   * Creates the codec for converting PhysicalDamageReduction from and to JSON.
   */
  implicit val decoder: Decoder[PhysicalDamageReduction] = deriveDecoder
  implicit val encoder: ObjectEncoder[PhysicalDamageReduction] = deriveEncoder
}
