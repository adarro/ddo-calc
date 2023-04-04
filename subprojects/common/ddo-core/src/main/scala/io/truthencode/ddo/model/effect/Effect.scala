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

import io.truthencode.ddo.model.effect.features.Features

/**
 * Base trait representing some effect.
 *
 * Effects can be anything from increasing stats or abilities to granting feats etc.
 */
trait Effect {
  self: Features =>
}

trait EffectImpl extends Effect {
  self: Features =>
}

trait EffectList {
  val effects: List[Effect]
}

/**
 * Adds or reduces a given effect
 *
 * i.e. Adds 3 to STR or 2 to charisma skills etc.
 */
trait AugmentEffect[T] extends Effect {
  self: Features =>
  val amount: Int
  val effectType: AugmentEffectType
  val effect: T

}

trait NamedEffect extends Effect {
  self: Features =>
  val name: String
}

trait GrantEffect extends Effect {
  self: Features =>
}
