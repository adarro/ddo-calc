/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

/** Represents a location where an item is.
  */
sealed trait InventorySection extends InventorySection.Value with NoDefault[InventorySection.Value]
object InventorySection extends Enum[InventorySection] {
  case object ActiveInventory extends InventorySection
  case object CharacterBank extends InventorySection
  case object SharedBank extends InventorySection
  /** No current direct implementation plans for auction,
    * just a place holder for the code.
    */
  case object Auction extends InventorySection
  case object Eqquiped extends InventorySection
  val values = List(ActiveInventory, CharacterBank, SharedBank, Auction, Eqquiped)
}
