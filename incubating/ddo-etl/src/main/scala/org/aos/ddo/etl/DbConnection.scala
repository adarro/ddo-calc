package io.truthencode.ddo.etl

import org.mongodb.scala.{MongoClient, MongoDatabase}

/**
  * Created by adarr on 5/5/2017.
  */
case class DbConnection(mongoClient: MongoClient) {

  val database: MongoDatabase = mongoClient.getDatabase("mydb")

}
