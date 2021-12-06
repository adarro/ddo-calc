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
package io.swagger.v3.plugins.grudle.petstore;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;

public class PetStoreVerticle extends AbstractVerticle {
    @Override
    public void start() {

        RouterBuilder.create(vertx, "src/main/resources/petstore.yaml")
            .onSuccess(routerBuilder -> {
                Router router = routerBuilder.createRouter();
                HttpServer server =
                    vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost(
                        "localhost"));
                server.requestHandler(router).listen();

            })
            .onFailure(err -> {
                // Something went wrong during router builder initialization
            });

    }


}
