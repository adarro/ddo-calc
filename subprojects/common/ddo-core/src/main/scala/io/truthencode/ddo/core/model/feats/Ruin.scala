/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Ruin.scala
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
import io.truthencode.ddo.support.requisite.{FreeFeat, RequiresCharacterLevel}

/**
 * [[https://ddowiki.com/page/Ruin_(feat) Ruin]] Name: Ruin School: Transmutation (Force) Level:
 * Epic Feats, level 27 Spell Point Cost: 75 Components: none Metamagic: Empower, Maximize, Quicken
 * Range: Standard Target: Foe Duration: Instantaneous Saving Throw: No Spell Resistance: No
 * Cooldown: 15 seconds Description: Deals 500 untyped damage to a single enemy. (No saving throw.)
 *
 * @note
 *   Force Spell Power applies to this spell. Using metamagic feats increases the cost.
 * @todo
 *   ERRATA: This is a Feat, but metamagic cost etc. affect it, so we likely treat it as a spell
 *   instead of SLA? Created by adarr on 4/3/2017.
 */
protected[feats] trait Ruin
  extends FreeFeat with TriggeredActivationImpl with SpellFeats with OnSpellCastEvent
  with RequiresCharacterLevel {
  self: EpicFeat =>

  final override val requireCharacterLevel: Int = 27

}
