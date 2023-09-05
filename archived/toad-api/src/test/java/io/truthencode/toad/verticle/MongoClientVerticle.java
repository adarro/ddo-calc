package io.truthencode.toad.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import io.vertx.ext.mongo.MongoClient;

public class MongoClientVerticle extends AbstractVerticle {


    static final String LABEL_ITEM_ID = "itemId";
    static final String LABEL_NAME = "name";
    static final String LABEL_PRICE = "price";
    static final String SOME_NUMBER = "12345";
    static final String LABEL_PRODUCTS = "products";
    Logger logger = java.util.logging.Logger.getLogger(MongoClientVerticle.class);

    @Override
    public void start() throws Exception {

        JsonObject config = Vertx.currentContext().config();

        String uri = config.getString("mongo_uri");
        if (uri == null) {
            uri = "mongodb://localhost:27017";
        }
        String db = config.getString("mongo_db");
        if (db == null) {
            db = "test";
        }

        JsonObject mongoconfig = new JsonObject()
            .put("connection_string", uri)
            .put("db_name", db);

        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);

        JsonObject product1 = new JsonObject().put(LABEL_ITEM_ID, LABEL_SOME_NUMBER).put(LABEL_NAME, "Cooler").put(LABEL_PRICE, "100.0");

        mongoClient.save(LABEL_PRODUCTS, product1)
            .compose(id -> {
                logger.debug("Inserted id: " + id);
                return mongoClient.find(LABEL_PRODUCTS, new JsonObject().put(LABEL_ITEM_ID, LABEL_SOME_NUMBER));
            })
            .compose(res -> {
                logger.debug("Name is " + res.get(0).getString(LABEL_NAME));
                return mongoClient.removeDocument(LABEL_PRODUCTS, new JsonObject().put(LABEL_ITEM_ID, LABEL_SOME_NUMBER));
            })
            .onComplete(ar -> {
                if (ar.succeeded()) {
                    logger.debug("Product removed ");
                } else {
                    ar.cause().printStackTrace();
                }
            });
    }
}