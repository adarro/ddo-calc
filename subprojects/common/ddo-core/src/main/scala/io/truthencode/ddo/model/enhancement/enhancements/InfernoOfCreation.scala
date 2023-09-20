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
import io.truthencode.ddo.support.requisite.{
  ClassEnhancementRequisiteImpl,
  RequiresAllOfClassEnhancement
}

trait InfernoOfCreation
  extends ClassEnhancementRequisiteImpl with BombardierTierFive with ClassEnhancementImpl
  with RequiresAllOfClassEnhancement {

  override lazy val description: Option[String] = Some(
    "When your Reaction is Pyrite, you gain +1 Burning Ambition die, and your harmful Crimsonite spells apply a stack of vulnerability to enemies that have at least 3 different elements of Burning Ambition.\nVulnerable: You take 1% more damage for 3 seconds. This effect stacks up to 20 times, and loses one stack on expiration."
  )

  /**
   * Some enhancements can be taken multiple times (generally up to three)
   */
  override val ranks: Int = 1

  override def allOfClassEnhancements: Seq[ClassEnhancement] =
    List(ClassEnhancement.BurningAmbition)

  /**
   * Some enhancements have multiple ranks. This is the cost for each rank. Older versions had
   * increasing costs which has been streamlined to a linear progression.
   *
   * @return
   */
  override def apCostPerRank: Int = 1
}
