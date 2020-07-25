/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.model.misc.CoolDownPool.Cleave
import io.truthencode.ddo.model.misc.SharedCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
  * [[https://ddowiki.com/page/Cleave Cleave]]
  * Special Attack - Activate this ability to attack one or more enemies in an arc in front of you. This attack deals +1[W] damage.
  * Cleave has a better chance to hit more enemies at once than a basic attack.
  */
protected[feats] trait Cleave
    extends FeatRequisiteImpl
    with ActiveFeat
    with AtWillEvent
    with RequiresAllOfFeat
    with FighterBonusFeat
    with SharedCoolDown {
  self: GeneralFeat =>
  override val allOfFeats = List(GeneralFeat.PowerAttack)
  override lazy val anyOfFeats: Seq[GeneralFeat] = IndexedSeq.apply()
  override lazy val noneOfFeats: Seq[GeneralFeat] = IndexedSeq.apply()
  override val coolDownPoolId: String = Cleave.coolDownPoolId

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(5))
}
