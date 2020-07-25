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
import io.truthencode.ddo.model.classes.HeroicCharacterClass._
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * Eldritch Blast deals 1d6 damage at level 1,
  *     and increases by +1d6 at Warlock level 3, 5, 7, 9, 11, 14, 17, and 20
  *     for a total of 9d6 at level 20. The base damage is Force,
  *     though enhancements can change this to Evil (will affect evil enemies) or Piercing.
  * The base Eldritch Blast scales with 130% spell power, but different shape or essences can change this percentage.
  */
protected[feats] trait PactFiend
    extends FeatRequisiteImpl
    with Passive
    with RequiresAllOfClass
    with GrantsToClass
    with PactPrefix { self: ClassFeat =>

  override protected def nameSource: String = "Fiend"

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Warlock, 1))
}
