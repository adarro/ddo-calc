package org.aos.ddo.model.alignment

import enumeratum.{EnumEntry, Enum => SmartEnum}
import org.aos.ddo.model.alignment.LawAxis.{Chaotic, Lawful, Neutral}
import org.aos.ddo.model.alignment.MoralAxis.{Evil, Good}

/** Represents the dual axis Alignments
  *
  * @param law   The range between Lawful to Chaotic
  * @param moral The moral range between Good and evil
  */
sealed class Alignments(law: LawAxis, moral: MoralAxis) extends EnumEntry

object Alignments extends SmartEnum[Alignments] {

  case object ChaoticGood extends Alignments(Chaotic, Good)

  case object ChaoticNeutral extends Alignments(Chaotic, MoralAxis.Neutral)

  case object ChaoticEvil extends Alignments(Chaotic, Evil)

  case object NeutralGood extends Alignments(Neutral, Good)

  case object TrueNeutral extends Alignments(Neutral, MoralAxis.Neutral)

  case object NeutralEvil extends Alignments(Neutral, Evil)

  case object LawfulGood extends Alignments(Lawful, Good)

  case object LawfulNeutral extends Alignments(Lawful, MoralAxis.Neutral)

  case object LawfulEvil extends Alignments(Lawful, Evil)

  val values = findValues
}
