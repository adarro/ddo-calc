/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: VulkoorsAvatar.scala
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

import io.truthencode.ddo.activation.{OnSummon, TriggeredActivationImpl}
import io.truthencode.ddo.model.religions.Vulkoor
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Vulkoor%27s_Avatar Vulkoor's Avatar]] Cooldown: 10 minutes Usage:
 * Active Prerequisite: Level 6: Cleric, Favored Soul, Paladin; Follower of Vulkoor Description
 * Duration: 10 minutes
 *
 * You are a devoted follower of Vulkoor, and your faith has been rewarded. Activate this ability to
 * to ask Vulkoor to send one of his servants to destroy your enemies.
 *
 * @note
 *   Quick summon gesture CR 10 Drow Scorpion It does not scale with level. It prefers melee but
 *   casts spells if it cannot reach the enemy (Melf's Acid Arrow, Web, Poison). It never lands a
 *   blow on ghostly creatures. Created by adarr on 4/7/2017.
 */
trait VulkoorsAvatar
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Vulkoor with TheVulkoorFeatBase
  with ActiveFeat with OnSummon {
  self: DeityFeat =>

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))

  override def displayText: String = "Vulkoor's Avatar"

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfVulkoor)

}
