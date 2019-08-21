package org.aos.ddo.etl

import com.mongodb.ServerAddress
import com.mongodb.async.client.MongoClientSettings
import com.mongodb.connection.ClusterSettings
import com.typesafe.config.{Config, ConfigFactory}
import org.mongodb.scala.MongoClient
import configs.syntax._

import scala.collection.JavaConverters._

/**
  * Created by adarr on 5/7/2017.
  */
object EtlConfig {
  val cString =
    """
      |mongo {
      |  name: "effects"
      |  server-address: "localhost"
      |}
      |
      |data {
      |  affix-category {
      |    local: "/data/random_affixes.html"
      |    remote: "http://ddowiki.com/page/Update_29_randomly_generated_loot#Enchantments_by_slot"
      |
      |  }
      |}
    """.stripMargin

  lazy val config: Config = ConfigFactory.load().withFallback(ConfigFactory.parseString(cString))
  lazy val mongoClient: MongoClient = {

    val address = config.get[String]("mongo.server-address").value
    val clusterSettings: ClusterSettings = ClusterSettings
      .builder()
      .hosts(List(new ServerAddress(address)).asJava)
      .build()
    val settings: MongoClientSettings =
      MongoClientSettings.builder().clusterSettings(clusterSettings).build()
    MongoClient(settings)
  }
}
