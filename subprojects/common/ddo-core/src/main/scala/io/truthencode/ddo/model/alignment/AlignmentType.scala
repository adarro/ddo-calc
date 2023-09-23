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
package io.truthencode.ddo.model.alignment

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.enumeration.BitSupport
import io.truthencode.ddo.support.SearchPrefix

import scala.collection.immutable

/**
 * Created by adarr on 8/12/2016.
 */
sealed trait AlignmentType

sealed trait LawAxis extends EnumEntry with AlignmentType with NoDefault[LawAxis] {

  lazy val bitValue: Int = {
    c.bitValues.filter { x =>
      c.checkVal(x._1)
    }.values.headOption
      .getOrElse(0)

  }

  protected val c = LawAxis
}

object LawAxis extends Enum[LawAxis] with BitSupport with SearchPrefix {
  type T = LawAxis
  val values: immutable.IndexedSeq[T] = findValues
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "LawAxis"

  case object Chaotic extends LawAxis

  case object Neutral extends LawAxis

  case object Lawful extends LawAxis

}

sealed trait MoralAxis extends EnumEntry with AlignmentType with NoDefault[MoralAxis] {

  val c = MoralAxis

  /* val bitValue: Int = c.bitValues
    .filter { x =>
      c.checkVal(x._1)
    }.values.head */
  val bitValue: Int = 0
}

object MoralAxis extends Enum[MoralAxis] with BitSupport with SearchPrefix {

  type T = MoralAxis
  val values: immutable.IndexedSeq[MoralAxis] = findValues
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "MoralAxis"

  case object Good extends MoralAxis

  case object Neutral extends MoralAxis

  case object Evil extends MoralAxis

}
