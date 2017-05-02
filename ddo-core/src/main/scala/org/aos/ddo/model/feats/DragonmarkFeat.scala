package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.{
  Dwarf,
  Elf,
  Gnome,
  HalfElf,
  HalfOrc,
  Halfling,
  Human
}
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 3/26/2017.
  */
sealed trait DragonmarkFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation
    with FeatMatcher
    with FeatRequisiteImpl {
  self: FeatType with Requisite with RequisiteType with Inclusion =>
  val matchFeat: PartialFunction[Feat, DragonmarkFeat] = {
    case x: DragonmarkFeat => x
  }
  val matchFeatById: PartialFunction[String, DragonmarkFeat] = {
    case x: String if DragonmarkFeat.namesToValuesMap.contains(x) =>
      DragonmarkFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

object DragonmarkFeat extends Enum[DragonmarkFeat] with FeatSearchPrefix {
  case object LeastDragonmarkOfStorm
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfElf, 1))
  }
  case object LeastDragonmarkOfShadow
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Elf, 1))
  }
  case object LeastDragonmarkOfFinding
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((HalfOrc, 1))
  }
  case object LeastDragonmarkOfHealing
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAnyOfRace
      with Active
      with Passive {
    override def anyOfRace: Seq[(Race, Int)] = List((Halfling, 1), (Human, 1))
  }
  case object LeastDragonmarkOfMaking
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAnyOfRace
      with Active
      with Passive {
    override def anyOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfPassage
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfSentinel
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Human, 1))
  }
  case object LeastDragonmarkOfWarding
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Dwarf, 1))
  }
  case object LeastDragonmarkOfScribing
      extends DragonmarkFeat
      with RaceRequisiteImpl
      with RequiresAllOfRace
      with Active
      with Passive {
    override def allOfRace: Seq[(Race, Int)] = List((Gnome, 1))
  }

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "Dragonmark: "

  override def values: immutable.IndexedSeq[DragonmarkFeat] = findValues
}
