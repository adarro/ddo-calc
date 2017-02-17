package org.aos.ddo.model.misc

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.activation.ActivationType
import org.aos.ddo.activation.ActivationType.Passive
import org.aos.ddo.model.effect.TriggerEvent
import org.scalatest.{FunSpec, Matchers}

import scala.languageFeature.postfixOps

/**
  * Created by adarr on 2/6/2017.
  */
class SelfTypeEnumTest extends FunSpec with Matchers with LazyLogging {

  sealed trait Simple extends EnumEntry

  sealed trait values extends EnumEntry

  object Simple extends Enum[Simple] {

    object Basic extends values with Simple

    override def values: Seq[Simple with EnumEntry] = findValues
  }

  describe("Self typed enum entry hacks") {
    they("exist") {
      noException should be thrownBy (Simple.values)
    }
  }

}
