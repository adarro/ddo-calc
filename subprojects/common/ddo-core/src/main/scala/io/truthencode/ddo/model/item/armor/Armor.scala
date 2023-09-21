/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.model.item.armor

import io.truthencode.ddo.Wearable
import io.truthencode.ddo.model.item.WearableItem
import io.truthencode.ddo.support.slots.WearLocation

/**
 * Represents body armor such as Breast plates, Chainmail etc.
 *
 * @example
 *   Armor Type Breastplate / Scalemail Blue Dragonscale Armor shown.jpg Feat Requirement Medium
 *   Armor Proficiency Race Absolutely Excluded Warforged Minimum Level 25 Binding Bound to
 *   Character on Acquire Armor Bonus +26 Maximum Dexterity Bonus 8 Armor Check Penalty -1 Arcane
 *   Spell Failure 25% Durability 312 Material Blue Dragonscale Hardness 29 Base Value 10,020pp
 *   Weight 15 lbs Location Blue Dragonscale Armor Enchantments +7 Enhancement Bonus Attuned to
 *   Heroism Attuned by Heroism: Tier 1 +7 Enhancement Bonus becomes +8 Enhancement Bonus Attuned by
 *   Heroism: Tier 2 Spell Lore VI becomes Spell Lore IX Draconic Mind Spell Penetration III Spell
 *   Lore VI Potency +80 Superior Lightning Resistance Blue Augment Slot
 */
trait Armor extends WearableItem with Wearable {

  /**
   * A bitmask that corresponds to one or more [io.truthencode.ddo.WearLocation] values.
   */
  override def allowedWearLocationFlags: Int = WearLocation.Body.bitValue
}
