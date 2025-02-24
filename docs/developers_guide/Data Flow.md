# Data Ingestion

## Bulk Load

json files from ParseHub

- Initial bulk loads
  occur for initial load and a scan of update items on release of expansions / updates.
  a. Download results to common folder and use Camel Route filter to move to appropriate folder
  b. Download results directly to appropriate folder for processing.
  c. post (via Camel Route) to appropriate web service for processing.
- Categories
    - Items
        1. Weapons
        1. Armor
        1. Shields
        1. Jewlery
        1. Clothing
        1. Consumables
            1. Potions
            1. Scrolls
            1. Wands
        1. Augments
            1. Chromatic
            1. Lunar / Solar
        1. Filigrees
    - Other Entities
        1. Affixes
            1. Prefixes
            1. Suffixes
        1. Spells
    - Effects
        1. Additional Effects
            1. Mythic Boots
            1. Reaper Bonus
            1. Deck of Many Things
            1. Stone of Change

Currently 'Hard-coded'
Feats
Skills
Attributes (Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma)
Races
Classes

TBD:
Destinies
Destiny Effects should be similar to implementing other effects.
Codifying prerequisites might be done like feats unless we create a parser -> requisite.
