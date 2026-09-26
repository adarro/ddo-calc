/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: OnatarsForge.scala
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
import io.truthencode.ddo.model.religions.Onatar
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Onatar%27s_Forge Onatar's Forge]] Cooldown: 10 minutes Usage: Active
 * Prerequisite: Level 6: Cleric, Favored Soul, Paladin; Follower of Onatar Description You are a
 * devoted follower of Onatar, and your faith has been rewarded. Activate this ability to channel
 * the power of Onatar's mighty forge for 24 seconds plus 6 seconds per Religious Lore feat you
 * have.
 *
 * In this state, you gain +3 to tactical feat DCs, +10 Melee Power, +10 Repair Spell Power, +10
 * Rust Spell Power, and +10 Fire Spell Power. If you are a Warforged or otherwise already benefit
 * from Repair, you gain +30 Repair Amplification. Created by adarr on 4/7/2017.
 */
trait OnatarsForge
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Onatar with OnatarFeatBase with ActiveFeat
  with AtWillEvent {
  self: DeityFeat =>

  override def displayText: String = "Onatar's Forge"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfOnatar)

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))
}
