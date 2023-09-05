package io.truthencode.toad.verticle

import io.vertx.core.Vertx
import org.scalatest.{BeforeAndAfterEach, Suite}

import java.util.UUID

trait VertxEnvironment extends BeforeAndAfterEach {
  this: Suite =>

  var vertx: Vertx = _
//var testContext:TestContext = _
  override def beforeEach(): Unit = {
    super.beforeEach()
    vertx = Vertx.vertx()
  }

  def registerEventBusCodec(clazz: Class[_]): Unit = {
//        vertx.eventBus().registerDefaultCodec(clazz.asInstanceOf[Class[AnyRef]], AkkaSerializationMessageCodec(clazz))
  }

  def endpointAddress(id: String): String =
    s"vertx-endpoint-$id-${UUID.randomUUID().toString}"
}
