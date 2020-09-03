/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.enhancement

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{
  Alchemist,
  Artificer,
  Barbarian,
  Bard,
  Cleric,
  Druid,
  FavoredSoul,
  Fighter,
  Monk,
  Paladin,
  Ranger,
  Rogue,
  Sorcerer,
  Warlock,
  Wizard
}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfClass
}

import scala.collection.immutable

sealed trait EnhancementClass extends EnumEntry with DisplayName with FriendlyDisplay {

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

// Class Enhancements
/*
Alchemist Enhancements
Apothecary enhancements - Buff, support, positive healing & negative damage; Tier 5 - Cure/Inflict Critical Wounds admixture as AoE SLA, no Max Caster Level for Curative Admixtures and Gildleaf spells
Bombardier enhancements - Spell DPS, elemental SLAs, bonuses with Pyrite reactions; Tier 5 - Make immune creatures vulnerable to elemental damage
Vile Chemist enhancements - Poisons & debuffs: Additional poison damage to weapons (scales with Alchemist level & Cores enhancements); Bonuses to hit/damage for simple weapons; AoE poison SLAs with lingering damage; Tier 5 - Extra Critical Threat with Darts
 */

trait AlchemistEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Alchemist, 1))
}

/*
Artificer enhancements
Enhancements: Arcanotechnician, Battle Engineer, Renegade Mastermaker
 */
trait ArtificerEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 1))
}

/*
Barbarian
Enhancements: Frenzied Berserker, Occult Slayer, Ravager
 */
trait BarbarianEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Barbarian, 1))
}

/*
Bard
Enhancements: Spellsinger, Warchanter, Swashbuckler
 */
trait BardEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Bard, 1))
}

/*
Cleric
Enhancements: Divine Disciple, Warpriest, Radiant Servant
 */
trait ClericEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Cleric, 1))
}

/*
Druid
Enhancements: Nature's Protector, Nature's Warrior, Season's Herald
@note may need to adjust entry name to include apostrophe ??
 */
trait DruidEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 1))
}

/*
Favored Soul
Enhancements: Angel of Vengeance, Beacon of Hope, War Soul
 */

trait FavoredSoulEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((FavoredSoul, 1))
}

/*
Fighter
Enhancements: Kensei, Stalwart Defender, Vanguard
 */
trait FighterEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Fighter, 1))
}

trait FighterOrPaladinEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 1), (Fighter, 1))

}

/*
Monk
Enhancements: Henshin Mystic, Ninja Spy, Shintao Monk
 */
trait MonkEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 1))
}

/*
Paladin
Enhancements: Knight of the Chalice, Sacred Defender, Vanguard
 */

trait PaladinEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 1))
}

// Vanguard included with Fighter Enhancement of the same name
/*
Ranger
Enhancements: Arcane Archer, Deepwood Stalker, Tempest
 */
trait RangerEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 1))
}

/*
Rogue
Enhancements: Assassin, Mechanic, Thief-Acrobat
 */

trait RogueEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Rogue, 1))
}

/*
Sorcerer
Enhancements: Air Savant, Earth Savant, Eldritch Knight, Fire Savant, Water Savant
 */
trait SorcererEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Sorcerer, 1))
}

trait SorcererOrWizardEnhancement
    extends EnhancementClass
    with ClassRequisiteImpl
    with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Sorcerer, 1), (Wizard, 1))
}

/*
Warlock
Enhancements: Soul Eater, Tainted Scholar, Enlightened Spirit
 */
trait WarlockEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Warlock, 1))
}

/*
Wizard
Enhancements: Archmage, Eldritch Knight, Pale Master
 */
trait WizardEnhancement extends EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Wizard, 1))
}

/*
Reaper *
Reaper Enhancements: Dread Adversary, Dire Thaumaturge, Grim Barricade
 */

// scalastyle:off number.of.methods
object EnhancementClass extends Enum[EnhancementClass] {
    val matchFeatById: PartialFunction[HeroicCharacterClass, EnhancementClass with ClassRequisiteImpl with RequiresAllOfClass] = {
        case Alchemist => new AlchemistEnhancement {}
        case Artificer => new ArtificerEnhancement {}
        case Bard => new BardEnhancement {}
        case Cleric => new ClericEnhancement {}
        case Druid => new DruidEnhancement {}
        case FavoredSoul => new FavoredSoulEnhancement {}
            case Fighter => new FighterEnhancement {}
        case Monk => new MonkEnhancement {}
        case Paladin => new PaladinEnhancement {}
        case Rogue => new RogueEnhancement {}
        case Ranger => new RangerEnhancement {}
        case Sorcerer => new SorcererEnhancement {}
        case Warlock => new WarlockEnhancement {}
        case Wizard => new WizardEnhancement {}
    }
  case class SingleClass(heroicCharacterClass: HeroicCharacterClass)
      extends EnhancementClass
      with ClassRequisiteImpl
      with RequiresAllOfClass {
    override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Seq((heroicCharacterClass, 1))
  }

  def singleClassEnhancements: immutable.Seq[SingleClass] = HeroicCharacterClass.values.map {
    SingleClass
  }
  case object FighterOrPaladinEnhancement extends FighterOrPaladinEnhancement
  case object SorcererOrWizardEnhancement extends SorcererOrWizardEnhancement
  override def values: immutable.IndexedSeq[EnhancementClass] = findValues ++ singleClassEnhancements
}
