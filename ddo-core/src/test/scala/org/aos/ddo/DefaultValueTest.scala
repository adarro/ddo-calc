package org.aos.ddo

import org.junit.runner.RunWith
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar

import com.typesafe.scalalogging.slf4j.LazyLogging

@RunWith(classOf[JUnitRunner])
class DefaultValueTest extends FunSpec with Matchers with LazyLogging {
  final val greeting = "Hello World"
  val noDefault: DefaultValue[String] = new DefaultValue[String] with NoDefault[String] {}
  val greetDefault = new DefaultValue[String] {
    override lazy val defaultValue = Some(greeting)
  }
  val otherGreet = new DefaultValue[String] {
    override lazy val defaultValue = Some(greeting)
  }
  describe("A Default Value ") {
    it("should be able to check if it has one") {
      noDefault.hasDefaultValue should be(false)
    }

    it("can be checked against None") {
      noDefault.defaultValue should be(empty)
    }

    it("May have a value") {
      greetDefault.hasDefaultValue should be(true)
    }

    it("can be verified to have a value") {
      val thisGreeting = Some(greeting)
      greetDefault.defaultValue should be(thisGreeting)
    }

    it("can see if it is the default value (equals)") {
      val thisGreeting = Some(greeting)
      greetDefault.isDefaultValue(otherGreet)
    }
  }
}
