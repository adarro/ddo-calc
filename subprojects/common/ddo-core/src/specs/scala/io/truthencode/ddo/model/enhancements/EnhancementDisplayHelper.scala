/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.enhancements

import io.truthencode.ddo.model.DisplayHelper
import io.truthencode.ddo.model.enhancement.{ClassEnhancement, Enhancement, Tier}
import io.truthencode.ddo.support.requisite.{ActionPointRequisite, PointInTreeRequisite}
import io.truthencode.ddo.support.tree.ClassTrees

trait EnhancementDisplayHelper extends DisplayHelper {
  override val enum: E = Enhancement
  val tree: ClassTrees
  type ENH = ClassEnhancement with Tier with ActionPointRequisite with PointInTreeRequisite

  val values: Seq[ENH] = enum.values collect { case x: ENH => x }

  def foo = {
    val b: Option[String] = Some("test")
    val c: Option[String] = None
    List(b, c).flatMap { p =>
      p.get
    }

  }

  case class CEnhancement(
    id: String
  ) {
    private val _enh = values.find(p => p.entryName.equals(id))
    require(_enh.nonEmpty)

    private val enh: ENH = _enh.get

    val actionPointCost: Int = enh.costPerRank
    val ranks: Int = enh.ranks

    val progression: Int = enh.pointsInTree.find { p =>
      p._1 == enh.tree
    } match {
      case Some(x) => x._3
      case _       => 0
    } // .flatMap{x => x._3} //.flatMap {p => p._3}
    val requirements: Option[List[String]] = None
  }


}
