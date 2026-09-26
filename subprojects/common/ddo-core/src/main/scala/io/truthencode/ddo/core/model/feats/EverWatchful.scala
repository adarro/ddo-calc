/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EverWatchful.scala
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
import io.truthencode.ddo.model.religions.Helm
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * Created by adarr on 4/7/2017. [[https://ddowiki.com/page/Ever_Watchful Ever Watchful]] You are a
 * devoted follower of Helm, and your faith has been rewarded. Activate this ability to draw upon
 * the vigilance of Helm for 24 seconds plus 6 seconds per Religious Lore feat you have.
 *
 * In this state, you gain True Seeing, +5 Sacred bonus to Spot and Search, +4 Wisdom, +5 to
 * Fortitude saves, and PRR equal to the number of Religious Lore feats you have.
 */
trait EverWatchful
  extends FeatRequisiteImpl with TriggeredActivationImpl with ForgottenRealmsReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Helm with HelmFeatBase with ActiveFeat
  with AtWillEvent {
  self: DeityFeat =>

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfHelm)

}
