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
package org.aos.ddo.weapon

import org.aos.ddo.{ Enum, NoDefault }

/** handedness is used to determine a one handed, two handed or off hand equip.
  * DDOwiki lists bows as ranged
  */
sealed trait Handedness extends Handedness.Value with NoDefault[Handedness.Value]
object Handedness extends Enum[Handedness] {
  case object OneHand extends Handedness
  case object TwoHand extends Handedness
  case object OffHand extends Handedness
  override val values: List[org.aos.ddo.weapon.Handedness with Product with Serializable] = List(OneHand, TwoHand, OffHand)
}
