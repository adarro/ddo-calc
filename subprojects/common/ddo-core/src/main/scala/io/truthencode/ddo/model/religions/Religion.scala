/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Religion.scala
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
package io.truthencode.ddo.model.religions

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.item.weapon.{FavoredWeapon, WeaponCategory}
import io.truthencode.ddo.model.worlds.{Eberron, ForgottenRealms, HomeWorld}
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable.IndexedSeq

/**
 * Represents the Deities followed by player characters.
 */
sealed trait Religion extends EnumEntry with DisplayName with FriendlyDisplay {
  self: HomeWorld & FavoredWeapon =>
  def hasFavoredWeapon(fw: WeaponCategory): Boolean = fw == favoredWeapon
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

sealed trait EberronReligion extends Religion with Eberron {
  self: FavoredWeapon =>
}
sealed trait ForgottenRealmsReligion extends Religion with ForgottenRealms {
  self: FavoredWeapon =>
}

object Religion extends Enum[Religion] {
  override def values: IndexedSeq[Religion] = findValues

  // Eberron
  case object Aureon extends EberronReligion with Aureon

  case object BloodOfVol extends EberronReligion with BloodOfVol

  case object LordOfBlades extends EberronReligion with LordOfBlades

  case object Olladra extends EberronReligion with Olladra

  case object Onatar extends EberronReligion with Onatar

  case object SilverFlame extends EberronReligion with SilverFlame

  case object SovereignHost extends EberronReligion with SovereignHost

  case object UndyingCourt extends EberronReligion with UndyingCourt

  case object Vulkoor extends EberronReligion with Vulkoor

  // Forgotten Realms
  case object Amaunator extends ForgottenRealmsReligion with Amaunator

  case object Helm extends ForgottenRealmsReligion with Helm

  case object Silvanus extends ForgottenRealmsReligion with Silvanus
}
