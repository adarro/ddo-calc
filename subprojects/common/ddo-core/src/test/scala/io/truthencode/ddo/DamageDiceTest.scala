/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DamageDiceTest.scala
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
package io.truthencode.ddo

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.meta.PhysicalDamageType
import io.truthencode.ddo.support.TraverseOps.*
import io.truthencode.ddo.support.dice.DamageInfo
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable
import scala.util.Random
import scala.util.Random.shuffle

class DamageDiceTest
  extends AnyFunSpec with TableDrivenPropertyChecks with Matchers with MockitoSugar
  with LazyLogging {

  final val maxFlags = 4
  val diceSet: Vector[String] =
    Vector(
      "3[2d10] + 4",
      "1.5[1d8]",
      "1d8",
      "10d4",
      "15d3",
      "[3d4]",
      "3d4 + 2",
      "3[2d10] - 4"
    )
  val validDiceExp: TableFor1[String] = Table(
    "dice",
    "3[2d10] + 4",
    "1.5[1d8]",
    "1d8",
    "10d4",
    "15d3",
    //  "[3d4]", // we really shouldn't be using this
    "3d4 + 2",
    "3[2d10] - 4"
  )
  def randomDiceSets: Iterable[String] = {

    @scala.annotation.tailrec
    def rng: Int = {
      val x = new java.security.SecureRandom().nextInt(PhysicalDamageType.values.size)
      if x.!=(0) then {
        x
      } else {
        rng
      }
    }

    def pl: Set[String] =
      shuffle(PhysicalDamageType.values.map(_.entryName)).take(rng).toSet

    val tpl: immutable.Seq[String] =
      for _ <- 0.to(maxFlags)
      yield pl
        .mkString(",")
    for x <- diceSet.cross(tpl) yield s"${x._1} ${x._2}"
  }
  describe("DnD Dice") {
    they("should support W[nDn + n] flags syntax") {
      randomDiceSets.foreach { d =>
        noException shouldBe thrownBy(DamageInfo.apply(d))
      }
    }
    they("should correctly parse all parts") {
      val dExp = "3[2d10] - 4 Slash"
      val dice = DamageInfo(dExp)
      dice.damageType should contain(PhysicalDamageType.Slash)
      dice.extra.toInt shouldEqual -4
      dice.extra.symbol shouldEqual "-"
      dice.weaponModifier shouldEqual 3
      dice.dice.number shouldEqual 2
      dice.dice.sides shouldEqual 10

    }

    they("should convert to string and back") {

      forAll(validDiceExp) { (s: String) =>
        logger.debug(s"evaluating $s")
        val res = DamageInfo(s)
        res.toString shouldEqual s
      }
    }
  }

}
