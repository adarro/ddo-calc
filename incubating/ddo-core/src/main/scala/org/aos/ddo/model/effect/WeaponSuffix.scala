/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.model.effect

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault

sealed trait WeaponSuffix extends EnumEntry with Suffix
object WeaponSuffix extends Enum[WeaponSuffix] with NoDefault[WeaponSuffix] {
  val values = findValues
  case object ArcaneDetonation extends WeaponSuffix
  case object Backstabbing extends WeaponSuffix
  case object Bane extends WeaponSuffix
  case object Blasphemy extends WeaponSuffix
  case object Bleed extends WeaponSuffix
  case object Bloodletter extends WeaponSuffix
  case object BoneBreaking extends WeaponSuffix
  //  case object Composedweaponaffixes extends Suffix
  // case object  Crafteditemenchantments/Temporarylist  extends Suffix
  case object Dazing extends WeaponSuffix
  case object Debilitation extends WeaponSuffix
  case object Deception extends WeaponSuffix
  case object Destroyer extends WeaponSuffix
  case object Destruction extends WeaponSuffix
  case object Devastation extends WeaponSuffix
  case object Disruption extends WeaponSuffix
  case object DivineLight extends WeaponSuffix
  //  case object ElementalAreaofEffect extends Suffix
  case object Enfeebling extends WeaponSuffix
  case object Everbright extends WeaponSuffix
  case object Ghostbane extends WeaponSuffix
  case object GreaterBane extends WeaponSuffix
  case object GreaterGood extends WeaponSuffix
  case object GreaterParrying extends WeaponSuffix
  case object Heartseeker extends WeaponSuffix
  case object HeavenlyWrath extends WeaponSuffix
  case object ImprovedDestruction extends WeaponSuffix
  case object ImprovedShattermantle extends WeaponSuffix
  case object Lacerating extends WeaponSuffix
  case object LesserBane extends WeaponSuffix
  case object Maiming extends WeaponSuffix
  case object Mangling extends WeaponSuffix
  // case object  Nibor/AlignmentProperties  extends Suffix
  case object Obscenity extends WeaponSuffix
  case object Parrying extends WeaponSuffix
  case object Poison extends WeaponSuffix
  case object Precision extends WeaponSuffix
  case object Puncturing extends WeaponSuffix
  case object PureEvil extends WeaponSuffix
  case object RapidStrikes extends WeaponSuffix
  case object Ribcracker extends WeaponSuffix
  case object Righteous extends WeaponSuffix
  case object Riposte extends WeaponSuffix
  case object Shatter extends WeaponSuffix
  case object Shattering extends WeaponSuffix
  case object Shattermantle extends WeaponSuffix
  case object Slowburst extends WeaponSuffix
  case object Smiting extends WeaponSuffix
  case object SpellLore extends WeaponSuffix
  case object SpellMastery extends WeaponSuffix
  case object Spellsight extends WeaponSuffix
  case object Stunning extends WeaponSuffix
  case object Sundering extends WeaponSuffix
  case object SupremeChaos extends WeaponSuffix
  case object SupremeEvil extends WeaponSuffix
  case object SupremeGood extends WeaponSuffix
  case object SupremeLaw extends WeaponSuffix
  case object TendonSlice extends WeaponSuffix
  case object TheCrusader extends WeaponSuffix
  case object TheIcyDepths extends WeaponSuffix
  case object TheRebel extends WeaponSuffix
  case object TheSun extends WeaponSuffix
  case object TheTyrant extends WeaponSuffix
  case object TrueAlignment extends WeaponSuffix
}
