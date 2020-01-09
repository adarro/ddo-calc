/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.Bard
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 4/5/2017.
  */
protected[feats] trait ImprovedInspireCourage
    extends SkillRequisiteImpl
    with RequiresAllOfSkill
    with ClassRequisiteImpl
    with RequiresAllOfClass
      with GrantsToClass
    with Active {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Bard, 8))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List(8,14,20).map((Bard,_))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 3))
}
