/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Fascinate.scala
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
 * Created by adarr on 4/5/2017. [[https://ddowiki.com/page/Fascinate Fascinate]] Song Description:
 * This song fascinates most nearby living (not oozes, undead or constructs, unless core spellsinger
 * enhancement have been acquired) enemies, causing them to cease their activities until they're
 * damaged or the effect ends. Fascinated enemies are also vulnerable to charming by Suggestion Song
 * and Mass Suggestion Song.
 * @note
 *   This song takes about 5 seconds of playing before the effect applies, and it affects enemies
 *   near the Bard at that time, not necessarily those that were close when the song started. This
 *   song benefits from enhancements that increase song duration. Fascinate is considered a
 *   mind-affecting ability; thus, mindless enemies or those immune to mind effecting abilities are
 *   immune, like vermin (scorpions and spiders). Music of the Spider Queen will allow this song to
 *   work on vermin. Orange, Red and purple named enemies are immune. In-game description displays
 *   an inaccurate duration. See this article's talk page for confirmation on duration with
 *   examples. *Only the base number of Perform ranks bought with skill points count toward the
 *   requirement to be able to play this song. (Skill bonuses do not count.)
 */
protected[feats] trait Fascinate
  extends SkillRequisiteImpl with TriggeredActivationImpl with RequiresAllOfSkill
  with ClassRequisiteImpl with RequiresAllOfClass with GrantsToClass with ActiveFeat
  with OnSongPlayedEvent with BardSongCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 1))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 3))
}
