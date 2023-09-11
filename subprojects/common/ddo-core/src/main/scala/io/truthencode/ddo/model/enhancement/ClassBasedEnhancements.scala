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
package io.truthencode.ddo.model.enhancement

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass._
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.DisplayName
import io.truthencode.ddo.support.requisite.{
  ClassRequisite,
  ClassRequisiteImpl,
  RequiresAllOfClass,
  RequiresAnyOfClass
}

import scala.collection.immutable

sealed trait ClassBasedEnhancements extends EnumEntry with DisplayName {
  self: ClassRequisite =>

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
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Alchemist, 1))
}

/*
Artificer enhancements
Enhancements: Arcanotechnician, Battle Engineer, Renegade Mastermaker
 */
trait ArtificerEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 1))
}

/*
Barbarian
Enhancements: Frenzied Berserker, Occult Slayer, Ravager
 */
trait BarbarianEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Barbarian, 1))
}

/*
Bard
Enhancements: Spellsinger, Warchanter, Swashbuckler
 */
trait BardEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Bard, 1))
}

/*
Cleric
Enhancements: Divine Disciple, Warpriest, Radiant Servant
 */
trait ClericEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Cleric, 1))
}

/*
Druid
Enhancements: Nature's Protector, Nature's Warrior, Season's Herald
@note may need to adjust entry name to include apostrophe ??
 */
trait DruidEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 1))
}

/*
Favored Soul
Enhancements: Angel of Vengeance, Beacon of Hope, War Soul
 */

trait FavoredSoulEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((FavoredSoul, 1))
}

/*
Fighter
Enhancements: Kensei, Stalwart Defender, Vanguard
 */
trait FighterEnhancement extends FighterOrPaladinEnhancement with RequiresAllOfClass {
  private val cls = List((Fighter, 1))
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = List((Fighter, 1))
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Fighter, 1))

}

trait FighterOrPaladinEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 1), (Fighter, 1))

}

/*
Monk
Enhancements: Henshin Mystic, Ninja Spy, Shintao Monk
 */
trait MonkEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 1))
}

/*
Paladin
Enhancements: Knight of the Chalice, Sacred Defender, Vanguard
 */

trait PaladinEnhancement extends FighterOrPaladinEnhancement with RequiresAllOfClass {
  private val cls = List((Paladin, 1))
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = cls
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = cls

}

// Vanguard included with Fighter Enhancement of the same name
/*
Ranger
Enhancements: Arcane Archer, Deepwood Stalker, Tempest
 */
trait RangerEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 1))
}

/*
Rogue
Enhancements: Assassin, Mechanic, Thief-Acrobat
 */

trait RogueEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Rogue, 1))
}

/*
Sorcerer
Enhancements: Air Savant, Earth Savant, Eldritch Knight, Fire Savant, Water Savant
 */
trait SorcererEnhancement extends SorcererOrWizardEnhancement with RequiresAllOfClass {
  private val cls = List((Sorcerer, 1))
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = cls
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = cls

}

trait SorcererOrWizardEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Sorcerer, 1), (Wizard, 1))
}

/*
Warlock
Enhancements: Soul Eater, Tainted Scholar, Enlightened Spirit
 */
trait WarlockEnhancement
  extends ClassBasedEnhancements with ClassRequisiteImpl with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Warlock, 1))
}

/*
Wizard
Enhancements: Archmage, Eldritch Knight, Pale Master
 */
trait WizardEnhancement extends SorcererOrWizardEnhancement with RequiresAllOfClass {
  private val cls = List((Wizard, 1))
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = cls
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = cls

}

/*
Reaper *
Reaper Enhancements: Dread Adversary, Dire Thaumaturge, Grim Barricade
 */

// scalastyle:off number.of.methods
object ClassBasedEnhancements extends Enum[ClassBasedEnhancements] {

  override lazy val values: immutable.IndexedSeq[ClassBasedEnhancements] =
    findValues

  case object FighterOrPaladinEnhancement extends FighterOrPaladinEnhancement

  case object SorcererOrWizardEnhancement extends SorcererOrWizardEnhancement

  case object AlchemistEnhancement extends AlchemistEnhancement

  case object ArtificerEnhancement extends ArtificerEnhancement

  case object BardEnhancementEnhancement extends BardEnhancement

  case object ClericEnhancementEnhancement extends ClericEnhancement

  case object DruidEnhancement extends DruidEnhancement

  case object FavoredSoulEnhancement extends FavoredSoulEnhancement

  case object FighterEnhancement extends FighterEnhancement

  case object MonkEnhancement extends MonkEnhancement

  case object PaladinEnhancement extends PaladinEnhancement

  case object RogueEnhancement extends RogueEnhancement

  case object RangerEnhancement extends RangerEnhancement

  case object SorcererEnhancement extends SorcererEnhancement

  case object WarlockEnhancement extends WarlockEnhancement

  case object WizardEnhancement extends WizardEnhancement
}
