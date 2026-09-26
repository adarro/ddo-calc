/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SilverFlameExorcism.scala
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

import io.truthencode.ddo.activation.{OnSpellLikeAbilityEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.religions.SilverFlame
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Silver_Flame_Exorcism Silver Flame Exorcism]] You are a devoted
 * follower of the Silver Flame, and your faith has been rewarded. Activate this ability to attempt
 * to exorcise an extraplanar creature, which is entirely consumed in holy fire on a failed Will
 * save or savagely burned by the light of the Silver Flame. A successful Fortitude save reduces the
 * damage to half. The Save DC for this ability is 10 + Cleric Level + Charisma Modifier. Cooldown:
 * 10 minutes. Created by adarr on 4/7/2017.
 * @todo
 *   Add difficulty check
 */
trait SilverFlameExorcism
  extends FeatRequisiteImpl with TriggeredActivationImpl with EberronReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with SilverFlame with TheSilverFlameFeatBase
  with ActiveFeat with OnSpellLikeAbilityEvent { self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheSilverFlame)

  override def coolDown: Option[Duration] = Some(Duration.ofMinutes(10))
}
