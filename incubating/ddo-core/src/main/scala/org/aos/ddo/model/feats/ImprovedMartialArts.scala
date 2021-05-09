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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.attribute.Attribute.Wisdom
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.model.item.weapon.WeaponClass
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 4/6/2017.
  */
trait ImprovedMartialArts
    extends FeatRequisiteImpl
    with FreeFeat
    with RequiresAllOfFeat
    with ClassRequisiteImpl
    with RequiresAllOfClass
    with RequiresAttribute
    with ClassRestricted { self: EpicFeat =>
  override def allOfClass: immutable.Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 12))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Wisdom, 32))

  override def allOfFeats: Seq[Feat] =
    List(GeneralFeat.ImprovedCritical(WeaponClass.Bludgeon))
}
