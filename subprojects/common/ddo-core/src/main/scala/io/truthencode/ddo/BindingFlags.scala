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
/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.truthencode.ddo

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.BindingFlags.Unbound
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.matching.{WordMatchStrategies, WordMatchStrategy}

import scala.collection.immutable

/**
 * Stores the binding status of an item.
 *
 * As this is basically a database / analysis tool, it is primarily for identifying if and when any
 * binding may occur, which is useful when attempting to acquire said item.
 */
sealed abstract class BindingFlags(
  override val entryName: String,
  status: BindingStatus,
  event: BindingEvent)
  extends EnumEntry with Abbreviation with DefaultValue[BindingFlags] {
  override lazy val default: Option[Unbound.type] = BindingFlags.default
  val abbr: String = entryName
}

/**
 * Distinct value of binding options.
 */
object BindingFlags extends Enum[BindingFlags] with DefaultValue[BindingFlags] with LazyLogging {

  /**
   * Returns the default binding status (BindingFlags.Unbound)
   */
  override lazy val default: Option[Unbound.type] = Some(BindingFlags.Unbound)
  val values: immutable.IndexedSeq[BindingFlags] = findValues

  /**
   * @see
   *   [[io.truthencode.ddo.BindingFlags.fromWords(#Option[String])]]
   */
  def fromWords(words: String)(implicit strategy: WordMatchStrategy): Option[BindingFlags] =
    fromWords(Option(words))(strategy)

  /**
   * Attempts to mangle an Enumeration using free text.
   *
   * There is no extensive rule set, but DDO commonly uses acronyms such as BTC for Bound to
   * Account, so we are translating Binds|Bound to Account to BTA etc.
   *
   * @param words
   *   Optional String of text to translate.
   * @param strategy
   *   Implicit strategy used to determine matching constraints such as Upper / Lowercase / Preserve
   *   Case, full word etc.
   * @return
   *   [BindingFlags] from name or None if no data or no matching data is found.
   * @note
   *   First catches 'Unbound' Next Attempts to extract abbreviation using space or case to separate
   *   words
   *
   * Finally defaults to None
   *
   * If you have the exact words without spaces, it may be more performant to use the 'withName'
   * variant. NOTE: This enumeration is keyed by Acronym, so BTCoE will match, where 'Bound to
   * Character On Equip' will fail.
   */
  def fromWords(words: Option[String])(implicit
    strategy: WordMatchStrategy = WordMatchStrategies.TitleCaseWordStrategy)
    : Option[BindingFlags] = {
    words match {
      case Some(x) if x.equalsIgnoreCase(BindingFlags.Unbound.toFullWord) =>
        Some(BindingFlags.Unbound)
      case Some(x) =>
        x.wordsToAcronym match {
          case Some(abbr) =>
            BindingFlags.values.find { x => x.abbr.equalsIgnoreCase(abbr) } match {
              case Some(opt) => Some(opt)
              case _ =>
                logger.warn(s"No Matching Value found for $words, defaulting")
                None
            }
          case _ => None
        }
      case None => None
    }
  }

  case object Unbound extends BindingFlags("Unbound", BindingStatus.Unbound, BindingEvent.None) {
    def toFullWord: String = "Unbound"
  }

  case object BoundToAccountOnAcquire
    extends BindingFlags("BTAoA", BindingStatus.BindsToAccount, BindingEvent.OnAcquire) {
    def toFullWord: String = "Bound To Account On Acquire"
  }

  case object BoundToCharacterOnAcquire
    extends BindingFlags("BTCoA", BindingStatus.BindsToAccount, BindingEvent.OnAcquire) {
    def toFullWord: String = "Bound To Character On Acquire"
  }

  case object BoundToAccount
    extends BindingFlags("BTA", BindingStatus.BindsToAccount, BindingEvent.None) {
    def toFullWord: String = "Bound To Account"
  }

  case object BoundToCharacter
    extends BindingFlags("BTC", BindingStatus.BindsToCharacter, BindingEvent.None) {
    def toFullWord: String = "Bound To Character"
  }

  case object BoundToAccountOnEquip
    extends BindingFlags("BTAoE", BindingStatus.BindsToAccount, BindingEvent.OnEquip) {
    def toFullWord: String = "Bound To Account On Equip"
  }

  case object BoundToCharacterOnEquip
    extends BindingFlags("BTCoE", BindingStatus.BindsToAccount, BindingEvent.OnEquip) {
    def toFullWord: String = "Bound To Character On Equip"
  }

}
