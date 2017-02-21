package org.aos.ddo.support.requisite

import org.aos.ddo.model.race.Race
import org.aos.ddo.support.requisite.RequirementImplicits.raceToReq

/**
  * Created by adarr on 1/30/2017.
  */
trait RaceRequisite {
  def anyOfRace: Seq[(Race, Int)] = IndexedSeq.apply()

  def allOfRace: Seq[(Race, Int)] = IndexedSeq.apply()

  def noneOfRace: Seq[(Race, Int)] = IndexedSeq.apply()

  def grantsToRace:Seq[(Race,Int)] = IndexedSeq.apply()
}

trait FreeRace extends RaceRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfRace extends RaceRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override val oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfRace collect raceToReq
  }
}

trait RequiresAllOfRace extends RaceRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override val allOf: Seq[Requirement] = super.allOf ++ {
    allOfRace collect raceToReq
  }
}

trait RequiresNoneOfRace extends RaceRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override val noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfRace collect raceToReq
  }
}