/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpellInfo.scala
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

import io.truthencode.ddo.model.misc.CoolDown
import io.truthencode.ddo.model.spells.component.ComponentList

import java.time.Duration

/**
 * Encapsulates the cost, duration, range etc. of a given spell
 */
trait SpellInfo extends CoolDown with SpellResistance {
  // caster level needs to be applied at source site

  // spell failure TBD by existence of somatic / verbal / concentration / armor spell failure etc
  /**
   * Amount of time, usually in seconds that must elapse before a given spell or ability can be used
   * again
   */
  val coolDown: Option[Duration]

  // TBD spell result
  /**
   * List of applicable targets [[SpellTarget]]
   */
  val target: List[SpellTarget]

  /**
   * Range / Area of effect such as Spherical AOE
   */
  val range: Range

  /**
   * Available saving throws, if any
   */
  val savingThrow: List[SavingThrow]

  /**
   * List of required spell components
   *
   * @return
   */
  val components: List[ComponentList]
  val spellResistance = this

  /**
   * Spell Point Cost
   *
   * @return
   */
  def spellPoints: Int

  /**
   * Hit Point Cost
   *
   * @return
   */
  def hitPoints: Option[Int] = None
}

final case class CreateSpellInfo(
  override val coolDown: Option[Duration],
  override val savingThrow: List[SavingThrow],
  override val sr: Option[Int],
  override val target: List[SpellTarget],
  override val components: List[ComponentList],
  override val spellPoints: Int,
  override val hitPoints: Option[Int] = None,
  override val range: Range)
  extends SpellInfo
