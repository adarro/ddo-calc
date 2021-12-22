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
package io.truthencode.ddo.model.spells

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
 * Keywords that describe what the spell affects.
 *
 * @note
 *   This is not the same thing as whether you have to aim the spell by highlighting something
 *   first. Some spells always affect an area around you no matter what you have highlighted. In
 *   many cases a spell will not hit its intended target, even if it's legal, due to intervening
 *   obstacles or other enemies. Some spells are cast at a default direction or target if your
 *   highlighted target is illegal.
 */
sealed trait SpellTarget extends EnumEntry

trait SingleTarget extends SpellTarget

object SpellTarget extends Enum[SpellTarget] {

  override def values: immutable.IndexedSeq[SpellTarget] = findValues

  /**
   * The one casting the spell
   */
  case object Self extends SingleTarget

  case object Friend extends SingleTarget

  /**
   * Any PC, NPC, or creature other than yourself that isn't hostile to you. This can include
   * hirelings, summoned creatures, friendly combative NPCs, and other players both in and outside
   * your party.
   */
  case object Ally extends SingleTarget

  /**
   * Any PC, NPC, or creature hostile to you.
   */
  case object Foe extends SingleTarget

  /**
   * The spell creates a stationary effect centered on its initial target (or at the spot along a
   * line to that target at the spell's maximum range, if that target is too far away). The effect
   * does not move with the target afterwards. Also used for spells that summon things to specify
   * where that thing is initially created.
   */
  case object Positional extends SpellTarget

  /**
   * The spell only affects targets in the direction of its casting.
   */
  case object Directional extends SpellTarget

  /**
   * The spell can target and break objects like crates or barrels.
   */
  case object Breakable extends SpellTarget
}
