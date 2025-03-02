/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AureonsInstruction.scala
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
import io.truthencode.ddo.model.religions.Aureon
import io.truthencode.ddo.support.naming.DisplayProperties
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * Created by adarr on 4/7/2017.
 * [[https://ddowiki.com/page/Aureon%27s_Instruction Aureon's Instruction]] You are a devoted
 * follower of Aureon, and your faith has been rewarded. Activate this ability to channel the power
 * of Aureon's wisdom for 24 seconds plus 6 seconds per Religious Lore feat you have.
 *
 * In this state, you gain +4 Wisdom, +3 to Spell Penetration, +2 to your effective level for Turn
 * Undead, +4 to maximum Hit Dice affected when using Turn Undead, and +4 to the total Hit Dice
 * affected by Turn Undead.
 */
trait AureonsInstruction
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Aureon with AureonFeatBase with ActiveFeat
  with AtWillEvent with DisplayProperties { self: DeityFeat =>

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAureon)

  override def displayText: String = "Aureon's Instruction"
}
