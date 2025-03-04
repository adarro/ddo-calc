/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: UnyieldingSovereignty.scala
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
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.model.religions.SovereignHost
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * [[https://ddowiki.com/page/Unyielding_Sovereignty Unyielding Sovereignty]] Usage: Active
 * Prerequisite: Level 6: Cleric, Favored Soul, Paladin; Follower of the Sovereign Host Description
 * You are a devoted follower of the Sovereign Host, and your faith has been rewarded. Activate this
 * ability to fully heal hit point damage done to a targeted ally, remove ability damage, death
 * penalty effects, negative levels, and the conditions blinded, confused, dazed, dazzled, deafened,
 * diseased, exhausted, fatigued, feebleminded, insanity, nauseated, poisoned, and stunned.
 *
 * @todo
 *   check for times per rest etc Created by adarr on 4/7/2017.
 */
trait UnyieldingSovereignty
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with SovereignHost with TheSovereignHostFeatBase
  with ActiveFeat with AtWillEvent with DefaultCoolDown {
  self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheSovereignHost)

}
