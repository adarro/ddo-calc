/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: FinishingMoves.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * Created by adarr on 3/17/2017. [[https://ddowiki.com/page/Finishing_Moves Finishing Moves]]
 * Through careful use of their abilities, monks can unlock special finishing moves to perform
 * amazing effects. This button changes into various finishing moves as they become available.
 *
 * @note
 *   Since Update 33 all Finishing Moves deal +1[w] damage.
 */
trait FinishingMoves
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with AtWillEvent
  with GrantsToClass with RequiresAllOfClass with DefaultCoolDown {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 1))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 1))
}
