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
package io.truthencode.ddo.support.points

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.Abbreviation
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

/**
 * Points that are accumulated such as Hit Points or Spell Points
 */
sealed trait ExpendablePoints extends EnumEntry with DisplayName with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

/**
 * Represents the general health or Life. Players are incapacitated when this falls below zero and die if it falls too
 * far into the negative.
 */
trait HitPoints extends ExpendablePoints

/**
 * Used by spell casters to cast spells
 */
trait SpellPoints extends ExpendablePoints

/**
 * Energy used by monks to perform certain acts
 */
trait Ki extends ExpendablePoints

object ExpendablePoints extends Enum[ExpendablePoints] with SearchPrefix {

  /**
   * @inheritdoc
   */
  case object Ki extends Ki

  /**
   * @inheritdoc
   */
  case object HitPoints extends HitPoints with Abbreviation {

    /**
     * The short form of the word
     */
    override val abbr: String = "HP"

    /**
     * Expands the abbr to its full value
     */
    override def toFullWord: String = entryName
  }

  /**
   * @inheritdoc
   */
  case object SpellPoints extends SpellPoints with Abbreviation {

    /**
     * The short form of the word
     */
    override val abbr: String = "SP"

    /**
     * Expands the abbr to its full value
     */
    override def toFullWord: String = entryName
  }

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Points"

  override def values: immutable.IndexedSeq[ExpendablePoints] = findValues
}
