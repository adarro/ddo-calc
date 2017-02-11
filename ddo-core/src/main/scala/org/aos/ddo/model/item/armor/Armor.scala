package org.aos.ddo.model.item.armor

import org.aos.ddo.{Item, MetaData}

/** Represents body armor such as Breast plates, Chainmail etc.
  *
  * @example
  * Armor Type  Breastplate / Scalemail
  * Blue Dragonscale Armor shown.jpg
  * Feat Requirement  Medium Armor Proficiency
  * Race Absolutely Excluded  Warforged
  * Minimum Level  25
  * Binding  Bound to Character on Acquire
  * Armor Bonus  +26
  * Maximum Dexterity Bonus  8
  * Armor Check Penalty  -1
  * Arcane Spell Failure  25%
  * Durability  312
  * Material  Blue Dragonscale
  * Hardness  29
  * Base Value  10,020pp
  * Weight  15 lbs
  * Location  Blue Dragonscale Armor
  * Enchantments
  * +7 Enhancement Bonus
  * Attuned to Heroism
  * Attuned by Heroism: Tier 1
  * +7 Enhancement Bonus becomes +8 Enhancement Bonus
  * Attuned by Heroism: Tier 2
  * Spell Lore VI becomes Spell Lore IX
  * Draconic Mind
  * Spell Penetration III
  * Spell Lore VI
  * Potency +80
  * Superior Lightning Resistance
  * Blue Augment Slot
  */
trait Armor extends Item with MetaData
