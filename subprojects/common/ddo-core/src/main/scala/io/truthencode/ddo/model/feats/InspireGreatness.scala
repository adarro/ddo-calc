/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: InspireGreatness.scala
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
 * [[https://ddowiki.com/page/Inspire_Greatness Inspire Greatness]] Level: Bard 9 Perform: 12 ranks*
 * Target: Bard and all nearby allies Base Duration: Song Description: Allies and yourself benefit
 * from +20 Temporary Hit Points, +3 Physical Resistance Rating, an +10 Healing Amplification as
 * long as you are not incapacitated, and they are within the Ballad. Notes: This song now included
 * in your Bardic Ballad. *Only the base number of Perform ranks bought with skill points count
 * toward the requirement to be able to play this song. (Skill bonuses do not count.) Created by
 * adarr on 4/5/2017.
 * @todo
 *   Add not incapacitated flag
 */
protected[feats] trait InspireGreatness
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 9))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 9))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 12))
}
