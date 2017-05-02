package org.aos.ddo.support.requisite

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.RequirementImplicits.raceToReq

/**
  * Created by adarr on 1/30/2017.
  */
trait RaceRequisite {
  def anyOfRace: Seq[(Race, Int)]

  def allOfRace: Seq[(Race, Int)]

  def noneOfRace: Seq[(Race, Int)]

  def grantsToRace: Seq[(Race, Int)]
}

trait RaceRequisiteImpl
    extends MustContainImpl[Requirement]
    with RaceRequisite { self: Requisite with RequisiteType =>
  override def anyOfRace: Seq[(Race, Int)] = Nil

  override def allOfRace: Seq[(Race, Int)] = Nil

  override def noneOfRace: Seq[(Race, Int)] = Nil

  override def grantsToRace: Seq[(Race, Int)] = Nil
}

trait FreeRace
    extends RaceRequisite
    with RequiresNone
    with RequiredExpression
    with Requisite

trait RequiresAnyOfRace
    extends RaceRequisite
    with RequiresOneOf[Requirement]
    with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfRace collect raceToReq
  }
}

trait RequiresAllOfRace
    extends RaceRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfRace collect raceToReq
  }
}

trait RequiresNoneOfRace
    extends RaceRequisite
    with RequiresNoneOf[Requirement]
    with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfRace collect raceToReq
  }
}

trait GrantsToRace
    extends RaceRequisite
    with GrantExpression
    with RequiresOneOf[Requirement]
    with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantsToRace collect raceToReq
  }
}
