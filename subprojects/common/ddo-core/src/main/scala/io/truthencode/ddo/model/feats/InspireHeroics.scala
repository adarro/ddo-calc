/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: InspireHeroics.scala
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
 * [[https://ddowiki.com/page/Inspire_Heroics Inspire Heroics]] Level: Bard 15 Perform: 18 ranks*
 * Target: Target of Bard's Bardic Inspiration Base Duration: Song Description: Gives a +4 Music
 * Bonus to Armor Class, +4% Music Bonus to Dodge, and a +4 Music bonus to all saves. Notes:
 * Prerequisite for Epic Feat Inspire Excellence *Only the base number of Perform ranks bought with
 * skill points count toward the requirement to be able to play this song. (Skill bonuses do not
 * count.) Created by adarr on 4/5/2017.
 * @todo
 *   Verify this was not conglomerated into Bardic Inspiration
 */
protected[feats] trait InspireHeroics
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 15))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 15))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 18))
}
