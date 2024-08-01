package io.truthencode.ddo.support.numbers

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.support.TraverseOps.Crossable
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AdjustableNumberTest extends AnyFunSpec with Matchers with LazyLogging {
  private def fixture = new {
    val bitListOf3 = List(1, 2, 4)
    val anyStack = (1 to 3).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.Untyped)
    }

    val noneStack = List(1, 2, 4).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.ActionBoost)
    }

    val diffStack = List(8, 16, 32).map { x =>
      Adjustment(x, AdjustmentType.Value, BonusType.Miscellaneous)
    }

    val allValueStacks = anyStack ++ noneStack ++ diffStack

    val anyOrNoneValueStacks = anyStack ++ noneStack

    val noneStackMany = bitListOf3.cross(BonusType.NonStackingBonusTypes.take(3)).map { b =>
      Adjustment(b._1, AdjustmentType.Value, b._2)
    }

    val anyStackMany = bitListOf3.cross(BonusType.AnyBonusTypes.take(3)).map { b =>
      Adjustment(b._1, AdjustmentType.Value, b._2)
    }

    val uniqueStackMany = bitListOf3.cross(BonusType.UniqueBonusTypes.take(3)).map { b =>
      Adjustment(b._1, AdjustmentType.Value, b._2, Some(s"KEY:${b._2.displayText}"))

    }
  }

  describe("AdjustableNumberTest") {

    describe("It should filter") {
      it("by Stacks with Any") {
        val anyFilter = fixture.anyOrNoneValueStacks.collect(AdjustableNumber.fnStackAny)
        anyFilter should have size 3
      }

      it("by stacks with other") {
        val diffFilter = fixture.allValueStacks.collect(AdjustableNumber.fnStackDifferent)
        diffFilter should have size 3
      }

      it("by stacks with none") {
        val noneFilter = fixture.allValueStacks.collect(AdjustableNumber.fnStackNone)
        noneFilter should have size 3
      }
    }

    describe("it should add") {
      describe("fully stacking values") {
        it("Add all 'Any Stacking' values ") {
          val fl = fixture.anyStack.foldLeft(0)((x, y) => x + y.value)
          fl should equal(6)
        }
      }
      describe("Unique stacking values") {
        it("takes the highest value of a given type with a given key") {
          val fl = fixture.uniqueStackMany
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
          for (bt <- btg.groupBy(_._2.groupBy(_.sourceId))) yield bt
//          val btg2 = btg.groupMap(_._1)(_._2.)
        }
      }
      describe("Non-stacking values") {
        it("Only take the highest of a given value") {
          val fl = fixture.noneStack.sortBy(_.value).reverse.headOption
          fl.value.value should equal(4)
        }
        it("Sum the highest of each unique non-stacking value") {
          // should be 3 types with 3 values each, highest being 4
          val fl = fixture.noneStackMany
          val btg = fl.groupBy(_.bonusType)
          val uniqueTypes = btg.size // max of 3, currently only 1 (Misc) exists
          val max = fixture.bitListOf3.max
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
