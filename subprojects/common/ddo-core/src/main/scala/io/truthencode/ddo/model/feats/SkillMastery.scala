/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Barbarian, Bard, Rogue}
import io.truthencode.ddo.support.requisite.{ClassRequisite, FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
  * A rogue with this ability gains +1 to all skills. This ability may be taken multiple times.
  * Notes
  * This ability may be taken multiple times.
  */
protected[feats] trait SkillMastery
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = rogueOptionMatrix

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = List((Rogue,10),(Bard,18),(Barbarian,12))
}
