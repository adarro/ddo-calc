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
package io.truthencode.ddo.model.effect

trait Trigger {
  val triggerEvent: TriggerEvent
}

/**
 * Effect is passive and pervasive
 *
 * The effect is generally active provided you have the Feat / Enhancement or wearing the item
 * granting the effect.
 */
trait Passive extends Trigger {
  override val triggerEvent: TriggerEvent = TriggerEvent.Passive
}
