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
package io.truthencode.toad.verticle;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a verticle. A verticle is a _Vert.x component_. This verticle is
 * implemented in Java, but you can implement them in JavaScript, Groovy or even
 * Ruby.
 */
public class MyFirstVerticle extends AbstractVerticle {
    private static final Logger log = LoggerFactory.getLogger(MyFirstVerticle.class);
    private static final String COLLECTION = "whiskies";
    public static final String CONTENT_TYPE = "content-type";
    public static final String API_WHISKIES_ID = "/api/whiskies/:id";
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
    private MongoClient mongo;

    /**
     * This method is called when the verticle is deployed. It creates a HTTP
     * server and registers a simple request handler.
     * <p>
     * Notice the `listen` method. It passes a lambda checking the port binding
     * result. When the HTTP server has been bound on the port, it call the
     * `complete` method to inform that the starting has completed. Else it
     * reports the error.
     *
     * @param fut the future
     */
    @Override
    public void start(Promise<Void> fut) {
//		try {
//			MongoService.Start();
//		} catch (IOException e) {
//			log.error("Failed to start Mongo",e);
//		}
        // Create a Mongo client
        JsonObject cfg = Vertx.currentContext().config();
        String uri = cfg.getString("mongo_uri");
        if (uri == null) {
            log.info("mongo_uri not specified, defaulting to localhost  27017");
            uri = "mongodb://localhost:27017";
        }
        String db = cfg.getString("mongo_db");
        if (db == null) {
            log.info("mongo_db not specified, defaulting to test");
            db = "test";
        }

        JsonObject mongoconfig = new JsonObject()
            .put("connection_string", uri)
            .put("db_name", db);
        mongo = MongoClient.createShared(vertx, mongoconfig);
        if (mongoconfig.size() > 0) {
            log.info("Mongo config");
            mongoconfig.forEach(a -> {
                String key = a.getKey();
                Object value = a.getValue();
                log.info(String.format("k:%s\tv:%s", key, value));

            });
        } else {
            log.info("Using default Mongo config");
        }

        createSomeData(
            (nothing) -> startWebApp((http) -> completeStartup(http, fut)),
            fut);
    }

    /**
     * Method startWebApp ...
     *
     * @param next Handler<AsyncResult<HttpServer>> used to chain next deployment or event
     */
    private void startWebApp(Handler<AsyncResult<HttpServer>> next) {
        // Create a router object.
        Router router = Router.router(vertx);

        // Bind "/" to our hello message.
        router.route("/")
            .handler(
                routingContext -> {
                    HttpServerResponse response = routingContext
                        .response();
                    response.putHeader(CONTENT_TYPE, "text/html")
                        .end("<h1>Hello from my first Vert.x 3 application</h1>");
                });

        router.route("/assets/*").handler(StaticHandler.create("assets"));

        router.get("/api/whiskies").handler(this::getAll);
        router.route("/api/whiskies*").handler(BodyHandler.create());
        router.post("/api/whiskies").handler(this::addOne);
        router.get(API_WHISKIES_ID).handler(this::getOne);
        router.put(API_WHISKIES_ID).handler(this::updateOne);
        router.delete(API_WHISKIES_ID).handler(this::deleteOne);
        Config conf = ConfigFactory.load("defaults");
        int port = conf.getInt("server-info.port");

        log.info(String.format("Creating http server on port (%d)", port));
        // Create the HTTP server and pass the "accept" method to the request
        // handler.
        vertx.createHttpServer().requestHandler(router).listen(
            // Retrieve the port from the configuration,
            // default to 8080.
            port, next);
    }

    /**
     * Listener used to handle completion events.
     * <p>
     * Applies result to future.
     *
     * @param http current status
     * @param fut  result on completion of http
     */
    private void completeStartup(AsyncResult<HttpServer> http, Promise<Void> fut) {
        if (http.succeeded()) {
            fut.complete();
        } else {
            fut.fail(http.cause());
        }
    }

