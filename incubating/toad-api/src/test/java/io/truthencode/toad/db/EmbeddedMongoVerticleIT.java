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
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@ExtendWith(VertxExtension.class)
public class EmbeddedMongoVerticleIT {

       public VertxOptions getOptions() {
        // It can take some time to download the first time!
        return new VertxOptions().setMaxWorkerExecuteTime(30 * 60 * 1000);
    }

    @Test
    @DisplayName("test Java based mongo verticle ")
    public void testDeployUsingClassName(Vertx vertx, VertxTestContext testContext) throws Exception {
        vertx.deployVerticle("java:" + MyFirstVerticle.class.getCanonicalName(), ar -> {
            testContext.verify(() -> {
                    assertTrue(ar.succeeded());
                }
            );
        });

    }

    @Test
    @DisplayName("Deploy an embedded mongo data as a verticle service")
    public void testEmbeddedMongo(Vertx vertx, VertxTestContext testContext) {
        // Not really sure what to test here apart from start and stop vertx.
        vertx.deployVerticle("service:io.vertx.vertx-mongo-embedded-db", testContext.succeeding(deploymentID -> {
            assertNotNull(deploymentID);
            vertx.undeploy(deploymentID, testContext.succeeding(v -> {
                testContext.verify(() -> {
                    assertNull(v);
                });
            }));
        }));
    }

    @Test
    @DisplayName("Deploy / Undeploy a simple scala verticle")
    public void testSimpleVerticle(Vertx vertx, VertxTestContext testContext) {
        DeploymentOptions opts = new DeploymentOptions().setMaxWorkerExecuteTime(30 * 60 * 1000);
        vertx.deployVerticle("io.truthencode.toad.SimpleScalaVerticle", opts, testContext.succeeding(id -> {
            testContext.verify(() -> {
                assertNotNull(id);
                vertx.undeploy(id, testContext.succeeding(Assertions::assertNull));
            });
        }));
    }
}
