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
package io.truthencode.ddo.model.feats

import java.time.Duration

import io.truthencode.ddo.activation.OnToggleEvent
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * [[https://ddowiki.com/page/Attack Attack]] This is the standard attack for all characters. It can be toggled on to
 * attack repeatedly until your target is defeated, or activated once by right-clicking the mouse.
 * @note
 *   Currently set to No Cooldown, aside from potential advanced metrics, this shouldn't be an issue.
 */
protected[feats] trait Attack extends FeatRequisiteImpl with ActiveFeat with FreeFeat with Stance {
  self: GeneralFeat =>
  override def coolDown: Option[Duration] = None
}
