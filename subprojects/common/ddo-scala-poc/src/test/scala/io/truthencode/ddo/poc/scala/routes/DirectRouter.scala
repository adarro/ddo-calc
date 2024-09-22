package io.truthencode.ddo.poc.scala.routes

//import io.vertx.core.json.Json
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import jakarta.enterprise.event.Observes
import org.jboss.logging.Logger
//import io.vertx.lang.scala.json.Json
import jakarta.enterprise.context.ApplicationScoped

import scala.jdk.CollectionConverters.*

@ApplicationScoped
class DirectRouter {
  private val LOG = Logger.getLogger(classOf[DirectRouter])

  def init(@Observes router: Router): Unit = {
    LOG.warn("We have entered (Scala) Direct Router")
    router
      .route()
      .handler(ctx => {
        // Get the address of the request
        val address = ctx.request.connection.remoteAddress.toString
        // Get the query parameter "name" or use "World" as default
        val name = ctx.queryParam("name").asScala.toList match
          case n :: _ => n
          case _ => "World"
        val greeting = s"Hello $name connected from $address"

        // Write a JSON response
        ctx.json(Json.encodeToBuffer(s"""
           {
             "name": "$name",
             "address": "$address",
             "message": "$greeting"
           }
         """))
      })

  }

}
