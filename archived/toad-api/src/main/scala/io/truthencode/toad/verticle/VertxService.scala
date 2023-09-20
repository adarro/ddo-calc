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

import io.truthencode.toad.config.CommonImplicits._
import io.truthencode.toad.config.cfg
import io.vertx.core._
import io.vertx.core.dns.AddressResolverOptions
import io.vertx.core.eventbus.EventBusOptions
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.slf4j.{Logger, LoggerFactory}

import java.net.{InetAddress, UnknownHostException}
import scala.concurrent.duration
import scala.language.postfixOps

/**
 * Created by adarr on 7/7/2016.
 */
object VertxService {

  import scala.concurrent.duration._

  final val DefaultDuration = 180 seconds

  private def toBilliSeconds(l: Long) = {
    l * 1000 * 1000000
  }

  final val DefaultTTL = 90
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  @throws[UnknownHostException]
  private[verticle] def startClustered: Future[Vertx] = {

    val ipv4: String = InetAddress.getLocalHost.getHostAddress
    val options: VertxOptions =
      new VertxOptions().setEventBusOptions(
        new EventBusOptions().setHost(ipv4).setClusterPublicHost(ipv4))
    Vertx.clusteredVertx(options)
  }

  def startVertxSync(timeout: Duration = DefaultDuration): Promise[Vertx] = {
    val promise: Promise[Vertx] = startVertXAsync()
    val sleepTimer = 7.seconds
    val tooLong: Duration = Duration(timeout.toMillis / sleepTimer.toMillis, duration.SECONDS)

    logger.info(s"Synchronously starting VertX with ${tooLong.toSeconds} second Timeout")
    val task: Task[Boolean] =
      for {
        _ <- Task.sleep(sleepTimer)
        r <- Task { logger.info("Waiting..."); promise.future().isComplete }
      } yield r

    task
      .restartUntil((completed) => {
        completed
      })
      .runSyncUnsafe(tooLong)

    promise
  }

  /**
   * Initializes a clustered vertx instance Asynchronously
   *
   * @return
   *   Initialized Future containing Vertx instance or exception
   */
  def startVertXAsync(): Promise[Vertx] = {
    logger.info("Starting Vertx (Async)")
    val promise: Promise[Vertx] = Promise.promise()
    val addOpts = new AddressResolverOptions()
      .setCacheMinTimeToLive(DefaultTTL) // 90 second min cache ttl
      .setCacheNegativeTimeToLive(DefaultTTL)
    // FIXME: change MaxEventLoopExecutionTime back to a more reasonable number or fix blocking to accept the default
    val blockDelay: Long = toBilliSeconds(10)
    val options = new VertxOptions()
      .setMaxEventLoopExecuteTime(blockDelay)
      .setAddressResolverOptions(addOpts)

    // Launch SSL base verticle on success
    promise.future().onSuccess { server =>
      server.getOrCreateContext().config().mergeIn(cfg.root)

      val sslVerticle = classOf[WebSSLCapableServerVerticle].getName
      logger.info("Launching SSL Verticle")
      server.deployVerticle(sslVerticle, new DeploymentOptions().mergeConfig())
    }

    Vertx.clusteredVertx(options, promise)
    promise
  }

  /**
   * Initializes the Vertx engine synchronously
   *
   * @note
   *   internally calls [[io.truthencode.toad.verticle.VertxService#startVertXAsync()]] with a
   *   blocking call.
   * @param timeout
   *   maximum wait time to block before failing. (defaults to [[DefaultDuration]]
   * @return
   *   A Vertx instance or exception in the case of failure.
   */
  def startVertx(timeout: Duration = DefaultDuration): Vertx = {
    logger.info("Starting Vert.x Service")
    startVertxSync().future().result()
  }

  def fooBar(): Unit = {
    val ipv4 = InetAddress.getLocalHost.getHostAddress
    val options = new VertxOptions().setEventBusOptions(
      new EventBusOptions().setHost(ipv4).setClusterPublicHost(ipv4))
    Vertx.clusteredVertx(
      options,
      (ar: AsyncResult[Vertx]) => {
        def foo(ar: AsyncResult[Vertx]): Unit = if (ar.succeeded)
          ar.result.deployVerticle(new WebSSLCapableServerVerticle)
        else logger.error("Could not start", ar.cause)

        foo(ar)
      }
    )
  }
}
