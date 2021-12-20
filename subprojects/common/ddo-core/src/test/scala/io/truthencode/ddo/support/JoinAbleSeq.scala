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

trait JoinAbleSeq[+X, C <: Seq[X]] extends JoinAbleBase[X, C] {
  lazy val source: Seq[X] = Seq[X]()
  val list: Seq[X] = source.take(sampleSize)
  //  val list:C = source.take(sampleSize)
  val listA: Seq[X] = list.take(portion)
  val listB: Seq[X] = list.toList.takeRight(portion)
  val onlyB: Seq[X] = list.toList.takeRight(remainder)
  val onlyA: Seq[X] = list.take(remainder)
  val common: Seq[X] = listA.intersect(listB)
  val commonWithA: Seq[X] = list.take(sampleSize - remainder)
  val commonWithB: Seq[X] = list.toList.takeRight(sampleSize - remainder)

  val leftJoinA: Seq[X] = listA.leftJoin(listB)
  val rightJoinA: Seq[X] = listA.rightJoin(listB)
  val leftJoinB: Seq[X] = listB.leftJoin(listA)
  val rightJoinB: Seq[X] = listB.rightJoin(listA)
}
