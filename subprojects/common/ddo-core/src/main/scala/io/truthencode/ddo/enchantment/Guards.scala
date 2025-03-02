/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Guards.scala
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
package io.truthencode.ddo.enchantment

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault

import scala.collection.immutable.IndexedSeq

sealed trait Guards extends EnumEntry
object Guards extends Enum[Guards] with NoDefault[Guards] {
  val values: IndexedSeq[Guards] = findValues
  case object AcidGuard extends Guards
  case object AirGuard extends Guards
  case object CacophonicGuard extends Guards
  case object ChaosGuard extends Guards
  case object CorrosiveSaltGuard extends Guards
  case object DiseaseGuard extends Guards
  case object DispellingGuard extends Guards
  case object DisentigrationGuard extends Guards
  case object DisruptionGuard extends Guards
  case object EarthenGuard extends Guards
  case object EarthGrabGuard extends Guards
  case object EnervationGuard extends Guards
  case object EvilGuard extends Guards
  case object FireGuard extends Guards
  case object FreezingIceGuard extends Guards
  case object FrostGuard extends Guards
  case object GoodGuard extends Guards
  case object HasteGuard extends Guards
  case object IceGuard extends Guards
  case object IncinerationGuard extends Guards
  case object InvisibilityGuard extends Guards
  // TODO: Not sure if Light Of Dawn qualifies here
  case object LightOfDawnGuard extends Guards
  case object LightingStormGuard extends Guards
  case object MagmaSurgeGuard extends Guards
  case object MelodicGuard extends Guards
  case object NightmareGuard extends Guards
  case object PoisonGuard extends Guards
  case object RadianceGuard extends Guards
  case object SolarGuard extends Guards
  case object SonicGuard extends Guards
  case object StickyGooGaurd extends Guards
  case object StonePrisonGuard extends Guards
  case object SunderingOozeGuard extends Guards
  case object TelekinesisGuard extends Guards
  case object ThornGuard extends Guards
  case object UndeadGuard extends Guards
  case object VulnerabilityGuard extends Guards

}
