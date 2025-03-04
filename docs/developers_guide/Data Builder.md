# Data population

## Entities and properties

### Entities

Items
Sourced from DDO Wiki

Initial Population:
From Items, extract text values by category via ParseHub.
run_results.json to bulk load. This should be a very limited run.
Ideally a one time run.

Fields
| JSoN Field | Target Type | Group | Errata |
|---|---|---|---|
| Name | NamedPattern -> id | Common | May be unique or have a qualifier such as ML (Level X) or Flag (Historic)|
| Required Trait | List[Trait] | Common | May be class, alignment |
| Minimum Level | Integer | Common | May be blank to indicate no Minimum Level |
| [Slot](https://ddowiki.com/page/Slot) | Slot | Jewlery; Clothing | |
| Race Absolutely Required | List[Race] | Common | race you must be to use the item. |
| Race Absolutely Excluded | List[Race] | Common | races that are not allowed to use the item. |
| Binding | List[Binding] | Common | Field contains additional flags such as Exclusive and Drops On Death; May have multiple types depending on acquisition (i.e. BTA from chest but BTC from completion reward or trade-in) |
| Durability | Integer | Common | |
| Material / Made From | List[Material] | Common | This will generally be a single material but may be multiple materials in the case of Weapons etc. |
| Hardness | Integer | Common | |
| Weight | Float | Common | |
| Value | Integer | Common | |
| Location | List[Location] | Common | Quest (optional qualifiers such as end chest {Difficulty})/ NPC / Crafting |
| Upgradable? | Text specifying upgrade path | Common | |
| Description | Text | Common | |
| Notes | Text | Common | |
| Tips | Text | Common | Appears to be a list of tips |
| Enchantment | List[Enchantment] | Common | List of Enchantments / Enchantment Options / Augment Slots |
| Proficiency Class | Weapon Proficiency | Weapon | |
| Accepts Sentience? | Boolean | Weapon | |
| Damage and Type / Damage | DnD Dice Expression | Weapon, Shield | value such as 1.50[1d10] + 5 Slash, Magic|
| [Critical Threat Range](https://ddowiki.com/page/Threat_range) | Dice / Multiplier | Weapon, Shield | e.g. 19-20 / x3 |
| Weapon Type | Weapon Category / Damage Type (Slashing, Piercing, Bludgeoning) | Weapon, Shield | |
| Use Magical Device DC | Integer | Common | DC check to bypass Requirements|
| Handedness | Unknown | Weapon | Need to find a non-null example |
| Attack Mod | Attribute | Weapon, Shield | Default Attribute for bonus to hit |
| Damage Mod | Attribute | Weapon, Shield | Default Attribute for bonus to damage |
| Shield Type | Shield Type | Shield | |
| Proficiency | Shield Proficiency (Feat) | Shield | Feat required to use without penalty |
| Shield Bonus | Integer | Shield | Armor class bonus |
| Max Dex Bonus | Integer | Shield, Armor | Caps the maximum Dex bonus applied while using the item |
| Damage Reduction | Integer | Shield | Damage Reduction bonus while actively blocking |
| Armor Check Penalty | Integer | Shield, Armor | Penalty to skill checks while wearing the item |
| Arcane Spell Failure | Integer (Percent) | Shield, Armor | % Penalty to Arcane spellcasting while wearing the item (Does not affect divine spells)|
| Feat Requirement | Armor Proficiency | Armor | Feat required to use without penalty |
| Spell | Spell | Spell, Scroll, Wand | |
| Equips to | List[Slot] | Scroll, Wand | Should map to Slot field? |
| No Umd Check For | List[(Class,Level)] |Scroll, Wand | Class, Level pairs that can use the item without penalty such as Wiz 3|
| Caster Level | Integer | Scroll, Wand | Caster level of the spell |
| Vendors | List[Vendor] | Scroll, Wand, Potion | List of Vendors that sell the item |
| Acquired From | List[Quest, Vendor, DDO Store] | Potion | |
| Stack Size | Integer | Potion | |
| Effect | List[Effect] | Potion | Will likely need to rename this or alter effect property in implementation classes |

Incremental Update / Population:

- Individual Page scraping via ddo-web may be invoked to verify and update.
- Scrape of new items from release notes to only add new items.

### Item Entity States

Persisting Entities

- Item
    - Base Item - May have multiple variants based on enchantments such as 'One of 4 enchantments' or Mythic boost values. These values must be specified when creating an instance.
    - Multi-level Item
      Item has variants generally based on difficulty levels.
    - Instance Item - An instance of an Item. This item would be a specific level with any and all options specified such as enchantment choices, augment slots, etc.

We may need a meta-data flag to indicate if an item is a base item and / or if it has variants.
