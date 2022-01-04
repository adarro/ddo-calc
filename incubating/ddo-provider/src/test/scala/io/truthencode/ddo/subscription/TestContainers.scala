package io.truthencode.ddo.subscription

import org.testcontainers.containers.{MongoDBContainer, Network, PulsarContainer}
import org.testcontainers.utility.DockerImageName

object TestContainers {
  lazy val n = Network.newNetwork()
  lazy val pulsarTestContainer: PulsarContainer =
    new PulsarContainer(DockerImageName.parse("apachepulsar/pulsar:2.9.1"))
  // .withExposedPorts(6650, 8080) // .withNetworkAliases("localhost").withAccessToHost(true)
  def pulsarServiceUrl: String = {
    if (!pulsarTestContainer.isRunning) {
      pulsarTestContainer.start()
    }
    pulsarTestContainer.getPulsarBrokerUrl
  }

  lazy val mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))

  def mongoUrl: String = {
    if (!mongoDBContainer.isRunning) {
      mongoDBContainer.start
    }
    val port = mongoDBContainer.getFirstMappedPort.toInt
    val host = mongoDBContainer.getHost
    s"mongodb://$host:$port"
  }

}
