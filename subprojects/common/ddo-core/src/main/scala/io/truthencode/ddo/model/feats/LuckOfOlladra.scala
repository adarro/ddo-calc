/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: LuckOfOlladra.scala
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
import io.truthencode.ddo.model.religions.Olladra
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Luck_of_Olladra Luck of Olladra]] Cooldown: 10 minutes Usage: Active
 * Prerequisite: Level 6: Cleric, Favored Soul, Paladin, Follower of Olladra Description Luck of
 * Olladra: You are a devoted follower of Olladra, and your faith has been rewarded. Activate this
 * ability to heal yourself or an ally to full health and bestow +1d6 Luck bonus to each Ability
 * Score, lasting 24 seconds plus 6 seconds per Religious Lore feat you have. Created by adarr on
 * 4/7/2017.
 * @note
 *   Bug: Luck of Olladra can only be used on yourself.
 *   [[https://www.ddo.com/forums/showthread.php/498539 Forum Discussion Link]]
 */
trait LuckOfOlladra
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Olladra with OlladraFeatBase with ActiveFeat
  with AtWillEvent {
  self: DeityFeat =>

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfOlladra)

}
