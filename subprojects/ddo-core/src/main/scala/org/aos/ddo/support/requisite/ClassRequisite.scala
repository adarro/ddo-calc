/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.support.requisite

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.RequirementImplicits.classToReq

/**
  * Represents Character class based restrictions and allowances
  * Created by adarr on 1/30/2017.
  */
trait ClassRequisite {
  def anyOfClass: Seq[(CharacterClass, Int)]

  def allOfClass: Seq[(CharacterClass, Int)]

  def noneOfClass: Seq[(CharacterClass, Int)]

  def grantToClass: Seq[(CharacterClass, Int)]

  def bonusSelectableToClass: Seq[(CharacterClass, Int)]
}

/**
  * Base implementation setting all required values to a default empty set.
  * Stackable traits may then override and augment selected values.
  */
trait ClassRequisiteImpl extends ClassRequisite {
  override def anyOfClass: Seq[(CharacterClass, Int)] = Nil

  override def allOfClass: Seq[(CharacterClass, Int)] = Nil

  override def noneOfClass: Seq[(CharacterClass, Int)] = Nil

  override def grantToClass: Seq[(CharacterClass, Int)] = Nil

  override def bonusSelectableToClass: Seq[(CharacterClass, Int)] = Nil
}

trait FreeClass
  extends ClassRequisite
    with RequiresNone
    with RequiredExpression
    with Requisite

/**
  * Feature requires any of the included classes.
  */
trait RequiresAnyOfClass
  extends ClassRequisite
    with RequiresOneOf[Requirement]
    with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfClass collect classToReq
  }
}

/**
  * Feature requires this particular set of classes.
  * i.e. A given feat may only be available to Monks.
  */
trait RequiresAllOfClass
  extends ClassRequisite
    with RequiresAllOf[Requirement]
    with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfClass collect classToReq
  }
}

/**
  * Feature is prohibited for a given set of classes.
  * This is often used as an exclusive selector, i.e. you can choose one thing or the other,
  * but one excludes the other.
  */
trait RequiresNoneOfClass
  extends ClassRequisite
    with RequiresNoneOf[Requirement]
    with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfClass collect classToReq
  }
}

/**
  * This feature is automatically granted to a particular class or set of classes.
  */
trait GrantsToClass
  extends ClassRequisite
    with GrantExpression
    with RequiresOneOf[Requirement]
    with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantToClass collect classToReq
  }
}

/**
  * This feature becomes selectable to a given class, generally at a particular level.
  * Specifically, this applies to Feats to allow bonus feat selection such as Monk Bonus feats.
  */
trait SelectableToClass
  extends ClassRequisite
    with ClassRequisiteImpl
    with RequiresOneOf[Requirement]
    with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    bonusSelectableToClass collect classToReq
  }
}