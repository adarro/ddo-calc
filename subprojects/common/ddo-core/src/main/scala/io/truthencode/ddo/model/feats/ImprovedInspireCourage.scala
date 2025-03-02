/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ImprovedInspireCourage.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Bard
import io.truthencode.ddo.model.misc.BardSongCoolDown
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite.*

/**
 * [[https://ddowiki.com/page/Improved_Inspire_Courage Improved Inspire Courage]] Level: Bard 8
 * Perform: 3 ranks* Target: Bard and all nearby allies Base Duration: 4 minutes Song Description:
 * Gives a +2 (total) morale bonus to attack rolls, damage rolls, and saving throws versus fear. At
 * Bard level 14, these bonuses rise to +3, and at Bard level 20 they rise to +4. Notes: Each of
 * these bonuses can be raised through Warchanter enhancements (maximum +3) and the Fatesinger epic
 * destiny. The song's duration can be raised through Spellsinger enhancements (+60% lingering song,
 * +20% core ability), Fatesinger epic destiny, and Lasting Inspiration epic feat. *Only the base
 * number of Perform ranks bought with skill points count toward the requirement to be able to play
 * this song. (Skill bonuses do not count.) See also Inspire Courage Created by adarr on 4/5/2017.
 */
protected[feats] trait ImprovedInspireCourage
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 8))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List(8, 14, 20).map((Bard, _))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 3))
}
