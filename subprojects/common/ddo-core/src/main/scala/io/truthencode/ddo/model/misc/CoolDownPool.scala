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
package io.truthencode.ddo.model.misc

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
 * Labels shared cooldown pools / timers
 */
sealed trait CoolDownPool extends EnumEntry with PoolId

object CoolDownPool extends Enum[CoolDownPool] {
  override def values: immutable.IndexedSeq[CoolDownPool] = findValues

  /**
   * Several Ranged Events use this pool. * ManyShot * Multitude of Missiles
   */
  case object ManyShot extends CoolDownPool {

    /**
     * Used to group shared timer resources. It is strongly recommended to use one of the values in
     * [[io.truthencode.ddo.model.misc.CoolDownPool]]
     */
    override val coolDownPoolId: String = PoolManyShot
  }
  case object Cleave extends CoolDownPool {

    /**
     * Used to group shared timer resources. It is strongly recommended to use one of the values in
     * [[io.truthencode.ddo.model.misc.CoolDownPool]]
     */
    override val coolDownPoolId: String = PoolCleave
  }
  case object GreatCleave extends CoolDownPool {

    /**
     * Used to group shared timer resources. It is strongly recommended to use one of the values in
     * [[io.truthencode.ddo.model.misc.CoolDownPool]]
     */
    override val coolDownPoolId: String = PoolGreatCleave
  }
}
