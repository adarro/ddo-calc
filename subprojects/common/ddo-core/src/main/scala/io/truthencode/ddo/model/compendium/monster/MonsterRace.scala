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
package io.truthencode.ddo.model.compendium.monster

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.compendium.types._

import scala.collection.immutable

/**
 * Represents a Monster Race and should essentially match the corresponding Monster Manual entry.
 * This may at some point be used to add default resists / vulnerabilities to calculations. Examples
 * include racial bane weapons, favored enemies etc. Created by adarr on 3/25/2017.
 */
sealed trait MonsterRace extends EnumEntry with MainTypeImpl {

  lazy val subRaces: List[SubRace] = Nil

}
// scalastyle:off number.of.methods
object MonsterRace extends Enum[MonsterRace] {
  override def values: immutable.IndexedSeq[MonsterRace] = findValues

  case object Beholder extends MonsterRace with Aberrations

  case object DreamReaver extends MonsterRace with Aberrations

  case object Drider extends MonsterRace with Aberrations

  case object DrowScorpion extends MonsterRace with Aberrations

  case object EvilEye extends MonsterRace with Aberrations

  case object EyeHorror extends MonsterRace with Aberrations

  case object Mimic extends MonsterRace with Aberrations

  case object MindFlayer extends MonsterRace with Aberrations

  case object RustMonster extends MonsterRace with Aberrations

  case object Taken extends MonsterRace with Aberrations

  case object WilloWisp extends MonsterRace with Aberrations

  // @todo need to add chaotic neutral
  case object Djinn extends MonsterRace with AirOutsiders

  case object Bat extends MonsterRace with Animals

  case object Bear extends MonsterRace with Animals

  case object Dog extends MonsterRace with Animals

  case object Hyena extends MonsterRace with Animals

  case object Lion extends MonsterRace with Animals

  case object Rat extends MonsterRace with Animals

  case object Wolf extends MonsterRace with Animals

  case object AnimatedObject extends MonsterRace with Constructs

  case object Golem extends MonsterRace with Constructs

  case object InanimateObject extends MonsterRace with Constructs

  case object Inevitable extends MonsterRace with Constructs

  case object IronDefender extends MonsterRace with Constructs

  case object WarforgedTitan extends MonsterRace with Constructs

  case object BlackDragon extends MonsterRace with Dragons

  case object BlueDragon extends MonsterRace with Dragons

  case object GreenDragon extends MonsterRace with Dragons

  case object RedDragon extends MonsterRace with Dragons

  case object WhiteDragon extends MonsterRace with Dragons

  case object Duergar extends MonsterRace with Dwarves

  case object Dwarf extends MonsterRace with Dwarves

  case object Mephit extends MonsterRace // with AirOutsiders with FireOutsiders with EarthOutsiders

  case object AirElemental extends MonsterRace with Elementals

  case object EarthElemental extends MonsterRace with Elementals

  case object FireElemental extends MonsterRace with Elementals

  case object InvisibleStalker extends MonsterRace with Elementals

  case object Muckman extends MonsterRace with Elementals

  case object WaterElemental extends MonsterRace with Elementals

  case object DrowElf extends MonsterRace with Elves

  case object Elf extends MonsterRace with Elves

  case object Demon extends MonsterRace with EvilOutsiders

  case object Devil extends MonsterRace with EvilOutsiders

  case object Dryad extends MonsterRace with TheFey

  case object Giant extends MonsterRace with Giantese

  case object Ogre extends MonsterRace with Giantese

  case object OgreMagi extends MonsterRace with Giantese

  case object Troll extends MonsterRace with Giantese

  case object Gnoll extends MonsterRace with Gnollish

  case object Bugbear extends MonsterRace with Goblinoid

  case object Hobgoblin extends MonsterRace with Goblinoid

  case object BralaniEladrin extends MonsterRace with GoodOutsiders

  case object GhaeleEladrin extends MonsterRace with GoodOutsiders

  case object Halfling extends MonsterRace with Halfings

  case object Human extends MonsterRace with Humanoid

  case object Warforged extends MonsterRace with LivingConstruct

  case object FiendishScorpion extends MonsterRace with MagicalBeasts

  case object FiendishSpider extends MonsterRace with MagicalBeasts

  case object Panther extends Animals

  case object PurpleWorm extends MagicalBeasts

  case object RazorCat extends Animals

  case object Worgs extends MonsterRace

  case object WinterWolves extends MonsterRace

  case object Gargoyle extends MonsterRace

  case object Hag extends MonsterRace

  case object ForestHags extends MonsterRace

  case object Medusa extends MonsterRace

  case object Minotaur extends MonsterRace

  case object Sahuagin extends MonsterRace

  case object Werewolf extends MonsterRace

  case object Wildman extends MonsterRace

  case object Yuanti extends MonsterRace

  case object Tiefling extends MonsterRace

  case object ArcaneOoze extends MonsterRace

  case object BlackPudding extends MonsterRace

  case object GelatinousCube extends MonsterRace

  case object GrayOoze extends MonsterRace

  case object OchreJelly extends MonsterRace

  case object Spell extends MonsterRace

  case object VioletSlime extends MonsterRace

  case object HalfOrc extends MonsterRace

  case object Orc extends MonsterRace

  case object PlantCreature extends MonsterRace

  case object VineStalker extends MonsterRace

  case object WoodWoad extends MonsterRace

  case object Kobold extends MonsterRace

  case object Troglodyte extends MonsterRace

  case object Ghast extends MonsterRace

  case object Ghoul extends MonsterRace

  case object Lich extends MonsterRace

  case object Mummy extends MonsterRace

  case object Quell extends MonsterRace

  case object Shadow extends MonsterRace

  case object Skeleton extends MonsterRace

  case object Spectre extends MonsterRace

  case object Vampire extends MonsterRace

  case object Wheep extends MonsterRace

  case object Wight extends MonsterRace

  case object Wraith extends MonsterRace

  case object Zombie extends MonsterRace

  case object Scorpion extends MonsterRace

  case object Spider extends MonsterRace
}
