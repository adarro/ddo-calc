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
package org.aos.ddo.model.spells

import java.time.Duration

import org.aos.ddo.model.misc.CoolDown
import org.aos.ddo.model.spells.component.ComponentList

/**
  * Encapsulates the cost, duration, range etc of a given spell
  */
trait SpellInfo extends CoolDown {
  // caster level needs to be applied at source site

  // spell failure TBD by existence of somatic / verbal / concentration / armor spell failure etc
  /**
    * Amount of time, usually in seconds that must elapse before a given spell or ability can be used again
    */
  val coolDown: Option[Duration]

  /**
    * Determines if this spell is subject to a Resistance check
    */
  val spellResistance: Boolean

  // TBD spell result
  /**
    * List of applicable targets [[SpellTarget]]
    */
  val target: List[SpellTarget]

  /**
    * Available saving throws, if any
    */
  val savingThrow: List[SavingThrow]

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

  /**
    * List of required spell components
    *
    * @return
    */
  val components: List[ComponentList]
}


final case class CreateSpellInfo(
                                  override val coolDown: Option[Duration],
                                  override val savingThrow: List[SavingThrow],
                                  override val spellResistance: Boolean,
                                  override val target: List[SpellTarget],
                                  override val components: List[ComponentList],
                                  override val spellPoints: Int,
                                  override val hitPoints: Option[Int] = None) extends SpellInfo