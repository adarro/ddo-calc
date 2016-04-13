/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *   http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import enumeratum.{ EnumEntry, Enum ⇒ SmartEnum }

sealed trait AlignmentType
sealed trait LawAxis extends LawAxis.Value with AlignmentType with NoDefault[LawAxis.Value]
object LawAxis extends Enum[LawAxis] {
  case object Chaotic extends LawAxis
  case object Neutral extends LawAxis
  case object Lawful extends LawAxis
  val values = List(Chaotic, Neutral, Lawful)
}

sealed trait MoralAxis extends MoralAxis.Value with AlignmentType with NoDefault[MoralAxis.Value]
object MoralAxis extends Enum[MoralAxis] {
  case object Good extends MoralAxis
  case object Neutral extends MoralAxis
  case object Evil extends MoralAxis
  val values = List(Good, Neutral, Evil)
}

/** Represents the dual axis Alignments
  * @param law The range between Lawful to Chaotic
  * @param moral The moral range between Good and evil
  */
sealed class Alignments(law: LawAxis, moral: MoralAxis) extends EnumEntry
object Alignmentss extends SmartEnum[Alignments] {
  // scalastyle:off import.grouping underscore.import
  import org.aos.ddo.LawAxis._
  import org.aos.ddo.MoralAxis._
  // scalastyle:on import.grouping underscore.import
  case object ChaoticGood extends Alignments(Chaotic, Good)
  case object ChaoticNeutral extends Alignments(Chaotic, MoralAxis.Neutral)
  case object ChaoticEvil extends Alignments(Chaotic, Evil)
  case object NeutralGood extends Alignments(LawAxis.Neutral, Good)
  case object TrueNeutral extends Alignments(LawAxis.Neutral, MoralAxis.Neutral)
  case object NeutralEvil extends Alignments(LawAxis.Neutral, Evil)
  case object LawfulGood extends Alignments(Lawful, Good)
  case object LawfulNeutral extends Alignments(Lawful, MoralAxis.Neutral)
  case object LawfulEvil extends Alignments(Lawful, Evil)
  val values = findValues
}
