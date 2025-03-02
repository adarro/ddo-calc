/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpellBook.scala
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

/**
 * Holds known / available spells
 */
trait SpellBook {
  def knownSpells: Seq[Spell]

  /**
   * A list of functions that will return spells which are castable assuming they sufficient spell
   * points and level requirements etc. i.e. A level 3 wizard may have Power Word: Kill inscribed
   * but will not be able to cast it until they reach the appropriate level.
   * @return
   */
  def findAvailableSpells: Seq[(String) => Option[Spell]]

  /**
   * A list of string spell id's that will be loaded into the spellbook if found.
   * @return
   */
  def spellIds: Set[String]

  /**
   * Loads the spells by invoking
   * [[io.truthencode.ddo.model.spells.SpellBook#findAvailableSpells()]] on each of the
   * [[io.truthencode.ddo.model.spells.SpellBook#spellIds()]]
   * @return
   */
  def loadFromIds: Seq[Option[Spell]] = {
    for
      s <- spellIds.toSeq
      fn <- findAvailableSpells
    yield (fn(s))
  }

}

trait SpellBookImpl extends SpellBook {
  override def knownSpells: Seq[Spell] = IndexedSeq()

  override def findAvailableSpells: Seq[String => Option[Spell]] = {
    val seq = spellIds.toSeq
    val fn = Spell.ls(fn = Spell.withNameOption, seq*)
    IndexedSeq() ++ fn
  }

  override def spellIds: Set[String] = Set()

}
