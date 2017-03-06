package org.aos.ddo.model.feats

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.{Inclusion, RaceRequisite, Requisite}
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.reflect.ClassTag

/**
  * Created by adarr on 2/14/2017.
  */
trait Feat
  extends EnumEntry
    with DisplayName
    with FriendlyDisplay
    with SubFeatInformation {
  self: FeatType with Requisite with Inclusion with FeatMatcher =>

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

object Feat extends Enum[Feat] with FeatSearchPrefix {
  def featByRace[Feat: ClassTag](feat: Feat): Option[Seq[Race]] = {
    feat match {
      case x: RaceRequisite =>
        Some(x.allOfRace.map(_._1) ++ x.anyOfRace.map(_._1) ++ x.grantsToRace.map(_._1))
      case _ => None
    }
  }

  def racialFeats: Seq[(Feat, Seq[Race])] = {
    for {
      f <- Feat.values
      r <- featByRace(f)
      if r.nonEmpty
    } yield f -> r
  }

  def featsFromRace(race: Race): Seq[Feat] =
    racialFeats
      .filter { x =>
        x._2.contains(race)
      }
      .map { x =>
        x._1
      }

  override def values: Seq[Feat] =
    GeneralFeat.values ++ ClassFeat.values ++ RacialFeat.values
}
