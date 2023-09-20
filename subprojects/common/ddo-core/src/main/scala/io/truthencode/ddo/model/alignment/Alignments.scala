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
package io.truthencode.ddo.model.alignment

import enumeratum.{Enum => SmartEnum, EnumEntry}
import io.truthencode.ddo.model.alignment.LawAxis.{Chaotic, Lawful, Neutral}
import io.truthencode.ddo.model.alignment.MoralAxis.{Evil, Good}
import io.truthencode.ddo.support.SearchPrefix

/**
 * Represents the dual axis Alignments
 *
 * @param law
 *   The range between Lawful to Chaotic
 * @param moral
 *   The moral range between Good and evil
 */
sealed class Alignments(
  override val law: _root_.io.truthencode.ddo.model.alignment.LawAxis,
  override val moral: MoralAxis
) extends EnumEntry with AlignmentCombination

object Alignments extends SmartEnum[Alignments] with SearchPrefix {

  val values = findValues

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Alignment"

  case object ChaoticGood extends Alignments(Chaotic, Good)

  case object ChaoticNeutral extends Alignments(Chaotic, MoralAxis.Neutral)

  case object ChaoticEvil extends Alignments(Chaotic, Evil)

  case object NeutralGood extends Alignments(Neutral, Good)

  case object TrueNeutral extends Alignments(Neutral, MoralAxis.Neutral)

  case object NeutralEvil extends Alignments(Neutral, Evil)

  case object LawfulGood extends Alignments(Lawful, Good)

  case object LawfulNeutral extends Alignments(Lawful, MoralAxis.Neutral)

  case object LawfulEvil extends Alignments(Lawful, Evil)
}
