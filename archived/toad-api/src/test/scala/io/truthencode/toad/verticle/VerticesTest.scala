package io.truthencode.toad.verticle

import io.vertx.core.Vertx
import io.vertx.core.shareddata.SharedData
import monix.execution.Scheduler
import org.scalatest.funspec.AsyncFunSpec
import org.scalatest.matchers.should.Matchers
import vertices.core._

import scala.language.postfixOps

class VerticesTest extends AsyncFunSpec with Matchers {
  lazy val vertx: Vertx = Vertx.vertx()
  implicit val scheduler: Scheduler = new VertxScheduler(vertx)

  def sharedData: SharedData = vertx.sharedData()

  describe("Vertx eventbus") {
    it(" can be accessed via Monix in scala async") {

      val resultTask = for {
        asyncMap <- sharedData.getAsyncMapL[String, String]("example")
        _ <- asyncMap.putL("key", "value")
        value <- asyncMap.getL("key")
      } yield value

      resultTask.map { rs => rs shouldEqual "value" }.runToFuture
    }
  }
}
