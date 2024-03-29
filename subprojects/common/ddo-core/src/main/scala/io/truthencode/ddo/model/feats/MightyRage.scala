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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Barbarian
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat, GrantsToClass}

/**
 * [[http://ddowiki.com/page/Greater_Rage Greater Rage]] The bonuses of the barbarian's rage
 * increase to a total of +6 strength, +6 constitution and +3 to will saving throws but the
 * barbarian still incurs a reduction of its Armor Class by 2.
 *
 * Fatigue penalties remain the same as regular Rage. Enhances the regular Barbarian Rage feat. +10
 * Physical Resistance Rating when wearing Medium armor. +10 Melee Power while the rage is active.
 */
protected[feats] trait MightyRage
  extends FeatRequisiteImpl with Passive with GrantsToClass with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Barbarian, 20))
}
