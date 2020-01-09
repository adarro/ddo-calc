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
import org.aos.ddo.model.classes.HeroicCharacterClass.Monk
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * [[http://ddowiki.com/page/Ki_Strike:_Magic]]
  * All of your unarmed melee attacks are empowered with ki and are considered magical for damage reduction purposes.
  */
protected[feats] trait KiStrikeMagic
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with KiStrikePrefix
    with RequiresAllOfClass { self: ClassFeat =>

  override protected def nameSource: String = "Magic"

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Monk, 4))
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Monk, 4))
}
