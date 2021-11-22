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
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Icon Feat Hamstring.png [[https://ddowiki.com/page/Hamstring Hamstring]] Hamstring Active - Special Attack This melee
 * special attack, when successful, reduces the target's movement rate by half for 12 seconds. Some creatures may be
 * immune to the hamstring effect.
 *
 * Sneak Attack
 */
protected[feats] trait Hamstring
  extends FeatRequisiteImpl with ActiveFeat with AtWillEvent with RequiresAllOfFeat with FighterBonusFeat {
  self: GeneralFeat =>
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))

  override val allOfFeats = List(ClassFeat.SneakAttack)
}
