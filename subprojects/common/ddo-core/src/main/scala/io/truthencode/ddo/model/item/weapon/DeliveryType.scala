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
package io.truthencode.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault

/**
 * used to denote the Delivery method (thrown, launched, hand-held) of a weapon.
 */
sealed trait DeliveryType extends EnumEntry with NoDefault[DeliveryType]

/**
 * Enum to constrain allowed Weapon Types
 */
object DeliveryType extends Enum[DeliveryType] {

  override val values = findValues

  /**
   * Launches projectiles
   *
   * This includes most bows, crossbows etc.
   */
  case object Ranged extends DeliveryType

  /**
   * Close combat
   *
   * Includes swords, daggers, clubs etc
   */
  case object Melee extends DeliveryType

  /**
   * Weapons that can be thrown
   *
   * Ninja stars, throwing knives and hammers etc
   */
  case object Thrown extends DeliveryType

  /**
   * Weapons that deal damage in multiple or otherwise unique ways.
   */
  case object Special extends DeliveryType
}
