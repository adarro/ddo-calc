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
package io.truthencode.ddo.support.tree

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}
import io.truthencode.ddo.support.points.SpendablePoints

import scala.collection.immutable
sealed trait TreeLike extends EnumEntry with DisplayName with FriendlyDisplay with SearchPrefix {
  val pointType: SpendablePoints

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

object TreeLike extends Enum[TreeLike] {
  override lazy val values: immutable.IndexedSeq[TreeLike] =
    ClassTrees.values ++ DestinySpheres.values ++ EpicDestiny.values ++ ReaperTrees.values ++ UniversalTrees.values
}

/**
 * Represents Enhancement Trees such as Universal and Class Enhancement trees (Pale Master, Falconry
 * etc)
 */
trait EnhancementTree extends TreeLike {
  override val pointType: SpendablePoints = SpendablePoints.ActionPoints
}

/**
 * Enhancement Lines with Class Restrictions
 */
trait ClassTree extends EnhancementTree

/**
 * Enhancement Lines with no Class Restrictions
 */
trait UniversalTree extends EnhancementTree

trait ReaperTree extends TreeLike {
  override val pointType: SpendablePoints = SpendablePoints.SurvivalPoints
}

/**
 * One of the Epic Destiny Spheres (Primal, Arcane etc)
 */
trait DestinySphere extends TreeLike {
  override val pointType: SpendablePoints = SpendablePoints.FatePoints
}

/**
 * Epic Destinies lines within a given sphere such as Grandmaster of Flowers
 */
trait DestinyTree extends TreeLike {
  override val pointType: SpendablePoints = SpendablePoints.EpicDestinyPoints
}
