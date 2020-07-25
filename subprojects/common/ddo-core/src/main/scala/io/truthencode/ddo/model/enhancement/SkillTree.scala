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

sealed trait SkillTree extends EnumEntry with DisplayName with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

// Class Enhancements
/*


Artificer enhancements
Enhancements: Arcanotechnician, Battle Engineer, Renegade Mastermaker
 */
trait ArtificerEnhancement
    extends SkillTree
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
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Barbarian, 1))
}

/*
Bard
Enhancements: Spellsinger, Warchanter, Swashbuckler
 */
trait BardEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Bard, 1))
}

/*
Cleric
Enhancements: Divine Disciple, Warpriest, Radiant Servant
 */
trait ClericEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Cleric, 1))
}

/*
Druid
Enhancements: Nature's Protector, Nature's Warrior, Season's Herald
@note may need to adjust entry name to include apostrophe ??
 */
trait DruidEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 1))
}

/*Favored Soul
Enhancements: Angel of Vengeance, Beacon of Hope, War Soul
 */

trait FavoredSoulEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((FavoredSoul, 1))
}

/*
Fighter
Enhancements: Kensei, Stalwart Defender, Vanguard
 */
trait FighterEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Fighter, 1))
}

trait FighterOrPaladinEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 1), (Fighter, 1))

}

/*
Monk
Enhancements: Henshin Mystic, Ninja Spy, Shintao Monk
 */
trait MonkEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 1))
}

/*
Paladin
Enhancements: Knight of the Chalice, Sacred Defender, Vanguard
 */

trait PaladinEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAnyOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Fighter, 1))
}

// Vanguard included with Fighter Enhancement of the same name
/*
Ranger
Enhancements: Arcane Archer, Deepwood Stalker, Tempest
 */
trait RangerEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 1))
}

/*
Rogue
Enhancements: Assassin, Mechanic, Thief-Acrobat
 */

trait RogueEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Rogue, 1))
}

/*
Sorcerer
Enhancements: Air Savant, Earth Savant, Eldritch Knight, Fire Savant, Water Savant
 */
trait SorcererEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Sorcerer, 1))
}

trait SorcererOrWizardEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAnyOfClass {

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Sorcerer, 1))
}

/*
Warlock
Enhancements: Soul Eater, Tainted Scholar, Enlightened Spirit
 */
trait WarlockEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Warlock, 1))
}

/*
Wizard
Enhancements: Archmage, Eldritch Knight, Pale Master
 */
trait WizardEnhancement
    extends SkillTree
    with ClassRequisiteImpl
    with RequiresAllOfClass {

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Wizard, 1))
}

/*
Reaper *
Reaper Enhancements: Dread Adversary, Dire Thaumaturge, Grim Barricade
 */
object SkillTree extends Enum[SkillTree] {
  case object Arcanotechnician extends Arcanotechnician

  case object BattleEngineer extends BattleEngineer

  case object RenegadeMastermaker extends RenegadeMastermaker
  /*
    Barbarian
    Enhancements: Frenzied Berserker, Occult Slayer, Ravager
   */

  case object FrenziedBerserker extends FrenziedBerserker

  case object OccultSlayer$ extends OccultSlayer
  case object Ravager extends Ravager
  /*
    Bard
    Enhancements: Spellsinger, Warchanter, Swashbuckler
   */

  case object SpellSinger extends SpellSinger

  case object Warchanter$ extends Warchanter

  case object Swashbuckler extends Swashbuckler
  /*
    Cleric
    Enhancements: Divine Disciple, Warpriest, Radiant Servant
   */

  case object DivineDisciple extends DivineDisciple

  case object WarPriest extends WarPriest

  case object RadiantServant extends RadiantServant

  /*
    Druid
    Enhancements: Nature's Protector, Nature's Warrior, Season's Herald
    @note may need to adjust entry name to include apostrophe ??
   */

  case object NaturesProtector extends NaturesProtector
  case object NaturesWarrior extends NaturesWarrior
  case object SeasonsHerald extends SeasonsHerald

  /*Favored Soul
    Enhancements: Angel of Vengeance, Beacon of Hope, War Soul
   */

  case object AngelOfVengeance extends AngelOfVengeance

  case object BeaconOfHope extends BeaconOfHope

  case object WarSoul extends WarSoul

  /*
    Fighter
    Enhancements: Kensei, Stalwart Defender, Vanguard
   */

  case object Kensei$ extends Kensei

  case object StalwartDefender extends StalwartDefender

  case object Vanguard extends Vanguard

  /*
    Monk
    Enhancements: Henshin Mystic, Ninja Spy, Shintao Monk
   */

  case object HenshinMystic extends HenshinMystic

  case object NinjaSpy extends NinjaSpy

  case object ShintaoMonk extends ShintaoMonk
  /*
    Paladin
    Enhancements: Knight of the Chalice, Sacred Defender, Vanguard
   */

  case object KnightOfTheChalice extends KnightOfTheChalice

  case object SacredDefender extends SacredDefender

  // Vanguard included with Fighter Enhancement of the same name
  /*
    Ranger
    Enhancements: Arcane Archer, Deepwood Stalker, Tempest
   */

  /**
    * @note will either need to qualify class or compensate for Racial Elven Arcane Archer access
    */
  case object ArcaneArcher extends ArcaneArcher

  case object DeepwoodStalker$ extends DeepwoodStalker

  case object Tempest extends Tempest
  /*
    Rogue
    Enhancements: Assassin, Mechanic, Thief-Acrobat
   */

  case object Assassin extends Assassin

  case object Mechanic extends Mechanic

  /**
    * @note may need to override display name to add Hyphen
    *       perhaps add Hyphenate rule to display options
    */
  case object ThiefAcrobat extends ThiefAcrobat

  /*
    Sorcerer
    Enhancements: Air Savant, Earth Savant, Eldritch Knight, Fire Savant, Water Savant
   */

  case object AirSavant extends AirSavant

  case object EarthSavant extends EarthSavant

  case object EldritchKnight extends EldritchKnight

  case object FireSavant extends FireSavant

  case object WaterSavant extends WaterSavant

  /*
    Warlock
    Enhancements: Soul Eater, Tainted Scholar, Enlightened Spirit
   */

  case object SoulEater extends SoulEater

  case object TaintedScholar extends TaintedScholar

  case object EnlightenedSpirit extends EnlightenedSpirit

  /*
    Wizard
    Enhancements: Archmage, Eldritch Knight, Pale Master
   */

  case object Archmage extends Archmage

  case object PaleMaster extends PaleMaster
  override def values: immutable.IndexedSeq[SkillTree] = findValues
}
