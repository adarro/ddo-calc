/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: MassSuggestion.scala
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
 * [[https://ddowiki.com/page/Mass_Suggestion_(song) Mass Suggestion (song)]] Level: Bard 18
 * Perform: 21 ranks* Target: Multiple enemies affected by Fascinate Base Duration: 30 seconds
 * Saving Throw: Will negates, 10 + ½ bard level + Cha modifier Song Description: This song charms
 * multiple foes that are currently under the effects of the bardic Fascinate song.
 * @note
 *   This song does not work on enemies that have been mesmerized by any other bardic song, such as
 *   Music of the Dead, Music of the Makers, or Enthrallment. This song benefits from effects that
 *   increase song duration. Unlike spell version, can charm any number of enemies within range if
 *   they are fascinated. Likewise, Negative Level, charm effect expires quickly on Epic Hard and
 *   higher difficulty (10~15 seconds). *Only the base number of Perform ranks bought with skill
 *   points count toward the requirement to be able to play this song. (Skill bonuses do not count.)
 *   Created by adarr on 4/5/2017.
 */
protected[feats] trait MassSuggestion
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 18))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 18))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 21))
}
