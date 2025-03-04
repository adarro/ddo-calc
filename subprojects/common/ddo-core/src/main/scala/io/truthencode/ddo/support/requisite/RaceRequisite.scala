/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RaceRequisite.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.support.requisite

import io.truthencode.ddo.model.race.Race
import io.truthencode.ddo.support.requisite.RequirementImplicits.raceToReq

/**
 * Created by adarr on 1/30/2017.
 */
trait RaceRequisite {
  def anyOfRace: Seq[(Race, Int)]

  def allOfRace: Seq[(Race, Int)]

  def noneOfRace: Seq[(Race, Int)]

  def grantsToRace: Seq[(Race, Int)]
}

trait RaceRequisiteImpl extends MustContainImpl[Requirement] with RaceRequisite {
  self: Requisite & RequisiteType =>
  override def anyOfRace: Seq[(Race, Int)] = Nil

  override def allOfRace: Seq[(Race, Int)] = Nil

  override def noneOfRace: Seq[(Race, Int)] = Nil

  override def grantsToRace: Seq[(Race, Int)] = Nil
}

trait FreeRace extends RaceRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfRace extends RaceRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfRace.collect(raceToReq)
  }
}

trait RequiresAllOfRace extends RaceRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfRace.collect(raceToReq)
  }
}

trait RequiresNoneOfRace extends RaceRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfRace.collect(raceToReq)
  }
}

trait GrantsToRace
  extends RaceRequisite with GrantExpression with RequiresOneOf[Requirement] with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantsToRace.collect(raceToReq)
  }
}
