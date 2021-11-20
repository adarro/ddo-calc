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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Cleric, FavoredSoul, Paladin}
import io.truthencode.ddo.model.item.weapon.FavoredWeapon
import io.truthencode.ddo.support.requisite.RequiresAnyOfClass

/**
  * Base properties common to all follower Deity Feats
  */
trait FollowerBase
    extends Passive
    with FollowerOfLevel
    with RequiresAnyOfClass
    with ReligionFeatBase { self: FavoredWeapon =>
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List(Cleric, Paladin, FavoredSoul).map((_, 1))
}
