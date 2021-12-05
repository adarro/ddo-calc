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
package io.truthencode.toad.verticle

import io.vertx.core.DeploymentOptions
import io.vertx.core.dns.AddressResolverOptions
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions

/**
 * Created by adarr on 7/25/2016.
 */
trait StackableOption {
  lazy val deploymentOptions: DeploymentOptions = mergeDeployment
  lazy val addressResolverOptions: AddressResolverOptions = mergeAddressResolver
  lazy val httpServerOptions: HttpServerOptions = mergeHttpServer
  lazy val bridgeOptions = mergeBridge

  def mergeDeployment: DeploymentOptions = new DeploymentOptions()

  def mergeHttpServer: HttpServerOptions = new HttpServerOptions()

  def mergeAddressResolver: AddressResolverOptions = new AddressResolverOptions()

  def mergeBridge: SockJSBridgeOptions = new SockJSBridgeOptions()

}
