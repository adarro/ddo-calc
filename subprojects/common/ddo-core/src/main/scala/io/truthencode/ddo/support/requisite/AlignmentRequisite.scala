/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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

import io.truthencode.ddo.model.alignment.{AlignmentType, Alignments}
import io.truthencode.ddo.support.requisite.RequirementImplicits.{
  alignmentTypeToReq,
  alignmentsToReq
}
trait AlignmentRequisite {

  /**
   * Allowed if Character has Any of these Alignments i.e. Can be Neutral or Good or Chaotic etc
   * @return
   *   Allowed Types
   */
  def anyOfAlignmentType: Seq[AlignmentType]

  /**
   * Allowed if Chacter has ALL of these alignments.
   * @return
   *   Required Alignments
   * @note
   *   This implementation may change as there can be at most two required alignment parts, one from
   *   each axis.
   */
  def allOfAlignmentType: Seq[AlignmentType]

  /**
   * Allowed providing Character posseses NONE of these alignments
   * @return
   *   Restricted alignments
   */
  def noneOfAlignmentType: Seq[AlignmentType]

  /**
   * Allowed if Character has these specific Alignment Combinations
   * @return
   *   List of valid alignment combinations such as Lawful Good
   */
  def anyOfAlignments: Seq[Alignments]

  /**
   * Allowed if Character has This alignment
   * @return
   *   Allowed Alignment
   * @note
   *   implementation may change as you can have only one alignment combination
   */
  def allOfAlignments: Seq[Alignments]

  /**
   * Allowed providing NONE of these alignment combinations apply.
   * @return
   *   Restricted alignments
   */
  def noneOfAlignments: Seq[Alignments]
}

trait AlignmentRequisiteImpl extends AlignmentRequisite {

  /**
   * Allowed if Character has Any of these Alignments i.e. Can be Neutral or Good or Chaotic etc
   *
   * @return
   *   Allowed Types
   */
  override def anyOfAlignmentType: Seq[AlignmentType] = Nil

  /**
   * Allowed if Chacter has ALL of these alignments.
   *
   * @return
   *   Required Alignments
   * @note
   *   This implementation may change as there can be at most two required alignment parts, one from
   *   each axis.
   */
  override def allOfAlignmentType: Seq[AlignmentType] = Nil

  /**
   * Allowed providing Character posseses NONE of these alignments
   *
   * @return
   *   Restricted alignments
   */
  override def noneOfAlignmentType: Seq[AlignmentType] = Nil

  /**
   * Allowed if Character has these specific Alignment Combinations
   *
   * @return
   *   List of valid alignment combinations such as Lawful Good
   */
  override def anyOfAlignments: Seq[Alignments] = Nil

  /**
   * Allowed if Character has This alignment
   *
   * @return
   *   Allowed Alignment
   * @note
   *   implementation may change as you can have only one alignment combination
   */
  override def allOfAlignments: Seq[Alignments] = Nil

  /**
   * Allowed providing NONE of these alignment combinations apply.
   *
   * @return
   *   Restricted alignments
   */
  override def noneOfAlignments: Seq[Alignments] = Nil
}

/**
 * Requires one of the listed Alignment Parts
 */
trait RequiresOneOfAxis extends AlignmentRequisite with RequiresAllOf[Requirement] with Requisite {
  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfAlignmentType.collect(alignmentTypeToReq)
  }
}

/**
 * Requires NONE of the following alignment types, i.e. Must not be Good or Lawful
 */
trait RequiresNoneOfAxis
  extends AlignmentRequisite with RequiresNoneOf[Requirement] with Requisite {
  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfAlignmentType.collect(alignmentTypeToReq)
  }
}

/**
 * Requires one of the listed Alignments (I.e. Lawful Good or Neutral Evil
 */
trait RequiresOneOfAlignment
  extends AlignmentRequisite with RequiresAllOf[Requirement] with Requisite {
  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfAlignments.collect(alignmentsToReq)
  }
}

/**
 * Requires that None of these alignments are true, i.e. Not True Neutral
 */
trait RequiresNoneOfAlignment
  extends AlignmentRequisite with AlignmentRequisiteImpl with RequiresNoneOf[Requirement]
  with Requisite {
  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfAlignments.collect(alignmentsToReq)
  }
}
