/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aos.ddo

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum,EnumEntry}
import org.aos.ddo.BindingFlags.Unbound
import org.aos.ddo.support.StringUtils.Extensions

/** Stores the binding status of an item.
  *
  * As this is basically a database / analysis tool, it is primarily for identifying if
  * and when any binding may occur, which is useful when attempting to acquire said item.
  */
sealed abstract class BindingFlags(
    override val entryName: String,
    status: BindingStatus,
    event: BindingEvent) extends EnumEntry with Abbreviation with DefaultValue[BindingFlags] {
  val abbr: String = entryName
  override lazy val default: Option[Unbound.type] = BindingFlags.default
}
/** Distinct value of binding options.
  */
object BindingFlags extends Enum[BindingFlags] with DefaultValue[BindingFlags] with LazyLogging {
  /** Attempts to mangle an Enumeration using free text.
    *
    * There is no extensive rule set, but DDO commonly using acronyms such as BTC,
    * so we are translating Binds|Bound to Account to BTA etc.
    *
    * @note
    * First catches 'Unbound'
    * Next Attempts to extract abbreviation using space or case to separate words
    *
    * Finally defaults to None
    *
    * If you have the exact words without spaces, it may be more performant to use the 'withName' variant.
    * NOTE: This enumeration is keyed by Acronym, so BTCoE will match, where 'Bound to Character On Equip' will fail.
    */
  def fromWords(words: Option[String]): Option[BindingFlags] = {
    words match {
      case Some(x) if x.equalsIgnoreCase(BindingFlags.Unbound.toFullWord) =>
        Some(BindingFlags.Unbound)
      case Some(x) => x.wordsToAcronym match {
        case Some(abbr) =>
          BindingFlags.values.find { x => x.abbr.equalsIgnoreCase(abbr) } match {
            case Some(opt) => Some(opt)
            case _ =>
              logger.warn(s"No Matching Value found for $words, defaulting")
              None
          }
        case _ => None
      }
    }
  }

  /** @see [[org.aos.ddo.BindingFlags.fromWords(#Option[String])]]
    */
  def fromWords(words: String): Option[BindingFlags] = fromWords(Option(words))

  /** Returns the default binding status (BindingFlags.Unbound)
    */
  override lazy val default: Option[Unbound.type] = Some(BindingFlags.Unbound)
  val values = findValues
  case object Unbound extends BindingFlags("Unbound", BindingStatus.Unbound, BindingEvent.None) {
    def toFullWord: String = "Unbound"
  }
  case object BoundToAccountOnAcquire extends BindingFlags("BTAoA", BindingStatus.BindsToAccount, BindingEvent.OnAcquire) {
    def toFullWord: String = "Bound To Account On Acquire"
  }
  case object BoundToCharacterOnAcquire extends BindingFlags("BTCoA", BindingStatus.BindsToAccount, BindingEvent.OnAcquire) {
    def toFullWord: String = "Bound To Character On Acquire"
  }
  case object BoundToAccount extends BindingFlags("BTA", BindingStatus.BindsToAccount, BindingEvent.None) {
    def toFullWord: String = "Bound To Account"
  }
  case object BoundToCharacter extends BindingFlags("BTC", BindingStatus.BindsToCharacter, BindingEvent.None) {
    def toFullWord: String = "Bound To Character"
  }
  case object BoundToAccountOnEquip extends BindingFlags("BTAoE", BindingStatus.BindsToAccount, BindingEvent.OnEquip) {
    def toFullWord: String = "Bound To Account On Equip"
  }
  case object BoundToCharacterOnEquip extends BindingFlags("BTCoE", BindingStatus.BindsToAccount, BindingEvent.OnEquip) {
    def toFullWord: String = "Bound To Character On Equip"
  }
}
