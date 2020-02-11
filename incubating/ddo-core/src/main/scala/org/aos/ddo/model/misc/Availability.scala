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
package org.aos.ddo.model.misc

import scala.collection.immutable.HashSet

/**
  * Created by adarr on 1/28/2017.
  */
sealed trait Availability {
  def availabilityLevels: Set[AvailabilityLevel] = new HashSet[AvailabilityLevel]()

  def isFreeToPlay: Boolean = availabilityLevels.contains(AvailabilityLevel.FreeToPlay) || availabilityLevels.contains(AvailabilityLevel.Favor)

  def isPremiumFeature: Boolean = availabilityLevels.contains(AvailabilityLevel.Premium)

}

/**
  * Feature is available to anyone
  */
trait FreeToPlayFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.FreeToPlay
}

trait FavorFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Favor
}
/**
  * Feature is available once any purchase has been made
  */
trait PremiumFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Premium
}

/**
  * Feature is available only via direct purchase
  */
trait IconicFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Iconic
}

/**
  * Feature is available with active VIP subscription
  */
trait VIPFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.VIP
}
