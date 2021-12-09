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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Bard
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite._

/**
 * Created by adarr on 4/5/2017.
 */
trait InspireExcellence
  extends RequiresAllOfFeat with RequiresAllOfClass with RequiresAllOfSkill with RequiresCharacterLevel
  with ClassRestricted {
  override def allOfFeats: Seq[Feat] = List(ClassFeat.InspireHeroics)

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Bard, 15))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 20))

  override val requireCharacterLevel: Int = 21
}
