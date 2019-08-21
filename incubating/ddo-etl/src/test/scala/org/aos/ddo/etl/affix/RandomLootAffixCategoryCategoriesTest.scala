package org.aos.ddo.etl.affix

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FunSpec, Matchers}
import org.aos.ddo.etl.affix.RandomLootAffixRules.catOpts
import org.scalatest.matchers.{MatchResult, Matcher}

/**
  * Created by adarr on 5/7/2017.
  */
class RandomLootAffixCategoryCategoriesTest
    extends FunSpec
    with Matchers
    with LazyLogging {
  implicit class iterOps(source: Iterable[_]) {
    def toAny: (Any, Any, Iterable[_]) = {
      val l = source.toList
      (l.head, l(2), l.drop(2))
    }
  }

//  val beFound =
//    Matcher { (left: String) =>
//      MatchResult(
//        left % 2 == 1,
//        left + " was not odd",
//        left + " was odd"
//      )
//    }

  describe("RandomLootAffixCategoriesTest") {

    it("should load AffixCategories") {

      noException shouldBe thrownBy(RandomLootAffixCategories.AffixCategories)

    }

    it("Generate a distinct set of prefixes") {
      RandomLootAffixCategories.prefixValues should not be empty

    }

    it("Should have entries for each prefix and Category") {
      val cats = RandomLootAffixCategories.prefixValues.toList.sorted
      //  val pEle = RandomLootAffixRules.ElementalAbsorptionPrefix

      val entries = RandomLootAffixRules.MappedPrefixEntries
        .flatMap(_.map(_.category.name))
        .distinct
        .sorted // RandomLootAffixRules.MappedPrefixEntries
      cats diff entries should be(empty)
      //   cats intersect entries should not be empty
//      entries.foreach { x =>
//        val sample = x.headOption.get.category.name
//        logger.info(s"Sample Category $sample")
//        val z = x.map(_.category.name).toList
//
//        cats intersect z should not be empty
//      }

    }

    it("Should have at least one prefix entry for every Category") {
      val cats = RandomLootAffixCategories.prefixValues.toList.sorted
      val entries = RandomLootAffixRules.MappedPrefixEntries
        .flatMap(_.map(_.category.name))
        .distinct
        .sorted
      //  val z = entries.flatMap(x => x.map(_.category.name))
      val notFound = entries diff cats
      notFound should be(empty)
    }

    it("Should have at least one category for each prefix") {

      val cats = RandomLootAffixCategories.prefixValues.toList.sorted
      val entries = RandomLootAffixRules.MappedPrefixEntries
        .flatMap(_.map(_.category.name))
        .distinct
        .sorted
      val (x, y, z) = cats.toAny
      entries should contain allOf (x, y, z)
      //    entries.foreach(x => cats should contain(x))

    }

  }
}
