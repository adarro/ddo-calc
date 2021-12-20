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

/**
 * Specifies the method of damage delivery.
 */
trait DefaultDeliveryMethod {
  val defaultDeliveryType: DeliveryType
}

/**
 * Includes hand-to-hand, non-ranged weapons such as clubs, swords etc.
 */
trait MeleeDamage extends DefaultDeliveryMethod {
  override val defaultDeliveryType = DeliveryType.Melee
}

/**
 * Represents weapon that launch projectiles such as Bows and Crossbows.
 */
trait RangeDamage extends DefaultDeliveryMethod {
  override val defaultDeliveryType = DeliveryType.Ranged
}

/**
 * Represents weapons that can be thrown such as throwing daggers, throwing hammers and throwing
 * stars.
 */
trait ThrownDamage extends DefaultDeliveryMethod {
  override val defaultDeliveryType = DeliveryType.Thrown
}

/**
 * This trait indicates Damage is delivered in a non-standard way and may require some custom
 * processing.
 */
trait SpecialDamage extends DefaultDeliveryMethod
