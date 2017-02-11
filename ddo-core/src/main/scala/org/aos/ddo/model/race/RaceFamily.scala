package org.aos.ddo.model.race

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable.HashSet

/**
  * Groups Individual race types into main families.
  * Used primarily for Race restricted equipment i.e.
  */
sealed trait RaceFamily extends EnumEntry {
  def includedRaces: Set[Race] = new HashSet[Race]()
}

trait Elvish extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Morninglord + Race.Elf + Race.HalfElf + Race.DrowElf
}

trait Human extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Human + Race.HalfElf
}

trait Dwarven extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Dwarf
}

trait Gnomish extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Gnome + Race.DeepGnome
}

trait Construct extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Warforged + Race.Bladeforged
}

trait Halfling extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Halfling
}

trait ShadarkaiFamily extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.Shadarkai
}

trait Orcish extends RaceFamily {
  abstract override def includedRaces: Set[Race] = super.includedRaces + Race.HalfOrc
}

object RaceFamily extends Enum[RaceFamily] {

  /**
    * Includes Standard Elves along with Drow, Sun and Half-elves
    */
  case object Elven extends Elvish

  /**
    * Includes Warforged, Bladeforged
    * TODO: needs to include any race with Construct Essence Feat
    */
  case object Construct extends Construct

  /**
    * Includes Humans and Half-elves
    */
  case object Human extends Human

  /**
    * Dwarven Lineage
    */
  case object Dwarven extends Dwarven

  /**
    * Includes Gnomes and their Underdark bretheren, Deep Gnomes
    */
  case object Gnomish extends Gnomish

  /**
    * The Halfling Race
    */
  case object Halfing extends Halfling

  /**
    * Includes Half-Orcs
    */
  case object Orcish extends Orcish

  /**
    * Includes the Iconic Shadarkai
    */
  case object Shadarkai extends ShadarkaiFamily

  override def values: Seq[RaceFamily] = findValues
}
