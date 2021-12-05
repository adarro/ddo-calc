/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.toad

import _root_.io.vertx.core._
import _root_.io.vertx.core.http.HttpServer
import _root_.io.vertx.core.json.{Json, JsonObject}
import _root_.io.vertx.ext.mongo.{MongoClient, MongoClientDeleteResult, MongoClientUpdateResult}
import _root_.io.vertx.ext.web.handler.{BodyHandler, StaticHandler}
import _root_.io.vertx.ext.web.{Router, RoutingContext}
import com.typesafe.config.{Config, ConfigFactory}
import io.truthencode.toad
import io.truthencode.toad.config.CommonImplicits._
import io.truthencode.toad.verticle.Event2HandlerImplicits._
import io.truthencode.toad.verticle.Whisky
import org.slf4j.{Logger, LoggerFactory}

import scala.jdk.CollectionConverters._

object SimpleScalaVerticle {
  private val log: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  private val COLLECTION = "whiskies"

}

class SimpleScalaVerticle extends AbstractVerticle {
  private[this] lazy val mongo = MongoClient.createShared(vertx, config)
  // this nonsense is simple to avoid 'Magic numbers' or adding a bunch of format off meta comments
  val s404 = 404
  val s200 = 200
  val s400 = 400
  val s204 = 204
  override def start(fut: Promise[Void]): Unit = {
    if (config.size > 0) {
      toad.SimpleScalaVerticle.log.info("Mongo config")
      for ((k, v) <- config.getMap.asScala) {
        toad.SimpleScalaVerticle.log.info(s"k:$k\tv:$v")
      }
    } else {
      toad.SimpleScalaVerticle.log.info("Using default Mongo config")
    }
    createSomeData(
      (_: AsyncResult[Void]) => {
        startWebApp((http: AsyncResult[HttpServer]) => completeStartup(http, fut))
      },
      fut)
  }

  private val id1 = "id"

  private def startWebApp(next: Handler[AsyncResult[HttpServer]]): Unit = {
    SimpleScalaVerticle.log.info("Starting verticle asyncly")
    val router: Router = Router.router(vertx)
    router
      .route("/")
      .handler(new Handler[RoutingContext] {
        override def handle(ctx: RoutingContext): Unit = {
          ctx
            .response()
            .putHeader("content-type", "text/html")
            .end("<h1>Hello from my first scala verticle with Vert.x 3 application</h1>")
        }
      })

    router
      .route("/test/")
      .handler((x: RoutingContext) => toad.SimpleScalaVerticle.log.info("we d" + id1 + " something"))

    router
      .route("/")
      .handler((c: RoutingContext) => {
        c.response
          .putHeader("content-type", "text/html")
          .end("<h1>Hello from my first Vert.x 3 application</h1>")
      })
    router.route("/assets/*").handler(StaticHandler.create("assets"))
    router.get("/api/whiskies").handler(this.retrieveAll _)
    router
      .route("/api/whiskies*")
      .handler(new Handler[RoutingContext] {
        override def handle(ctx: RoutingContext): Unit = {
          BodyHandler.create()
        }
      })
    // router.route("/api/whiskies*").handler((c:RoutingContext) => { BodyHandler.create()} )
    router.post("/api/whiskies").handler(this.addOne _)
    router.get("/api/whiskies/:" + id1).handler(this.retrieveOne _)
    router.put("/api/whiskies/:" + id1).handler(this.updateOne _)
    router.delete("/api/whiskies/:" + id1).handler(this.deleteOne _)
    val conf: Config = ConfigFactory.load("defaults")
    val port: Int = conf.getInt("server-info.port")
    toad.SimpleScalaVerticle.log.info(s"Creating http server on port $port")
    vertx.createHttpServer.requestHandler(router).listen(port, next.handle _)
  }

  private def completeStartup(http: AsyncResult[HttpServer], fut: Promise[Void]): Unit = {
    if (http.succeeded) {
      fut.complete()
    } else {
      fut.fail(http.cause)
    }
  }

  @throws[Exception]
  override def stop(): Unit = {
    mongo.close()
  }

