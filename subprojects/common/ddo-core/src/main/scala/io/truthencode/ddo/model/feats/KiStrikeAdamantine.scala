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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * [[http://ddowiki.com/page/Ki_Strike:_Magic]]
  * All of your unarmed melee attacks are empowered with ki and are considered magical for damage reduction purposes.
  */
protected[feats] trait KiStrikeAdamantine
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with KiStrikePrefix
    with RequiresAllOfClass { self: ClassFeat =>

  override protected def nameSource: String = "Adamantine"

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Monk, 16))
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Monk, 16))
}
