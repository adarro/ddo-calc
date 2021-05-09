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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  GrantsToClass
}

/**
  *A rogue with this ability can sneak attack opponents with such precision that his or her blows weaken and hamper them.
  * An opponent damaged by a sneak attack also takes 2 points of Strength damage.
  */
protected[feats] trait CripplingStrike
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = rogueOptionMatrix

}
