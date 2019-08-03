package org.aos.ddo.support

import com.typesafe.scalalogging.LazyLogging
import com.wix.accord.scalatest.ResultMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 8/14/2016.
  */
@RunWith(classOf[JUnitRunner])
class ConcordionTemplateBuilderTest
    extends FunSpec
    with Matchers
    with ResultMatchers
    with LazyLogging {
  ignore("A Custom enum") {
    it("should be generated from a jade template") {
      val builder = new ConcordionTemplateBuilder()
      val actual = builder.renderEnum("org.aos.ddo.model.alignment.MoralAxis")
      actual should not be empty
      logger.info(actual.getOrElse("No data returned"))
    }
  }
}
