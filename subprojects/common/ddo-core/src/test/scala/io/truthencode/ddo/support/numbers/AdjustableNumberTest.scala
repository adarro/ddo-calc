/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AdjustableNumberTest.scala
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
package io.truthencode.ddo.support.numbers

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.support.TraverseOps.Crossable
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import scala.reflect.Selectable.reflectiveSelectable

class AdjustableNumberTest extends AnyFunSpec with Matchers with LazyLogging {
  private def fixture: Object {
    val anyOrNoneValueStacks: Seq[Adjustment]; val allValueStacks: Seq[Adjustment];
    val noneStack: Seq[Adjustment]; val bitListOf3: List[Int];
    val noneStackMany: Iterable[Adjustment]; val anyStack: Seq[Adjustment];
    val uniqueStackMany: Iterable[Adjustment]; val diffStack: Seq[Adjustment];
    val anyStackMany: Iterable[Adjustment]
  } = new {

    val bitListOf3 = List(1, 2, 4)
    val anyStack: Seq[Adjustment] = (1 to 3).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.Untyped)
    }

    val noneStack: Seq[Adjustment] = List(1, 2, 4).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.ActionBoost)
    }

    val diffStack: Seq[Adjustment] = List(8, 16, 32).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.Miscellaneous)
    }

    val allValueStacks: Seq[Adjustment] = anyStack ++ noneStack ++ diffStack

    val anyOrNoneValueStacks: Seq[Adjustment] = anyStack ++ noneStack

    val noneStackMany: Iterable[Adjustment] =
      bitListOf3.cross(BonusType.NonStackingBonusTypes.take(3)).map { b =>
        Adjustment(b._1, AdjustmentType.Value, b._2)
      }

    val anyStackMany: Iterable[Adjustment] =
      bitListOf3.cross(BonusType.AnyBonusTypes.take(3)).map { b =>
        Adjustment(b._1, AdjustmentType.Value, b._2)
      }

    val uniqueStackMany: Iterable[Adjustment] =
      bitListOf3.cross(BonusType.UniqueBonusTypes.take(3)).map { b =>
        Adjustment(b._1, AdjustmentType.Value, b._2, Some(s"KEY:${b._2.displayText}"))

      }
  }

  describe("AdjustableNumberTest") {

    describe("It should filter") {
      it("by Stacks with Any") {
        val f = fixture

        val anyFilter = f.anyOrNoneValueStacks.collect(AdjustableNumber.fnStackAny)
        anyFilter should have size 3
      }

      it("by stacks with other") {
        val f = fixture
        val diffFilter = f.allValueStacks.collect(AdjustableNumber.fnStackDifferent)
        diffFilter should have size 3
      }

      it("by stacks with none") {
        val f = fixture
        val noneFilter = f.allValueStacks.collect(AdjustableNumber.fnStackNone)
        noneFilter should have size 3
      }
    }

    describe("it should add") {
      describe("fully stacking values") {
        it("Add all 'Any Stacking' values ") {
          val f = fixture
          val fl = f.anyStack.foldLeft(0)((x, y) => x + y.value)
          fl should equal(6)
        }
      }
      describe("Unique stacking values") {
        it("takes the highest value of a given type with a given key") {
          val f = fixture
          val fl = f.uniqueStackMany
          val ff = fl.map { f =>
            (f.bonusType, f.sourceId) -> f
          }
            .groupBy(_._1)

          val btg = fl.groupBy(_.bonusType)
          val uniqueTypes = btg.size
          val btg2 = btg.groupBy(_._2.groupBy(_.sourceId))
          val bb = btg.groupBy { x =>
            (x._1, x._2.groupBy(_.sourceId))
          }
          for bt <- btg.groupBy(_._2.groupBy(_.sourceId)) yield bt
//          val btg2 = btg.groupMap(_._1)(_._2.)
        }
      }
      describe("Non-stacking values") {
        it("Only take the highest of a given value") {
          val f = fixture
          val fl = f.noneStack.sortBy(_.value).reverse.headOption
          fl.value.value should equal(4)
        }
        it("Sum the highest of each unique non-stacking value") {
          val f = fixture
          // should be 3 types with 3 values each, highest being 4
          val fl = f.noneStackMany
          val btg = fl.groupBy(_.bonusType)
          val uniqueTypes = btg.size // max of 3, currently only 1 (Misc) exists
          val max = f.bitListOf3.max
          val mAdjust = btg.map { btm =>
            (btm._1, btm._2.toList.sortBy(_.value).reverse.headOption)
          }
          val rslt = mAdjust.foldLeft(0)((x, y) =>
            x + (y._2 match {
              case Some(x) => x.value
              case _ => 0
            }))
          logger.info(s"max: $max*\ttypes: $uniqueTypes")
          rslt should equal(max * uniqueTypes)

        }
      }
    }
  }
}
