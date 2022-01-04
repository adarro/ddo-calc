package io.truthencode.ddo.subscription

import io.truthencode.ddo.subscription.service.{EchoClient, EchoServer}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.io.IOException

class EchoTestScala extends AnyFunSpec with Matchers with BeforeAndAfterAll {

  private var server: Process = _
  private var client: EchoClient = _

  @throws[IOException]
  @throws[InterruptedException]
  def setup(): Unit = {
    server = EchoServer.start
    client = EchoClient.start
  }

  override def beforeAll(): Unit = {
    val resp1 = client.sendMessage("hello")
    val resp2 = client.sendMessage("world")
    "hello" shouldEqual resp1
    "world" shouldEqual resp2
  }

  @throws[IOException]
  override protected def afterAll(): Unit = {
    server.destroy()
    EchoClient.stop()
  }
}
