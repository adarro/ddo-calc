/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: InspireCourage.scala
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

import io.truthencode.ddo.activation.{OnSongPlayedEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.misc.BardSongCoolDown
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite.*

/**
 * [[https://ddowiki.com/page/Inspire_Courage Inspire Courage]] Level: Bard 1 Perform: 3 ranks*
 * Target: Bard and all nearby allies Base Duration: Passive aura Song Description: Gives a +1 music
 * bonus to attack rolls, damage rolls, and saving throws versus fear, as well as +3 universal Spell
 * Power. Notes: At Bard level 8, this song automatically becomes Improved Inspire Courage and these
 * bonuses rise to +2. At Bard level 14 they rise to +3, and at Bard level 20 they rise to +4. Each
 * of these bonuses can be raised through Warchanter enhancements (maximum +3, damage
 * +5[UnverifiedIcon tooltip.png]) and the Fatesinger epic destiny (damage +2, to-hit
 * +4[UnverifiedIcon tooltip.png]). Since Update 41, Inspire Courage is a part of the Bardic Ballad,
 * passive AoE aura automatically affecting nearby allies. Non-bards can access Inspire Courage via
 * Past Life: Bardic Dilettante or the Fatesinger epic destiny. These instances of Inspire Courage
 * are not part of a ballad but have to activated (sung) separately. *Only the base number of
 * Perform ranks bought with skill points count toward the requirement to be able to play this song.
 * (Skill bonuses do not count.) Created by adarr on 4/5/2017.
 */
protected[feats] trait InspireCourage
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 1))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 3))
}
