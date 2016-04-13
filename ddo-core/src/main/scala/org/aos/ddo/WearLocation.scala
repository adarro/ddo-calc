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

/** Constrains the places items can be used.
  */
sealed trait WearLocation extends WearLocation.Value with NoDefault[WearLocation.Value]
/** Distinct values for location slots.
  */
object WearLocation extends Enum[WearLocation] {
  case object Head extends WearLocation
  case object Neck extends WearLocation
  case object Back extends WearLocation
  case object Wrist extends WearLocation
  case object FirstFinger extends WearLocation
  case object SecondFinger extends WearLocation
  case object Body extends WearLocation
  case object Feet extends WearLocation
  case object Goggles extends WearLocation
  case object MainHand extends WearLocation
  /** OffHand holds shield, Orbs, rune arms etc. and will be unavailable when
    * using two handed weapons or bows.
    */
  case object OffHand extends WearLocation
  case object Trinket extends WearLocation
  case object HeadDecoration extends WearLocation
  case object BodyDecoration extends WearLocation
  case object Ammo extends WearLocation
  case object Quiver extends WearLocation
  val values = List(Head, Neck, Trinket, Back, Wrist, FirstFinger, SecondFinger, Body, Feet, Goggles, MainHand, OffHand,
    HeadDecoration, BodyDecoration, Ammo, Quiver)
}
