package org.aos.ddo.etl

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FunSpec, Matchers}
import com.typesafe.config.ConfigFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * Created by adarr on 5/5/2017.
  */
@RunWith(classOf[JUnitRunner])
class EtlConfigTest extends FunSpec with Matchers with LazyLogging {
describe("Configuration") {
  it("Should exist") {
    val config = ConfigFactory.load()
    noException shouldBe thrownBy (config.getString("mongo.server-address"))
  }
}
}
