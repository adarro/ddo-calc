/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GreaterRuin.scala
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

import io.truthencode.ddo.activation.{OnSpellCastEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.spells.SpellBookImpl
import io.truthencode.ddo.support.requisite.{FreeFeat, RequiresAllOfFeat, RequiresCharacterLevel}

import java.time.Duration

/**
 * Created by adarr on 4/3/2017. [[https://ddowiki.com/page/Greater_Ruin Greater Ruin]] Name:
 * Greater Ruin School: Transmutation (Force) Special: Epic Feats, level 30 Components: None Spell
 * Point Cost: 150 Metamagic: Empower, Maximize, Quicken Target: Foe Range: Standard Duration:
 * Instantaneous Saving Throw: No Spell Resistance: No Cooldown: 15 seconds
 *
 * Deals 1000 untyped damage to a single enemy. (No saving throw.)
 *
 * @note
 *   Force Spell Power applies to this spell. Using metamagic feats increases the cost. Taking the
 *   Greater Ruin feat also grants you +140 maximum spell points. Has a separate cool-down timer
 *   from Ruin.
 */
protected[feats] trait GreaterRuin
  extends FreeFeat with TriggeredActivationImpl with SpellFeats with SpellBookImpl
  with OnSpellCastEvent with Passive with RequiresCharacterLevel with RequiresAllOfFeat {
  self: EpicFeat =>

  final override val requireCharacterLevel: Int = 30

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(15))

  override def spellIds: Set[String] = super.spellIds + entryName
  override def allOfFeats: Seq[Feat] = List(EpicFeat.Ruin)
}
