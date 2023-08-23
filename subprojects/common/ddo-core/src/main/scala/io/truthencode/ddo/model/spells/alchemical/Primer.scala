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
package io.truthencode.ddo.model.spells.alchemical

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

// TODO: Add search Prefix to Primer
sealed trait Primer extends EnumEntry

/**
 * Catalyst for Pyrite and Orchidium Reactions
 */
trait Crimsonite extends Primer

/**
 * Catalyst for Pyrite and Verdanite Reactions
 */
trait Gildleaf extends Primer

/**
 * Catalyst for Verdanite and Orchidium Reactions
 */
trait Ceruleite extends Primer

object Primer extends Enum[Primer] {
  override def values: immutable.IndexedSeq[Primer] = findValues
  case object Crimsonite extends Primer with Crimsonite
  case object Gildleaf extends Primer with Gildleaf
  case object Ceruleite extends Primer with Ceruleite
}
