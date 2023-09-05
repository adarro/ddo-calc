package io.truthencode.toad.verticle;

import io.vertx.core.AbstractVerticle;

public class DemoVerticle extends AbstractVerticle {

    @Override
    public void start() {
// Create an HTTP server which simply returns "Hello World!" to each request.
        vertx.createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8180);
    }

    @Override
    public void stop() {

        // You can optionally override the stop method too, if you have some clean-up to do
        java.util.logging.Logger.getLogger(DemoVerticle.class.getName()).warning("In OtherVerticle.stop")

    }
}