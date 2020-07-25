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
package io.truthencode.ddo.support.tree

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.enhancement._
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

sealed trait ClassTrees
    extends EnumEntry
    with ClassTree
    with DisplayName
    with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}
// scalastyle:off number.of.methods
object ClassTrees extends Enum[ClassTrees] with TreePrefix {
  override def values: immutable.IndexedSeq[ClassTrees] = findValues
  // Artificier
  case object Arcanotechnician extends ClassTrees with Arcanotechnician
  case object BattleEngineer extends ClassTrees with BattleEngineer
  case object RenegadeMastermaker extends ClassTrees with RenegadeMastermaker
  // Barbarian
  case object FrenziedBerserker extends ClassTrees with FrenziedBerserker
  case object OccultSlayer extends ClassTrees with OccultSlayer
  case object Ravager extends ClassTrees with Ravager
  //  Bard
  case object Spellsinger extends ClassTrees with SpellSinger
  case object Warchanter extends ClassTrees with Warchanter
  case object Swashbuckler extends ClassTrees with Swashbuckler
  // Cleric
  case object DivineDisciple extends ClassTrees with DivineDisciple
  case object Warpriest extends ClassTrees with WarPriest
  case object RadiantServant extends ClassTrees with RadiantServant
  // Druid
  case object NaturesProtector extends ClassTrees with NaturesProtector
  case object NaturesWarrior extends ClassTrees with NaturesWarrior
  case object SeasonsHerald extends ClassTrees with SeasonsHerald
  //  Favored Soul
  case object AngelOfVengeance extends ClassTrees with AngelOfVengeance
  case object BeaconOfHope extends ClassTrees with BeaconOfHope
  case object WarSoul extends ClassTrees with WarSoul
  // Fighter
  case object Kensei extends ClassTrees with Kensei
  case object StalwartDefender extends ClassTrees with StalwartDefender
  case object Vanguard extends ClassTrees with Vanguard
  // Monk
  case object HenshinMystic extends ClassTrees with HenshinMystic
  case object NinjaSpy extends ClassTrees with NinjaSpy
  case object ShintaoMonk extends ClassTrees with ShintaoMonk
  // Paladin
  case object KnightOfTheChalice extends ClassTrees with KnightOfTheChalice
  case object SacredDefender extends ClassTrees with SacredDefender
  // Ranger
  case object ArcaneArcher extends ClassTrees with ArcaneArcher
  case object DeepwoodStalker extends ClassTrees with DeepwoodStalker
  case object Tempest extends ClassTrees with Tempest
  // Rogue
  case object Assassin extends ClassTrees with Assassin
  case object Mechanic extends ClassTrees with Mechanic
  case object ThiefAcrobat extends ClassTrees with ThiefAcrobat
  // Sorcerer
  case object AirSavant extends ClassTrees with AirSavant
  case object EarthSavant extends ClassTrees with EarthSavant
  case object EldritchKnight extends ClassTrees with EldritchKnight
  case object FireSavant extends ClassTrees with FireSavant
  case object WaterSavant extends ClassTrees with WaterSavant
  // Warlock
  case object SoulEater extends ClassTrees with SoulEater
  case object TaintedScholar extends ClassTree with TaintedScholar
  case object EnlightenedSpirit extends ClassTrees with EnlightenedSpirit
  //  Wizard
  case object Archmage extends ClassTrees with Archmage
  case object PaleMaster extends ClassTrees with PaleMaster

}
