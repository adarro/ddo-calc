/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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

import java.time.Duration

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * [[https://ddowiki.com/page/Combat_Expertise Combat Expertise]]
  * Cooldown: 30 seconds
  * Usage: Active, Toggled Stance
  * Prerequisite: Intelligence 13
  * Description
  * Defensive Combat Stance: While using Combat Expertise mode, you suffer -5 to your attack rolls but gain +10% feat bonus to Armor Class.
  * Spells have three times their normal cooldown when this mode is active.
  * Combat Expertise dispels and wards against all Rage effects.
  */
protected[feats] trait CombatExpertise
    extends FeatRequisiteImpl
    with ActiveFeat
    with RequiresAttribute
    with MartialArtsFeat
    with FighterBonusFeat
    with ArtificerBonusFeat
    with DefensiveCombatStance {
  self: GeneralFeat =>
  override val requiresAttribute = List((Attribute.Intelligence, 13))

    override def coolDown: Option[Duration] = Some(Duration.ofSeconds(30))
}
