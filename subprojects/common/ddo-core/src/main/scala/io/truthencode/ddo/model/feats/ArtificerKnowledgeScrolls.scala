/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ArtificerKnowledgeScrolls.scala
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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Artificer
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
 * Created by adarr on 2/16/2017. [[https://ddowiki.com/page/Artificer_Knowledge]] This feat grants
 * you benefits based on your Artificer or Alchemist level.
 *
 * Scrolls: Artificer level 1. Grants a +2 to UMD checks related to scroll use, and all scrolls used
 * by the Artificer have their caster levels increased by 1. This caster level bonus increases by 1
 * at Artificer levels 4, 7, 10, and 13, and is capped by the Artificer's Intelligence Bonus. Bug:
 * Currently Artificer Knowledge's UMD check bonus won't work correctly. "Chance to activate" on
 * item description include this feat bonus, but feat bonus does not affect when you use scroll. So
 * if you have this feat and description says 100%, then there is 10% chance to you will fail to
 * activate. The chance to activate does actually increase as it's supposed to. What actually
 * happens is the UMD requirement is lowered by 2. The feat then erroneously counts the knowledge
 * feat for UMD bonus (creating a false bonus of 4) that shows up on the tooltip but doesn't
 * actually happen when using the item. The best thing to do is keep track of your UMD and measure
 * it against the item's stated UMD requirement
 */
protected[feats] trait ArtificerKnowledgeScrolls
  extends FeatRequisiteImpl with ArtificerKnowledgePrefix with Passive with GrantsToClass
  with RequiresAnyOfClass {
  self: ClassFeat =>
  private lazy val levels = List(1, 4, 7, 10, 13)
// This could be written in a simple map, but I am unsure ATM if Alchemist get it to.  And it's easier to simply add / remove them.
  private lazy val classLevels = for
    c <- List(Artificer) // Possibly Alchemist?
    l <- levels
  yield (c, l)
  // Affects Alchemists and Artificers
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = classLevels

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = classLevels
}
