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
package io.truthencode.ddo.support

import io.truthencode.ddo.support.TraverseOps.Joinable

import scala.collection.{GenIterable, GenSeq}
import scala.language.higherKinds

trait JoinAbleSeq[+X, C <: GenSeq[X]] extends JoinAbleBase[X, C] {
  lazy val source = GenSeq[X]()
  val list = source.take(sampleSize)
  //  val list:C = source.take(sampleSize)
  val listA = list.take(portion)
  val listB = list.toList.takeRight(portion)
  val onlyB = list.toList.takeRight(remainder)
  val onlyA = list.take(remainder)
  val common = listA.intersect(listB)
  val commonWithA = list.take(sampleSize - remainder)
  val commonWithB = list.toList.takeRight(sampleSize - remainder).toSeq

  val leftJoinA = listA.toSeq.leftJoin(listB)
  val rightJoinA = listA.toSeq.rightJoin(listB)
  val leftJoinB = listB.toSeq.leftJoin(listA)
  val rightJoinB = listB.toSeq.rightJoin(listA)
}
