/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Reaction.scala
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

// TODO: Add search prefix for Reaction
sealed trait Reaction extends EnumEntry

/**
 * Reaction affects Elemental spells (Fire, Cold, Electric,Acid and poison)
 */
trait Pyrite extends Reaction

/**
 * Reaction affects Positive / Negative spells and Transmutation / Conjuration DC's
 */
trait Verudite extends Reaction

/**
 * Reaction affects PRR and Elemental Resistances
 */
trait Orchidium extends Reaction

object Reaction extends Enum[Reaction] {
  override def values: immutable.IndexedSeq[Reaction] = findValues
  case object Pyrite extends Pyrite, Reaction
  case object Verudite extends Verudite, Reaction
  case object Orchidium extends Orchidium, Reaction
}
