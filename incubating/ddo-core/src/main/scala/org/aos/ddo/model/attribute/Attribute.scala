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

package org.aos.ddo.model.attribute

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.Abbreviation
import org.aos.ddo.support.SearchPrefix

/**
  * Created by adarr on 2/3/2017.
  */
sealed trait Attribute extends EnumEntry with Abbreviation

object Attribute extends Enum[Attribute] with SearchPrefix {

  case object Strength extends Strength

  case object Dexterity extends Dexterity

  case object Intelligence extends Intelligence

  case object Wisdom extends Wisdom

  case object Constitution extends Constitution

  case object Charisma extends Charisma

  val values = findValues

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "Attribute"
}

protected trait Strength extends Attribute {
  override val abbr: String = "STR"

  def toFullWord: String = "Strength"
}

trait Dexterity extends Attribute {
  override val abbr: String = "DEX"

  def toFullWord: String = "Dexterity"
}

trait Constitution extends Attribute {
  override val abbr: String = "CON"

  def toFullWord: String = "Constitution"
}

trait Intelligence extends Attribute {
  override val abbr: String = "INT"

  def toFullWord: String = "Intelligence"
}

trait Wisdom extends Attribute {
  override val abbr: String = "WIS"

  def toFullWord: String = "Wisdom"
}

trait Charisma extends Attribute {
  override val abbr: String = "CHA"

  def toFullWord: String = "Charisma"
}