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
package io.truthencode.ddo.model.item.HeldItem

import io.truthencode.ddo.Wearable
import io.truthencode.ddo.model.item.WearableItem
import io.truthencode.ddo.support.slots.WearLocation

/**
 * Includes Bucklers / and Tower shields
 */
trait Shield extends WearableItem with Wearable {

  /**
   * A bitmask that corresponds to one or more [io.truthencode.ddo.WearLocation] values.
   */
  override def allowedWearLocationFlags: Int = WearLocation.OffHand.bitValue
}
