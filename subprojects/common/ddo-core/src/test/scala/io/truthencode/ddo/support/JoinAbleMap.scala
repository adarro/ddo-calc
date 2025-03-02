/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: JoinAbleMap.scala
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

import io.truthencode.ddo.support.TraverseOps.MapOps

trait JoinAbleMap[X, Y, C <: Map[X, Y]] extends JoinAbleBase[(X, Y), C] {
  implicit val joinOnKeys: Boolean // = false
  private val someBool = implicitly[Boolean] // : Boolean = false
  def source: Map[X, Y] // = Map[X, Y]()
  private val list = source.take(sampleSize)
  val listA: Map[X, Y] = list.take(portion)
  val listB: Map[X, Y] = list.takeRight(portion)
  val onlyB: Map[X, Y] = list.takeRight(remainder)
  val onlyA: Map[X, Y] = list.take(remainder)
  // This is a total intercept and NOT a Key intercept
  val common: Map[X, Y] = listA.toSeq.intersect(listB.toSeq).toMap
  val commonWithA: Map[X, Y] = list.take(sampleSize - remainder)
  val commonWithB: Map[X, Y] = list.takeRight(sampleSize - remainder)

  val leftJoinA: Map[X, Y] = listA.leftJoin(listB)
  val rightJoinA: Map[X, Y] = listA.rightJoin(listB)
  val leftJoinB: Map[X, Y] = listB.leftJoin(listA)
  val rightJoinB: Map[X, Y] = listB.rightJoin(listA)

}
