package io.truthencode.model

import io.circe._
import io.finch.circe._
import io.circe.generic.semiauto._
import io.circe.java8.time._
import io.truthencode.server._
import io.truthencode.model.Critical_Threat_Range
import io.truthencode.model.Damage
import io.truthencode.model.Damage_Type
import scala.collection.immutable.Seq

/**
 *
 * @param name Name of the Item
 * @param proficiency_class General Level of Martial training required to wield
 * @param image Screen shot or image URI
 * @param damage
 * @param damage_type
 * @param critical_threat_range
 * @param weapon_category The type of weapon such as long sword Axe etc
 * @param required_race Race required to use item without a UMD Check
 * @param abs_required_race Race absolutely required (no bypass) to use item
 * @param abs_restricted_race Races specifically not allowed to use
 * @param min_level Minimum character level required to use item
 * @param required_trait Trait such as Lawful needed to use item
 * @param umd Use Magical Device DC
 * @param handedness Allowed slots such as one handed (main or off hand) / two handed
 * @param attack_mod Attribute(s) that modify the attack roll
 * @param damage_mod Attribute(s) that modify the damage roll
 * @param binding Character or Account binding, if any
 * @param durability tensile strength, how hard it is to damage
 * @param wear_location Allowed slots for the item to be equipped
 * @param material material made from
 * @param hardness physical toughness of the item
 * @param base_value Monetary value of item
 * @param weight weight in pounds
 * @param location Text describing the location of the item
 * @param enchantments Collection of Enchantments on the item.
 * @param enchantments_choice Used to support One of the following
 * @param upgradeable If an item can be upgraded, instructions or the name of the upgraded item may appear here.
 * @param description_text descriptive text of the item
 * @param sets Any sets this item belongs to that give bonuses when all items are equipped.
 */
case class Weapon(
  name: Option[String],
  proficiency_class: Option[String],
  image: Option[String],
  damage: Option[Damage],
  damage_type: Option[Damage_Type],
  critical_threat_range: Option[Critical_Threat_Range],
  weapon_category: Option[String],
  required_race: Option[Seq[String]],
  abs_required_race: Option[Seq[String]],
  abs_restricted_race: Option[Seq[String]],
  min_level: Option[Int],
  required_trait: Option[Seq[String]],
  umd: Option[String],
  handedness: Option[Seq[String]],
  attack_mod: Option[Seq[String]],
  damage_mod: Option[Seq[String]],
  binding: Option[String],
  durability: Option[Int],
  wear_location: Option[String],
  material: Option[String],
  hardness: Option[Int],
  base_value: Option[String],
  weight: Option[Int],
  location: Option[String],
  enchantments: Option[Seq[String]],
  enchantments_choice: Option[Seq[String]],
  upgradeable: Option[String],
  description_text: Option[String],
  sets: Option[Seq[String]]
)

object Weapon {
  /**
   * Creates the codec for converting Weapon from and to JSON.
   */
  implicit val decoder: Decoder[Weapon] = deriveDecoder
  implicit val encoder: ObjectEncoder[Weapon] = deriveEncoder
}
