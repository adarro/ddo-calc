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

import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * Icon Feat Stunning Blow.png [[https://ddowiki.com/page/Stunning_Blow Stunning Blow]] Active - Special Attack This
 * feat has a chance to stun the target for 6 seconds if it fails a DC (10 + Str mod) Fortitude save. Some creatures may
 * be immune to the stunning effect.
 */
protected[feats] trait StunningBlow
  extends FeatRequisiteImpl with ActiveFeat with FreeFeat with Tactical with FighterBonusFeat {
  self: GeneralFeat =>
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))
}
