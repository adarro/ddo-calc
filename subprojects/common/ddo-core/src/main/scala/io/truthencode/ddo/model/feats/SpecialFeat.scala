package io.truthencode.ddo.model.feats

import com.typesafe.scalalogging.LazyLogging
import enumeratum.Enum
import io.truthencode.ddo.model.effect.features.{Features, FeaturesImpl}
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite.{Inclusion, Requisite}

/**
 * Meta / special feats such as Feat Respec Tokens which generally have meta-mechanic functions.
 */
sealed trait SpecialFeat
  extends Feat with FriendlyDisplay with SubFeatInformation with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & Features =>

}

object SpecialFeat
  extends Enum[SpecialFeat] with FeatSearchPrefix with FeatMatcher with LazyLogging {

  val matchFeat: PartialFunction[Feat, SpecialFeat] = { case x: SpecialFeat =>
    x
  }

  val matchFeatById: PartialFunction[String, SpecialFeat] = {
    case x: String if SpecialFeat.namesToValuesMap.contains(x) =>
      SpecialFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Special"

  override def values: IndexedSeq[SpecialFeat] = findValues

  //   Exchange feats
  case object FeatRespecToken extends SpecialFeat with FeatRespecToken
}
