/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *  http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import enumeratum.{ Enum ⇒ SmartEnum }

sealed abstract class Attributes(val abbr: String) extends enumeratum.EnumEntry with Abbreviation
object Attributes extends SmartEnum[Attributes] {
  case object Strength extends Attributes("STR") { def toFullWord(): String = "Strength" }
  case object Dexterity extends Attributes("DEX") { def toFullWord(): String = "Dexterity" }
  case object Intelligence extends Attributes("INT") { def toFullWord(): String = "Intelligence" }
  case object Wisdom extends Attributes("WIS") { def toFullWord(): String = "Wisdom" }
  case object Constitution extends Attributes("CON") { def toFullWord(): String = "Constitution" }
  case object Charisma extends Attributes("CHA") { def toFullWord(): String = "Charisma" }
  val values = findValues
}
