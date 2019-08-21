/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.model.alignment

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.NoDefault
import org.aos.ddo.enumeration.BitSupport

import scala.collection.immutable
// import org.aos.ddo.enumeration._


/**
  * Created by adarr on 8/12/2016.
  */
sealed trait AlignmentType

// extends BitWise

sealed trait LawAxis
  extends EnumEntry
    with AlignmentType
    with NoDefault[LawAxis] {

  lazy val bitValue: Int = {
    c.bitValues
      .filter { x =>
        c.checkVal(x._1)
      }
      .values
      .headOption
      .getOrElse(0)

  }

  // SuperEnum.companion[LawAxis]
  /* implicit def enumToSpecific(x: Enum[_ <: EnumEntry]): LawAxis = {
     x.asInstanceOf[LawAxis]
   }*/

  // def eToS[A,B](implicit A : LawAxis,B:Enum[_]) = LawAxis
  // def sToE[B,A](implicit A : LawAxis,B:Enum[_]) = B.asInstanceOf[A]

  // def makeWork[E] = implicitly[LawAxis]

  //  override type E = LawAxis.type
  // C <: Enum[_ <: enumeratum.EnumEntry]
  //  override val companion = LawAxis
  //  implicit val v = LawAxis
  protected val c = LawAxis
}

object LawAxis extends Enum[LawAxis] with BitSupport {

  /*  implicit def comp = new SuperEnum[LawAxis] {
    type C = LawAxis.type

    def apply(): LawAxis.type = LawAxis
  }*/

  type T = LawAxis
  val values: immutable.IndexedSeq[T] = findValues
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }

  case object Chaotic extends LawAxis

  case object Neutral extends LawAxis

  case object Lawful extends LawAxis

}

sealed trait MoralAxis
  extends EnumEntry
    with AlignmentType
    with NoDefault[MoralAxis] {

  val c = MoralAxis

  /* val bitValue: Int = c.bitValues
    .filter { x =>
      c.checkVal(x._1)
    }.values.head*/
  val bitValue: Int = 0
}

object MoralAxis extends Enum[MoralAxis] with BitSupport {

  type T = MoralAxis
  val values: immutable.IndexedSeq[MoralAxis] = findValues
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }

  case object Good extends MoralAxis

  case object Neutral extends MoralAxis

  case object Evil extends MoralAxis

}
