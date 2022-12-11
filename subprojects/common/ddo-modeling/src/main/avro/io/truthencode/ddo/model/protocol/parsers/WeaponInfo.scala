/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package io.truthencode.ddo.model.protocol.parsers

sealed trait WeaponInfo

/**
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
 * @param abs_min_level Altered minimum level usually caused as a side-effect of additional enchantments which may increase the requirement
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
final case class Weapon(name: Option[String], proficiency_class: Option[Weapon_proficiencyUnderscoreclass], image: Option[String], damage: Option[DamageTrait], damage_type: Option[DamageInfo], critical_threat_range: Option[CriticalThreatRange], weapon_category: Option[Weapon_weaponUnderscorecategory], required_race: Option[Seq[String]], abs_required_race: Option[Seq[String]], abs_restricted_race: Option[Seq[String]], min_level: Option[Int], abs_min_level: Option[Int], required_trait: Option[Seq[String]], umd: Option[String], handedness: Option[Seq[String]], attack_mod: Option[Seq[String]], damage_mod: Option[Seq[String]], binding: Option[String], durability: Option[Int], wear_location: Option[Weapon_wearUnderscorelocation], material: Option[Weapon_material], hardness: Option[Int], base_value: Option[String], weight: Option[Int], location: Option[String], enchantments: Option[Seq[String]], enchantments_choice: Option[Seq[String]], upgradeable: Option[String], description_text: Option[String], sets: Option[Seq[String]]) extends WeaponInfo

sealed trait Weapon_proficiencyUnderscoreclass extends WeaponInfo

object Weapon_proficiencyUnderscoreclass {
  case object Simple extends Weapon_proficiencyUnderscoreclass
  case object Martial extends Weapon_proficiencyUnderscoreclass
  case object Exotic extends Weapon_proficiencyUnderscoreclass
}

sealed trait Weapon_weaponUnderscorecategory extends WeaponInfo

object Weapon_weaponUnderscorecategory {
  case object BastardSword extends Weapon_weaponUnderscorecategory
  case object BattleAxe extends Weapon_weaponUnderscorecategory
  case object Club extends Weapon_weaponUnderscorecategory
  case object Dagger extends Weapon_weaponUnderscorecategory
  case object Dart extends Weapon_weaponUnderscorecategory
  case object DwarvenWarAxe extends Weapon_weaponUnderscorecategory
  case object Falchion extends Weapon_weaponUnderscorecategory
  case object GreatAxe extends Weapon_weaponUnderscorecategory
  case object GreatClub extends Weapon_weaponUnderscorecategory
  case object GreatCrossbow extends Weapon_weaponUnderscorecategory
  case object GreatSword extends Weapon_weaponUnderscorecategory
  case object HandAxe extends Weapon_weaponUnderscorecategory
  case object Handwrap extends Weapon_weaponUnderscorecategory
  case object HeavyCrossbow extends Weapon_weaponUnderscorecategory
  case object HeavyMace extends Weapon_weaponUnderscorecategory
  case object HeavyPick extends Weapon_weaponUnderscorecategory
  case object Kama extends Weapon_weaponUnderscorecategory
  case object Khopesh extends Weapon_weaponUnderscorecategory
  case object Kukris extends Weapon_weaponUnderscorecategory
  case object LightCrossbow extends Weapon_weaponUnderscorecategory
  case object LightHammer extends Weapon_weaponUnderscorecategory
  case object LightMace extends Weapon_weaponUnderscorecategory
  case object LightPick extends Weapon_weaponUnderscorecategory
  case object Longbow extends Weapon_weaponUnderscorecategory
  case object Longsword extends Weapon_weaponUnderscorecategory
  case object Maul extends Weapon_weaponUnderscorecategory
  case object Morningstar extends Weapon_weaponUnderscorecategory
  case object Quarterstaff extends Weapon_weaponUnderscorecategory
  case object Rapier extends Weapon_weaponUnderscorecategory
  case object RepeatingHeavyCrossbow extends Weapon_weaponUnderscorecategory
  case object RepeatingLightCrossbow extends Weapon_weaponUnderscorecategory
  case object Scimitar extends Weapon_weaponUnderscorecategory
  case object Shortbow extends Weapon_weaponUnderscorecategory
  case object ShortSword extends Weapon_weaponUnderscorecategory
  case object Shuriken extends Weapon_weaponUnderscorecategory
  case object Sickle extends Weapon_weaponUnderscorecategory
  case object SimpleProjectile extends Weapon_weaponUnderscorecategory
  case object ThrowingAxe extends Weapon_weaponUnderscorecategory
  case object ThrowingDagger extends Weapon_weaponUnderscorecategory
  case object ThrowingHammer extends Weapon_weaponUnderscorecategory
  case object WarHammer extends Weapon_weaponUnderscorecategory
}

sealed trait Weapon_wearUnderscorelocation extends WeaponInfo

object Weapon_wearUnderscorelocation {
  case object MainHand extends Weapon_wearUnderscorelocation
  case object OffHand extends Weapon_wearUnderscorelocation
  case object TwoHand extends Weapon_wearUnderscorelocation
}

sealed trait Weapon_material extends WeaponInfo

object Weapon_material {
  case object Adamantine extends Weapon_material
  case object Blueshine extends Weapon_material
  case object Bone extends Weapon_material
  case object Byeshk extends Weapon_material
  case object Cloth extends Weapon_material
  case object Cold_Iron extends Weapon_material
  case object Crystal extends Weapon_material
  case object Darkleaf extends Weapon_material
  case object Darkwood extends Weapon_material
  case object Densewood extends Weapon_material
  case object Dwarven_Iron extends Weapon_material
  case object Feyleather extends Weapon_material
  case object Flametouched_Iron extends Weapon_material
  case object Flesh extends Weapon_material
  case object Force extends Weapon_material
  case object Gem extends Weapon_material
  case object Glass extends Weapon_material
  case object Ice extends Weapon_material
  case object Leather extends Weapon_material
  case object Light extends Weapon_material
  case object Magesteel extends Weapon_material
  case object Mithral extends Weapon_material
  case object Planeforged_Steel extends Weapon_material
  case object Rust extends Weapon_material
  case object Silver extends Weapon_material
  case object Spiritcraft_Leather extends Weapon_material
  case object Spiritforged_Iron extends Weapon_material
  case object Steel extends Weapon_material
  case object Stone extends Weapon_material
  case object Wood extends Weapon_material
}