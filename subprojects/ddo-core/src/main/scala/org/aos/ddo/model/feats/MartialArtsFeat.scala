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

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{Inclusion, Requisite, SelectableToClass}

/**
  * Represents bonus feats selectable to certain martial classes at given levels.
  * see [[https://ddowiki.com/page/Martial_arts_feats]]
  */
trait MartialArtsFeat extends SelectableToClass with BonusSelectableFeat with BonusSelectableFeatImpl {
  self: Feat with FeatType with Requisite with Inclusion =>
  private[this] val myCharClass: CharacterClass = CharacterClass.Monk

  abstract override def bonusCharacterClass: Seq[CharacterClass] = super.bonusCharacterClass :+ myCharClass
  override val levels: Set[Int] = Set(1, 2, 6)

  abstract override def bonusSelectableToClass: Seq[(CharacterClass, Int)] = {

    val cc: Set[(CharacterClass, Int)] = for {l <- levels} yield (myCharClass, l)
    super.bonusSelectableToClass ++ cc.toSeq

  }

}
