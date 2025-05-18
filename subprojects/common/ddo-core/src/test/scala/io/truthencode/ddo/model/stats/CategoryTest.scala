package io.truthencode.ddo.model.stats

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers

class CategoryTest extends AnyFunSuiteLike with Matchers with LazyLogging {

  test("testInst uses proper class instance") {
    val dynamTrait = new MissChance {}
    logger.warn(dynamTrait.toString)

//    dynamTrait.catn shouldBe "MissChance"
  }

  test("testCategoryId") {}

}
