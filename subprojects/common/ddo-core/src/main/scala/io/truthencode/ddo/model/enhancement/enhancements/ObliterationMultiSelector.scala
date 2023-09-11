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
package io.truthencode.ddo.model.enhancement.enhancements

import io.truthencode.ddo.model.enhancement.enhancements.classbased.BombardierTierFive
import io.truthencode.ddo.support.naming.{DisplayProperties, FriendlyDisplay, SLAPrefix}

import scala.collection.immutable

trait ObliterationMultiSelector
  extends BombardierTierFive with ClassEnhancementImpl with SLAPrefix with SubEnhancement
  with DisplayProperties with FriendlyDisplay with MultiSelectorKeyGeneratorImpl {
  override lazy val description: Option[String] = Some(
    s"Throw a vial that explodes with $element damage per Caster Level to all enemies nearby. (Max Caster Level 10)"
  )
  protected lazy val keys: immutable.Seq[String] =
    keyList.map(v => s"$prefix$v")

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 3

  def element: String

  override def displayText: String = withPrefix.getOrElse("") + nameSource

  /**
   * Some enhancements have multiple ranks. This is the cost for each rank. Older versions had
   * increasing costs which has been streamlined to a linear progression.
   *
   * @return
   */
  override def apCostPerRank: Int = 1
}
