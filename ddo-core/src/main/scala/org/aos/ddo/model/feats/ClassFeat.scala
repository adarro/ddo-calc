package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{Inclusion, Requisite}

/**
  * Created by adarr on 2/14/2017.
  */
sealed trait ClassFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation
    with FeatMatcher { self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, ClassFeat] = {
    case x: ClassFeat => x
  }
  val matchFeatById: PartialFunction[String, ClassFeat] = {
    case x: String if ClassFeat.namesToValuesMap.contains(x) =>
      ClassFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

object ClassFeat extends Enum[ClassFeat] with FeatSearchPrefix {
  case object ArcaneLore extends ClassFeat with ArcaneLore
  case object ArtificerConstructMastery
      extends ClassFeat
      with ArtificerConstructMastery
  case object ArtificerKnowledgeScrolls
      extends ClassFeat
      with ArtificerKnowledgeScrolls
  case object InscribeArtificerScroll
      extends ClassFeat
      with InscribeArtificerScroll
  case object RapidReload extends ClassFeat with RapidReload
  case object ReanimateConstruct extends ClassFeat with ReanimateConstruct
  case object Trapfinding extends ClassFeat with Trapfinding
  case object UnleashIronDefender extends ClassFeat with UnleashIronDefender
  case object ArtificerCraftMastery
      extends ClassFeat
      with ArtificerCraftMastery
    case object RuneArmUse extends ClassFeat with RuneArmUse
  case object ArtificerKnowledgeWands
      extends ClassFeat
      with ArtificerKnowledgeWands
  case object Trapmaking extends ClassFeat with Trapmaking
  case object RetainEssence extends ClassFeat with RetainEssence
  case object ArtificerKnowledgeArmsAndArmor
      extends ClassFeat
      with ArtificerKnowledgeArmsAndArmor
  case object ArtificerKnowledgePotions
      extends ClassFeat
      with ArtificerKnowledgePotions
  case object ArtificerKnowledgeWondrousItems
      extends ClassFeat
      with ArtificerKnowledgeWondrousItems
  case object ArtificerSkillMastery
      extends ClassFeat
      with ArtificerSkillMastery
  case object SneakAttack extends ClassFeat with SneakAttack
  override lazy val values: Seq[ClassFeat] = findValues
}
