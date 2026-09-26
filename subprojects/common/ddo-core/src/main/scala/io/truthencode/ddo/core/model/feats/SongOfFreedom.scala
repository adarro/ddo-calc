/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SongOfFreedom.scala
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
 * [[https://ddowiki.com/page/Song_of_Freedom Song of Freedom]] Level: Bard 12 Perform: 15 ranks*
 * Target: One nearby ally other than the Bard. Base Duration: Instantaneous Song Description: This
 * ability is equivalent to the Break Enchantment spell on one ally. *Only the base number of
 * Perform ranks bought with skill points count toward the requirement to be able to play this song.
 * (Skill bonuses do not count.) Created by adarr on 4/5/2017.
 */
protected[feats] trait SongOfFreedom
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 12))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 12))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 15))
}
