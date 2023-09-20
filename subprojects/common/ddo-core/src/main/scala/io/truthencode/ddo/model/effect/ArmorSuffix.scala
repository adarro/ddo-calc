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
package io.truthencode.ddo.model.effect

import enumeratum.{Enum => SmartEnum, EnumEntry}

sealed trait ArmorSuffix extends EnumEntry with Suffix
object ArmorSuffix extends SmartEnum[ArmorSuffix] {
  override val values = findValues
  case object AcidResistance extends ArmorSuffix with Resist with Acid
  case object Axeblock extends ArmorSuffix with DamageReduction with Slashing
  case object ColdResistance extends ArmorSuffix with Resist with Cold
  case object Command extends ArmorSuffix
  // case object  Crafteditemenchantments/Temporarylist  extends ArmorSuffix
  case object ElectricResistance extends ArmorSuffix with Resist with Electric

  /**
   * Enumeration indicating All elements
   */
  case object ElementalResistance extends ArmorSuffix with Resist with ElementalResistance
  case object FalseLife extends ArmorSuffix
  case object FireResistance extends ArmorSuffix with Resist with Fire
  case object GreaterLuck extends ArmorSuffix
  case object Hammerblock extends ArmorSuffix with DamageReduction with Bludgeoning
  case object Incineration extends ArmorSuffix
  case object Invulnerability extends ArmorSuffix with DamageReduction with Magic
  case object LesserSpearblock extends ArmorSuffix with DamageReduction with Slashing
  case object LightResistance extends ArmorSuffix with DamageReduction with Light
  case object Power extends ArmorSuffix
  case object Prisms extends ArmorSuffix with ElementalResistance
  // TODO: Need full pass on Suffix etc as many deep changes since 2017
  //  Need to add Protection to Reinforcement
  case object Reinforcement extends ArmorSuffix with DamageReduction with FullPhysical
  case object Shadow extends ArmorSuffix
  case object SilentMoves extends ArmorSuffix
  case object SonicResistance extends ArmorSuffix with Resist with Sonic
  case object Spearblock extends ArmorSuffix with DamageReduction with Piercing
  case object Stability extends ArmorSuffix
  case object UndeadGuard extends ArmorSuffix
  case object Warding extends ArmorSuffix
  case object Watchfulness extends ArmorSuffix
  case object Wizardry extends ArmorSuffix
}
