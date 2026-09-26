/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BlessingOfSilvanus.scala
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

import io.truthencode.ddo.activation.{OnToggleEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.religions.Silvanus
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * Created by adarr on 4/7/2017.
 * [[https://ddowiki.com/page/Blessing_of_Silvanus Blessing of Silvanus]]
 *
 * You are a devoted follower of Silvanus, and your faith has been rewarded. Toggling this on gives
 * you Blessing of Silvanus: You permanently gain a +4 natural armor bonus to AC, and Mauls you
 * wield gain +2 to their Critical Threat Range.
 */
trait BlessingOfSilvanus
  extends FeatRequisiteImpl with TriggeredActivationImpl with ForgottenRealmsReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Silvanus with SilvanusFeatBase
  with ActiveFeat with OnToggleEvent {
  self: DeityFeat =>

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfSilvanus)

}
