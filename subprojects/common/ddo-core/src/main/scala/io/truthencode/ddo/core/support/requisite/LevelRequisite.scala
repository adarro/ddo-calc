/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: LevelRequisite.scala
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

import io.truthencode.ddo.support.requisite.Requirement.ReqCharacterLevel
import io.truthencode.ddo.support.requisite.RequirementImplicits.characterLevelToReq

/**
 * Represents the character level required to attain a feat, skill etc.
 */
trait LevelRequisite { self: Requisite =>
  val requireCharacterLevel: Int

  def anyOfCharacterLevel: Seq[Int]

  def allOfCharacterLevel: Seq[Int]

  def noneOfCharacterLevel: Seq[Int]

  def grantToCharacterLevel: Seq[Int]

  def bonusSelectableToCharacterLevel: Seq[Int]
}

trait LevelRequisiteImpl extends MustContainImpl[Requirement] with LevelRequisite {
  self: Requisite & RequisiteType =>
  override val requireCharacterLevel: Int = 0

  def anyOfCharacterLevel: Seq[Int] = Nil

  def allOfCharacterLevel: Seq[Int] = Nil

  def noneOfCharacterLevel: Seq[Int] = Nil

  def grantToCharacterLevel: Seq[Int] = Nil

  def bonusSelectableToCharacterLevel: Seq[Int] = Nil
}

trait RequiresCharacterLevel extends LevelRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] =
    super.oneOf :+ ReqCharacterLevel(requireCharacterLevel)
}

trait GrantsToCharacterLevel
  extends LevelRequisite with GrantExpression with RequiresOneOf[Requirement] with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantToCharacterLevel.collect(characterLevelToReq)
  }
}
