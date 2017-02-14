package org.aos.ddo.support

import com.typesafe.scalalogging.LazyLogging
import com.wix.accord.scalatest.ResultMatchers
import org.aos.ddo.model.alignment.MoralAxis
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import scala.language.reflectiveCalls

/**
  * Created by adarr on 8/14/2016.
  */
@RunWith(classOf[JUnitRunner])
class ConcordionEnumHelperTest  extends FunSpec with Matchers with ResultMatchers with LazyLogging {
  val fixture = new {
    val builder =  new ConcordionEnumBuilderSupport {
      override def actual = MoralAxis.values.map(_.toString)
    }
    val helper = new ConcordionEnumBuilderHelper(builder)
  }
  describe("An Enum Helper") {
    it("should be generated from a jade template") {
      val f = fixture
      val lst = f.helper.listValues()
     logger.info(lst)
    }
  }
}
