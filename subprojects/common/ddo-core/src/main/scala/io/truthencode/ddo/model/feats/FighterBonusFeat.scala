/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: FighterBonusFeat.scala
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
import io.truthencode.ddo.support.requisite.{Inclusion, Requisite, SelectableToClass}

trait FighterBonusFeat extends SelectableToClass with BonusSelectableToClassFeat {
  self: Feat & FeatType & Requisite & Inclusion =>
  override val levels: Set[Int] = Set(1) ++ (2 to 20 by 2).toSet
  private val myCharClass: HeroicCharacterClass = HeroicCharacterClass.Fighter

  abstract override def bonusCharacterClass: Seq[HeroicCharacterClass] =
    super.bonusCharacterClass :+ myCharClass

  abstract override def bonusSelectableToClass: Seq[(HeroicCharacterClass, Int)] = {

    val cc: Set[(HeroicCharacterClass, Int)] = for l <- levels yield (myCharClass, l)
    super.bonusSelectableToClass ++ cc.toSeq

  }
}
