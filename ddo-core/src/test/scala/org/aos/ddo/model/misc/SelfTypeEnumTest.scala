package org.aos.ddo.model.misc

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FunSpec, Matchers}

import scala.languageFeature.postfixOps

/**
  * Created by adarr on 2/6/2017.
  */
class SelfTypeEnumTest extends FunSpec with Matchers with LazyLogging {



  describe("Self typed enum entry hacks") {
    they("exist") {
      noException should be thrownBy (Simple.values)
    }
  }

}
