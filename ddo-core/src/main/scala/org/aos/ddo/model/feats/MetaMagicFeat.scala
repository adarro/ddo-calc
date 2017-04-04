package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{ClassRequisiteImpl, Inclusion, Requisite}

import scala.collection.immutable.IndexedSeq

/**
  * Created by adarr on 2/21/2017.
  */
sealed trait MetaMagicFeat
    extends Feat
    with ClassRequisiteImpl
    with FriendlyDisplay
    with FeatMatcher { self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, MetaMagicFeat] = {
    case x: MetaMagicFeat => x
  }
  val matchFeatById: PartialFunction[String, MetaMagicFeat] = {
    case x: String if MetaMagicFeat.namesToValuesMap.contains(x) =>
      MetaMagicFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

}

object MetaMagicFeat extends Enum[MetaMagicFeat] {
  lazy val minimumSpellCastingClass: Seq[(CharacterClass, Int)] =
    List(
      (CharacterClass.Artificer, 1),
      (CharacterClass.Bard, 1),
      (CharacterClass.Cleric, 1),
      (CharacterClass.Druid, 1),
      (CharacterClass.FavoredSoul, 1),
      (CharacterClass.Sorcerer, 1),
      (CharacterClass.Warlock, 1),
      (CharacterClass.Wizard, 1),
      (CharacterClass.Paladin, 4),
      (CharacterClass.Ranger, 4)
    )
  case object EmpowerHealingSpell
      extends MetaMagicFeat
      with EmpowerHealingSpell
  case object EmpowerSpell extends MetaMagicFeat with EmpowerSpell
  case object EnlargeSpell extends MetaMagicFeat with EnlargeSpell
  case object EschewMaterials extends MetaMagicFeat with EschewMaterials
  case object ExtendSpell extends MetaMagicFeat with ExtendSpell
  case object HeightenSpell extends MetaMagicFeat with HeightenSpell
  case object MaximizeSpell extends MetaMagicFeat with MaximizeSpell
  case object QuickenSpell extends MetaMagicFeat with QuickenSpell
  override def values: IndexedSeq[MetaMagicFeat] = findValues
}
