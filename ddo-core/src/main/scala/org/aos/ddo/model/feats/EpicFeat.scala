package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 2/14/2017.
  */
sealed trait EpicFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation {
  self: FeatType with Requisite with Inclusion with EpicFeatCategory =>

}

object EpicFeat extends Enum[EpicFeat] with FeatSearchPrefix with FeatMatcher {
  val matchFeat: PartialFunction[Feat, EpicFeat] = {
    case x: EpicFeat => x
  }
  val matchFeatById: PartialFunction[String, EpicFeat] = {
    case x: String if EpicFeat.namesToValuesMap.contains(x) =>
      EpicFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

  // General Passive Feats
  case object BlindingSpeed
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with BlindingSpeed

  case object BulwarkOfDefense
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with BulwarkOfDefense

  case object EpicDamageReduction
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicDamageReduction

  case object EpicFortitude
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicFortitude
  case object EpicReflexes
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicReflexes

  case object EpicReputation
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicReputation

  case object EpicSkills
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicSkills

  case object EpicWill
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicWill

  case object GreatAbility
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with GreatAbility

  case object OverwhelmingCritical
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with OverwhelmingCritical

  case object EpicToughness
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with EpicToughness

  case object WatchfulEye
      extends FeatRequisiteImpl
      with EpicFeat
      with GeneralPassive
      with FreeFeat
      with WatchfulEye

  case object MasterOfLight
      extends FeatRequisiteImpl
      with EpicFeat
      with SpellCastingActive
      with FreeFeat



  override def values: immutable.IndexedSeq[EpicFeat] = findValues
}
