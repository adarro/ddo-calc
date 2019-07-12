package org.aos.ddo.etl

import com.mongodb.ServerAddress
import com.mongodb.async.client.MongoClientSettings
import com.mongodb.connection.ClusterSettings
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.etl.tags.Db
import org.mongodb.scala.MongoClient
import org.scalatest.{FunSpec, Matchers}

import scala.collection.JavaConverters._

/**
  * Created by adarr on 5/5/2017.
  */
@Db
class DbConnectionTest extends FunSpec with Matchers with LazyLogging {
  def checkClient(client: MongoClient): Unit = {
    lazy val db = DbConnection(client)
    try {
      val name = db.database.name
      logger.info(s"Database name is $name")

    } finally {
      db.mongoClient.close()
    }
  }
  describe("Connection Options") {
    it("Should directly connect to the default server localhost on port 27017") {

      lazy val mongoClient: MongoClient = MongoClient()
      noException shouldBe thrownBy(checkClient(mongoClient))
    }

    it("Use a Connection String") {
      // Use a Connection String
      lazy val mongoClient: MongoClient = MongoClient("mongodb://localhost")
      noException shouldBe thrownBy(checkClient(mongoClient))
    }

    it("provide custom MongoClientSettings") {

      val clusterSettings: ClusterSettings = ClusterSettings
        .builder()
        .hosts(List(new ServerAddress("localhost")).asJava)
        .build()
      val settings: MongoClientSettings =
        MongoClientSettings.builder().clusterSettings(clusterSettings).build()
      lazy val mongoClient: MongoClient = MongoClient(settings)
      noException shouldBe thrownBy(checkClient(mongoClient))
    }

    it("can use config files") {

      val conf = ConfigFactory.load()
      val address = conf.getString("mongo.server-address")
      val clusterSettings: ClusterSettings = ClusterSettings
        .builder()
        .hosts(List(new ServerAddress(address)).asJava)
        .build()
      val settings: MongoClientSettings =
        MongoClientSettings.builder().clusterSettings(clusterSettings).build()
      lazy val mongoClient: MongoClient = MongoClient(settings)
      noException shouldBe thrownBy(checkClient(mongoClient))

    }
  }
}
