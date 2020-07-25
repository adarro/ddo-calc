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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Barbarian
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Tireless_Rage Tireless Rage]]
  * Upon reaching level 17, a barbarian is no longer fatigued after raging.
  * Also, gain a stacking +2 Strength and Constitution Bonus along with a +1 Morale Bonus to Will Saving Throws while raging.
  */
protected[feats] trait TirelessRage
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Barbarian, 17))
}