    /**
     * Cleans up resources upon stop request, specifically the mongo instance.
     */
    @Override
    public void stop() {
        mongo.close();

    }

    private void addOne(RoutingContext routingContext) {
        final Whisky whisky = Json.decodeValue(
            routingContext.getBodyAsString(), Whisky.class);

        mongo.insert(
            COLLECTION,
            whisky.toJson(),
            r -> routingContext
                .response()
                .setStatusCode(201)
                .putHeader(CONTENT_TYPE,
                    APPLICATION_JSON_CHARSET_UTF_8)
                .end(Json.encodePrettily(whisky.setId(r.result()))));
    }

    private void getOne(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        if (id == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            mongo.findOne(
                COLLECTION,
                new JsonObject().put("_id", id),
                null,
                ar -> {
                    if (ar.succeeded()) {
                        if (ar.result() == null) {
                            routingContext.response().setStatusCode(404)
                                .end();
                            return;
                        }
                        Whisky whisky = new Whisky(ar.result());
                        routingContext
                            .response()
                            .setStatusCode(200)
                            .putHeader(CONTENT_TYPE,
                                APPLICATION_JSON_CHARSET_UTF_8)
                            .end(Json.encodePrettily(whisky));
                    } else {
                        routingContext.response().setStatusCode(404).end();
                    }
                });
        }
    }

    private void updateOne(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            mongo.updateCollection(
                COLLECTION,
                new JsonObject().put("_id", id), // Select a unique document
                /* The update syntax: {$set, the json object containing the
                 fields to update} */
                new JsonObject().put("$set", json),
                v -> {
                    if (v.failed()) {
                        routingContext.response().setStatusCode(404).end();
                    } else {
                        routingContext
                            .response()
                            .putHeader(CONTENT_TYPE,
                                APPLICATION_JSON_CHARSET_UTF_8)
                            .end(Json.encodePrettily(new Whisky(id,
                                json.getString("name"), json
                                .getString("origin"))));
                    }
                });
        }
    }

    private void deleteOne(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        if (id == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            mongo.removeDocuments(COLLECTION, new JsonObject().put("_id", id),
                ar -> routingContext.response().setStatusCode(204).end());
        }
    }

    private void getAll(RoutingContext routingContext) {
        mongo.find(
            COLLECTION,
            new JsonObject(),
            results -> {
                List<JsonObject> objects = results.result();
                List<Whisky> whiskies = objects.stream().map(Whisky::new)
                    .collect(Collectors.toList());
                routingContext
                    .response()
                    .putHeader(CONTENT_TYPE,
                        APPLICATION_JSON_CHARSET_UTF_8)
                    .end(Json.encodePrettily(whiskies));
            });
    }

    private void createSomeData(Handler<AsyncResult<Void>> next,
                                Promise<Void> fut) {
        Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig",
            "Scotland, Islay");
        Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
        log.info(bowmore.toJson().toString());

        // Do we have data in the collection ?
        mongo.count(COLLECTION, new JsonObject(), count -> {
            if (count.succeeded()) {
                if (count.result() == 0) {
                    // no whiskies, insert data
                    mongo.insert(COLLECTION, bowmore.toJson(), ar -> {
                        log.info("No values in data collection. Adding sample data");
                        if (ar.failed()) {
                            log.error("fail to add sample data to mongo collection", ar.cause());
                            fut.fail(ar.cause());
                        } else {
                            log.info("Adding different data to collection");
                            mongo.insert(COLLECTION, talisker.toJson(), ar2 -> {
                                if (ar2.failed()) {
                                    log.error(("Failed to add data to collection"));
                                    fut.future().failed();
                                } else {
                                    next.handle(Future.succeededFuture());
                                }
                            });
                        }
                    });
                } else {
                    next.handle(Future.succeededFuture());
                }
            } else {
                // report the error
                fut.fail(count.cause());
            }
        });
    }
}