  private def addOne(routingContext: RoutingContext): Unit = {
    val whisky: Whisky = Json.decodeValue(routingContext.getBodyAsString, classOf[Whisky])
    mongo.insert(
      toad.SimpleScalaVerticle.COLLECTION,
      whisky.toJson,
      new Handler[AsyncResult[String]] {
        override def handle(r: AsyncResult[String]): Unit = {
          routingContext
            .response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(whisky.setId(r.result())))
        }
      }
    )
  }

  private val underScoreIid = "_" + id1

  private def retrieveOne(routingContext: RoutingContext): Unit = {
    Option(routingContext.request.getParam(id1)) match {
      case Some(id) =>
        mongo.findOne(
          toad.SimpleScalaVerticle.COLLECTION,
          new JsonObject().put(underScoreIid, id),
          null,
          (ar: AsyncResult[JsonObject]) => {
            if (ar.succeeded()) {
              if (ar.result() == null) {
                routingContext
                  .response()
                  .setStatusCode(s400)
                  .end()
                return
              }
              val whisky = new Whisky(ar.result())
              routingContext
                .response()
                .setStatusCode(s200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(whisky))
            } else {
              routingContext.response().setStatusCode(s404).end()
            }
          }
        )
      case None => routingContext.response.setStatusCode(s400).end()
    }
  }

  private def updateOne(routingContext: RoutingContext): Unit = {
    val id: String = routingContext.request.getParam(id1)
    val json: JsonObject = routingContext.getBodyAsJson
    if (id == null || json == null) {
      routingContext.response.setStatusCode(s400).end()
    } else {
      mongo.updateCollection(
        toad.SimpleScalaVerticle.COLLECTION,
        new JsonObject().put(underScoreIid, id),
        new JsonObject().put("$set", json),
        (v: AsyncResult[MongoClientUpdateResult]) => {
          if (v.failed()) {

            routingContext.response().setStatusCode(s404).end()
          } else {
            routingContext
              .response()
              .putHeader("content-type", "application/json; charset=utf-8")
              .end(
                Json.encodePrettily(
                  new Whisky(
                    id,
                    json.getString("name"),
                    json
                      .getString("origin"))))
          }
        }
      )
    }
  }

  private def deleteOne(routingContext: RoutingContext): Unit = {
    val x: Option[String] = routingContext.request.getParam(id1)
    x match {
      case Some(id) =>
        mongo.removeDocument(
          toad.SimpleScalaVerticle.COLLECTION,
          new JsonObject().put(underScoreIid, id),
          (ar: AsyncResult[MongoClientDeleteResult]) => {
            if (ar.succeeded()) {
              routingContext.response().setStatusCode(s204).end()
            } else {
              val t = ar.result()
              routingContext
                .response()
                .setStatusMessage(s"removed ${t.getRemovedCount} documents")
                .setStatusCode(s400)
                .end()
            }
          }
        )
      case None => routingContext.response.setStatusCode(s400).end()
    }
  }

  private def retrieveAll(routingContext: RoutingContext): Unit = {

    mongo.find(
      toad.SimpleScalaVerticle.COLLECTION,
      new JsonObject,
      (results: AsyncResult[java.util.List[JsonObject]]) => {
        val objects = results.result().asScala

        val whiskies = objects.map((o: JsonObject) => {
          new Whisky(o)
        })
        routingContext
          .response()
          .putHeader("content-type", "application/json; charset=utf-8")
          .end(Json.encodePrettily(whiskies))
      }
    )
  }

  private def ok[T] = {
    import Future.{succeededFuture => suss}
    suss[T]
  }

  private def createSomeData(next: Handler[AsyncResult[Void]], fut: Promise[Void]): Unit = {
    val bowmore: Whisky = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay")
    val talisker: Whisky = new Whisky("Talisker 57° North", "Scotland, Island")
    SimpleScalaVerticle.log.info(bowmore.toJson)
    val aResult2 = (ar2: AsyncResult[String]) => {
      if (ar2.failed()) {
        fut.fail(ar2.cause())
      } else {
        ok[Void]
      } // elif
    }
    val aResult = (ar: AsyncResult[String]) => {
      if (ar.failed()) {
        fut.fail(ar.cause())
      } else {
        mongo.insert(SimpleScalaVerticle.COLLECTION, talisker.toJson, aResult2)
      } // elif
    }

    val countResult = (count: AsyncResult[java.lang.Long]) => {
      if (count.succeeded())
        if (count.result() == 0) {
          // no whiskies, insert data
          mongo.insert(SimpleScalaVerticle.COLLECTION, bowmore.toJson, aResult)
        } else {
          //  next.handle(Future[Void].succeededFuture())
          next.handle(ok[Void])
        }
      else {
        // report the error
        fut.fail(count.cause())
      }
    }
    mongo.count(SimpleScalaVerticle.COLLECTION, new JsonObject, countResult)
  }

}
