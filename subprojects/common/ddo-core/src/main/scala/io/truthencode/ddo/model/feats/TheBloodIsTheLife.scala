/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TheBloodIsTheLife.scala
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
import io.truthencode.ddo.model.religions.BloodOfVol
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/The_Blood_is_The_Life The Blood is The Life]] Cooldown: 10 minutes
 * Usage: Active Prerequisite: Level 6: Cleric, Favored Soul, Paladin; Follower of the Blood of Vol
 * Description You are a devoted follower of The Blood of Vol, and your zeal has been rewarded.
 * Activate this ability to draw upon the power within your own blood for 24 seconds plus 6 seconds
 * per Religious Lore feat you have. In this state, you gain +4 Constitution, 100% Fortification,
 * +10 to Fortitude Saves, and PRR and MRR equal to the number of Religious Lore feats you have.
 * While in this state and have a Dagger in your main hand, you also gain Vampirism, stacking with
 * other sources of Vampirism. Created by adarr on 4/7/2017.
 */
trait TheBloodIsTheLife
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with BloodOfVol with TheBloodOfVolFeatBase
  with ActiveFeat with AtWillEvent { self: DeityFeat =>

  override def displayText: String = "The Blood is The Life"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheBloodOfVol)

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))
}
