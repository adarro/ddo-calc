package org.aos.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.requisite._

import scala.collection.immutable.IndexedSeq

/**
  * Created by adarr on 2/14/2017.
  */
trait Feat
    extends EnumEntry
    with DisplayName
    with FriendlyDisplay
    with SubFeatInformation { self: FeatType with Requisite with Inclusion =>

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

object Feat extends Enum[Feat] with FeatSearchPrefix with LazyLogging {
  def containsInTuple[T](target: T, search: Seq[(T, _)]*): Boolean = {
    val s: Seq[(T, _)] = search.flatMap(_.zipWithIndex).map(_._1)
    s.exists(_._1 == target)
  }

  /**
    * Filters feats which belong to racial / general feats only.
    *
    * This removes feats that happen to have some racial aspect but are considered
    * under another category, such as a Deity Based feat that requires classes as well as a race.
    *
    * @note Not sure if we really need this method in production.
    *       May only be useful for detecting / Acceptance testing subsets
    */
  val fnPureRacialFeat
    : PartialFunction[RaceRequisite, Feat with RaceRequisite] = {
    case x: RacialFeat => x
    case x: GeneralFeat => x
  }

  val fnRacialFeats: PartialFunction[Feat, Feat with RaceRequisite] = {
    case x: RaceRequisite => x
  }

  lazy val racialFeats: IndexedSeq[Feat with RaceRequisite] = {
    Feat.values collect fnRacialFeats
  }

  def fnOptionToRequisite(req: RaceRequisite,
                          opt: RequirementOption): Seq[(Race, Int)] = {
    opt match {
      case RequirementOption.AutoGrant => req.grantsToRace
      case RequirementOption.Available => req.anyOfRace ++ req.allOfRace

      //      case _ => logger.warn(s"Feature not yet supported: Can not determine values for option $opt")
      //        throw NotImplementedException
      //      case RequirementOption.SelectableAsBonus => ???
      //      case RequirementOption.SelectableWithRestriction => ???
    }
  }

  def fnOptionsToRaceSequence(
      req: RaceRequisite,
      options: RequirementOption*): Seq[(Race, Int)] = {
    val seqOfSeq = for {
      opt <- options

    } yield fnOptionToRequisite(req = req, opt = opt)
    seqOfSeq.flatten
  }

  def featsFromRace(race: Race, opt: RequirementOption*): Seq[Feat] = {

    racialFeats
      .filter { f =>
        containsInTuple(race, fnOptionsToRaceSequence(f, opt: _*))
      }
      .filterNot {
        case _: DeityFeat => true
        case _ => false
      }
  }

  def featsFromRace(race: Race): Seq[Feat] = {
    racialFeats.filter { f =>
      containsInTuple(race,
                      fnOptionsToRaceSequence(f, RequirementOption.AutoGrant))
    }
  }

  override def values: IndexedSeq[Feat] =
    GeneralFeat.values ++ ClassFeat.values ++ RacialFeat.values ++ MetaMagicFeat.values ++ DeityFeat.values ++ EpicFeat.values
}
