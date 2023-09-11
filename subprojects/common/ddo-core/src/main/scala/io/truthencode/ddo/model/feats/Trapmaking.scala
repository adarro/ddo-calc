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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Artificer, Rogue}
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass,
  RequiresAnyOfFeat
}

/**
 * Created by adarr on 2/16/2017.
 */
protected[feats] trait Trapmaking
  extends FeatRequisiteImpl with Passive with GrantsToClass with RequiresAnyOfClass
  with RequiresAnyOfFeat {
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 4), (Rogue, 4))
// @todo Add Least DM of Making
  override def anyOfFeats: Seq[Feat] =
    List(GeneralFeat.NimbleFingers, GeneralFeat.SkillFocus(Skill.DisableDevice))
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 4), (Rogue, 4))
}
