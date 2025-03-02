/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ValueWithRequirements.scala
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

import com.typesafe.scalalogging.LazyLogging
import enumeratum.EnumEntry

case class ValueWithRequirements(
  f: EnumEntry & RequisiteExpression,
  reqType: RequisiteType,
  incl: Inclusion,
  groupKey: String,
  req: Seq[Requirement]) {
  val requisiteType: String = reqType.requisiteType

  val includeType: String = incl.inclusionType
}

object ValueWithRequirements extends LazyLogging {

  /**
   * Used for guessing how we should sort something
   */
  object SortType extends Enumeration {
    type SortType = Value

    /**
     * Sort by Alpha Key
     */
    val Alpha,

    /**
     * Sort using Alpha then Numeric Key
     */
    AlhpaNumeric,

    /**
     * Sort Using Numeric then Alpha Key
     */
    NumericAlpha = Value
  }

  type RSort = Requirement & DefaultRequirementSort
  type ANSort = Requirement & NumberRequirementSort
  // Credit: SO https://stackoverflow.com/questions/19345030/easy-idiomatic-way-to-define-ordering-for-a-simple-case-class
  // Note that because `Ordering[A]` is not contravariant, the declaration
  // must be type-parametrized in the event that you want the implicit
  // ordering to apply to subclasses of `X`.
  implicit def orderingByName[A <: ValueWithRequirements]: Ordering[A] =
    Ordering.by(e => (e.groupKey, e.requisiteType))

  // scalastyle:off cyclomatic.complexity method.length
  def sortWithChaos(v1: RSort, v2: RSort): Boolean = {
    val anO1 = v1 match {
      case x: ANSort if x.reverse => (x, SortType.NumericAlpha)
      case x: ANSort => (x, SortType.AlhpaNumeric)
      case _ => (v1, SortType.Alpha)
    }

    val anO2 = v2 match {
      case x: ANSort if x.reverse => (x, SortType.NumericAlpha)
      case x: ANSort => (x, SortType.AlhpaNumeric)
      case _ => (v1, SortType.Alpha)
    }

    val an1 = anO1._2
    val an2 = anO2._2
    // Both alphanumeric type sorts
    // true iif an1 == an2 && SortType in (AlphaNumeric, NumericAlpha)
    val anAgreement = an1 == an2 && an1 != SortType.Alpha
    // true iif an1 == an2 == SortType.NumericAlpha
    val anrAgreement = anAgreement && an1 == SortType.NumericAlpha
    // both simple alpha sorts
    val aAgreement = an1 == an2 && an1 == SortType.Alpha
    // conflict in number of fields, so must use alpha sort and give preference to number fields (dummy number in non-numbered object).
    val mixed1 = !anAgreement && !anrAgreement && !aAgreement && an1 != SortType.Alpha

    if anrAgreement then {
      if anO1._1
          .asInstanceOf[ANSort]
          .numericalSortKey != anO2._1.asInstanceOf[ANSort].numericalSortKey then {
        anO1._1.asInstanceOf[ANSort].numericalSortKey < anO2._1
          .asInstanceOf[ANSort]
          .numericalSortKey
      } else { anO1._1.alphaSortKey > anO2._1.alphaSortKey }
    } else if anAgreement then {
      if anO1._1
          .asInstanceOf[ANSort]
          .numericalSortKey != anO2._1.asInstanceOf[ANSort].numericalSortKey then {
        anO1._1.asInstanceOf[ANSort].numericalSortKey < anO2._1
          .asInstanceOf[ANSort]
          .numericalSortKey
      } else { anO1._1.alphaSortKey < anO2._1.alphaSortKey }
    } else if aAgreement then {
      anO1._1.alphaSortKey < anO2._1.alphaSortKey
    } else if mixed1 then {
      (anO1._1.alphaSortKey > anO2._1.alphaSortKey || anO1._1.alphaSortKey == anO2._1.alphaSortKey && anO1._1
        .asInstanceOf[ANSort]
        .numericalSortKey < Int.MaxValue)
    } else {
      logger.warn("We've got something unfinished to _sort_ out")
      false
    }

  } // scalastyle:on
  val orderingByAlphaNumerical: Ordering[ANSort] =
    Ordering.by(e => (e.alphaSortKey, e.numericalSortKey))
  val orderingByAlphaNumericalReverse: Ordering[ANSort] =
    Ordering.by(e => (e.numericalSortKey, e.alphaSortKey))
  val orderingByAlphaSort: Ordering[RSort] = Ordering.by(e => (e.alphaSortKey))

  val orderingById: Ordering[ValueWithRequirements] = Ordering.by(e => e.f.entryName)
}
