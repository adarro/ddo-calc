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
package io.truthencode.toad.db;

import io.truthencode.toad.verticle.MyFirstVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@ExtendWith(VertxExtension.class)
@Testcontainers
public class EmbeddedMongoVerticleIT {

    // will be shared between test methods
    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.1.2"));

    public VertxOptions getOptions() {
        // It can take some time to download the first time!
        return new VertxOptions().setMaxWorkerExecuteTime(30 * 60 * 1000);
    }

    @Test
    @Tag("FunctionOnly")
    void testContainer() {
        assertTrue(mongoDBContainer.isRunning());
    }

    String mongoUrl(MongoDBContainer mongo) {
        if (!mongo.isRunning()) {
            mongo.start();
        }
        Integer port = mongo.getFirstMappedPort();
        String host = mongo.getHost();
        return String.format("mongodb://%s:%d", host, port);
    }

    @Test
    @DisplayName("test Java based mongo verticle ")
    public void testDeployUsingClassName(Vertx vertx, VertxTestContext testContext) throws Throwable {
        assumeTrue(mongoDBContainer.isRunning());

        DeploymentOptions dOpt = new DeploymentOptions().setConfig(new JsonObject().put("mongo_uri", mongoUrl(mongoDBContainer)));
        vertx.deployVerticle("java:" + MyFirstVerticle.class.getCanonicalName(), dOpt, testContext.succeeding(id -> {
            assertNotNull(id);
            vertx.undeploy(id, testContext.succeedingThenComplete());
        }));
        assertTrue(testContext.awaitCompletion(5, TimeUnit.SECONDS));
        if (testContext.failed())
            throw testContext.causeOfFailure();
    }

    @Test
    @Disabled("Vertx no longer provides an embedded mongo db")
    @DisplayName("Deploy an embedded mongo data as a verticle service")
    public void testEmbeddedMongo(Vertx vertx, VertxTestContext testContext) throws Throwable {
        assumeTrue(mongoDBContainer.isRunning());
        // Not really sure what to test here apart from start and stop vertx.
        vertx.deployVerticle("service:io.vertx.vertx-mongo-embedded-db", testContext.succeeding(deploymentID -> {
            assertNotNull(deploymentID);
            vertx.undeploy(deploymentID, testContext.succeeding(v ->
                testContext.verify(() ->
                    assertNull(v);
                );
            ));
        }));
        assertTrue(testContext.awaitCompletion(5, TimeUnit.SECONDS));
        if (testContext.failed())
            throw testContext.causeOfFailure();
    }

    @Test
    @Tag("FunctionOnly")
    @Tag("Slow")
    @Disabled("Need to migrate to scala flavored vertices")
    @DisplayName("Deploy / Undeploy a simple scala verticle")
    public void testSimpleVerticle(Vertx vertx, VertxTestContext testContext) throws Throwable {
        assumeTrue(mongoDBContainer.isRunning());
        DeploymentOptions opts = new DeploymentOptions().setMaxWorkerExecuteTime(30 * 60 * 1000).setConfig(new JsonObject().put("mongo_uri", mongoUrl(mongoDBContainer)));
        vertx.deployVerticle("io.truthencode.toad.SimpleScalaVerticle", opts, testContext.succeeding(id -> {
            assertNotNull(id);
            vertx.undeploy(id, testContext.succeedingThenComplete());
        }));
        assertTrue(testContext.awaitCompletion(15, TimeUnit.SECONDS));
        if (testContext.failed()) {
            throw testContext.causeOfFailure();
        }
    }


    @Test
    void startHttpServer(Vertx vertx, VertxTestContext testContext) throws Throwable {

        vertx.createHttpServer()
            .requestHandler(req -> req.response().end())
            .listen(16969)
            .onComplete(testContext.succeedingThenComplete());

        assertTrue(testContext.awaitCompletion(5, TimeUnit.SECONDS));
        if (testContext.failed()) {
            throw testContext.causeOfFailure();
        }
    }
}
