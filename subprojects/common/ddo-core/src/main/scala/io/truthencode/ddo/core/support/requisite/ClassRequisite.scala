/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ClassRequisite.scala
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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.support.requisite.Requirement.GroupedRequirement
import io.truthencode.ddo.support.requisite.RequirementImplicits.ClassImplicits

/**
 * Represents Character class based restrictions and allowances Created by adarr on 1/30/2017.
 */
trait ClassRequisite {
  def gkBonusSelectableClasses: String
  def gkGrantClasses: String
  def gkRequiredClasses: String
  def gkProhibitedClasses: String
  def anyOfClass: Seq[(HeroicCharacterClass, Int)]

  def allOfClass: Seq[(HeroicCharacterClass, Int)]

  def noneOfClass: Seq[(HeroicCharacterClass, Int)]

  def grantToClass: Seq[(HeroicCharacterClass, Int)]

  def bonusSelectableToClass: Seq[(HeroicCharacterClass, Int)]
}

/**
 * Base implementation setting all required values to a default empty set. Stackable traits may then
 * override and augment selected values.
 */
trait ClassRequisiteImpl extends MustContainImpl[Requirement] with ClassRequisite {
  self: Requisite & RequisiteType =>
  def gkRequiredClasses: String = defaultGroupKey
  def gkGrantClasses: String = defaultGroupKey
  def gkBonusSelectableClasses: String = defaultGroupKey

  override def gkProhibitedClasses: String = defaultGroupKey

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = Nil

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = Nil

  override def noneOfClass: Seq[(HeroicCharacterClass, Int)] = Nil

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = Nil

  override def bonusSelectableToClass: Seq[(HeroicCharacterClass, Int)] = Nil
}

trait FreeClass extends ClassRequisite with RequiresNone with RequiredExpression with Requisite

/**
 * Feature requires any of the included classes.
 */
trait RequiresAnyOfClass extends ClassRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfClass.map(_.toReq).map(GroupedRequirement(_, gkRequiredClasses, RequisiteType.Require))
  }
}

/**
 * Feature requires this particular set of classes. i.e. A given feat may only be available to
 * Monks.
 */
trait RequiresAllOfClass extends ClassRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfClass.map(_.toReq).map(GroupedRequirement(_, gkRequiredClasses, RequisiteType.Require))
  }
}

/**
 * Feature is prohibited for a given set of classes. This is often used as an exclusive selector,
 * i.e. you can choose one thing or the other, but one excludes the other.
 */
trait RequiresNoneOfClass extends ClassRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfClass.map(_.toReq).map(GroupedRequirement(_, gkProhibitedClasses, RequisiteType.Prohibit))
  }
}

/**
 * This feature is automatically granted to a particular class or set of classes.
 */
trait GrantsToClass
  extends ClassRequisite with GrantExpression with RequiresOneOf[Requirement] with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    grantToClass.map(_.toReq).map(GroupedRequirement(_, gkGrantClasses, RequisiteType.Grant))
  }
}

/**
 * This feature becomes selectable to a given class, generally at a particular level. Specifically,
 * this applies to Feats to allow bonus feat selection such as Monk Bonus feats.
 */
trait SelectableToClass
  extends ClassRequisite with ClassRequisiteImpl with RequiresOneOf[Requirement] with Requisite {
  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    bonusSelectableToClass
      .map(_.toReq)
      .map(GroupedRequirement(_, gkBonusSelectableClasses, RequisiteType.Require))
  }
}
