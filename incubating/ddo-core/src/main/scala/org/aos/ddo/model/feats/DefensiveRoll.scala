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
import org.aos.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  GrantsToClass
}

/**
  * If you are at below 20% of your hitpoint maximum,
  *     each time you are struck with an attack there is a percentage chance equal to your Reflex save that the attack does half main damage,
  *     and its special effects are negated (as if you were blocking).
  */
protected[feats] trait DefensiveRoll
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = rogueOptionMatrix

}
