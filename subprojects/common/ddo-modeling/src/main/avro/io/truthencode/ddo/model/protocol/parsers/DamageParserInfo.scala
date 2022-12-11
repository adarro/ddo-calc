/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package io.truthencode.ddo.model.protocol.parsers

sealed trait DamageParserInfo

/**
 * A most basic encapsulation of damage information.
 * @param dice_modifier Multiplier for the dice
 * @param dice Dice expression in the form of nDm
 * @param extra Additional Damage after the dice
 * @param damage_type Includes both Physical and Magical Damage types
 */
final case class DamageInfo(dice_modifier: Option[Int], dice: Option[String], extra: Option[Int], damage_type: DamageTrait) extends DamageParserInfo

sealed trait DamageTrait extends DamageParserInfo

object DamageTrait {
  case object Acid extends DamageTrait
  case object Fire extends DamageTrait
  case object Cold extends DamageTrait
  case object Electric extends DamageTrait
  case object Force extends DamageTrait
  case object Sonic extends DamageTrait
  case object Poison extends DamageTrait
  case object Evil extends DamageTrait
  case object Good extends DamageTrait
  case object UnTyped extends DamageTrait
  case object Special extends DamageTrait
  case object Bludgeon extends DamageTrait
  case object Pierce extends DamageTrait
  case object Slash extends DamageTrait
  case object Magic extends DamageTrait
}