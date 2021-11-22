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

trait JoinAbleBase[+X, C <: Iterable[X]] {
  val sampleSize = 15
//  implicit def asSeq(x: C) = x.toSeq
  def portion: Int = sampleSize / 2 + (sampleSize / 3)
  def remainder: Int = sampleSize - portion
//  val source: C
//  val list: C
//  val listA: C
//  val listB: C
//  val onlyB: C
//  val onlyA: C
//  val common: C
//  val commonWithA: C
//  val commonWithB: C
//
//  val leftJoinA: C
//  val rightJoinA: C
//  val leftJoinB: C
//  val rightJoinB: C

  //  def foo[B,K,V](c:C,rhs:B)(implicit ev:B =:= C ,ev2:C <:< Map[_,_]) = {
  //    c match {
  //        case x:Map[_,_] =>c.toMap.leftJoin(rhs.toMap)
  //    }
  //  }
  //  val fnLJ: PartialFunction[C,Map[K,V]] = {
  //
  //  }
  // def bar[A,B]( b: B)(implicit ev: B <:< C <:< MapLike[X,]) = (a,b)

  // def lj[A](x:X)(implicit ev:B)

}
