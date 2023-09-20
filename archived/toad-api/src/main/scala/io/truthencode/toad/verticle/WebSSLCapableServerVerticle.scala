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

import io.vertx.core.http.HttpServerOptions
import io.vertx.core.net._
import io.vertx.core.{AbstractVerticle, AsyncResult, Handler, Promise}
import io.vertx.ext.auth.AuthProvider
import io.vertx.ext.auth.shiro.{ShiroAuth, ShiroAuthOptions, ShiroAuthRealmType}
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.handler.sockjs.{SockJSBridgeOptions, SockJSHandler}
import org.slf4j.{Logger, LoggerFactory}
import sun.security.tools.keytool.CertAndKeyGen
import sun.security.x509.X500Name

import java.io.{FileOutputStream, IOException}
import java.security._
import java.security.cert.{Certificate, CertificateException}
import java.util.Date

/**
 * A [[io.vertx.core.Verticle]] which starts up the HTTP server for the web application UI. Based on
 * the given configuration, the web server may be configured for SSL using a self-generated SSL cert
 * or a provided SSL certificate file. The application accepts P12, PEM, and JKS files.
 *
 * The web server also configures handlers for the Auth Service and the
 * [[io.vertx.ext.web.handler.sockjs.SockJSHandler]]event bus bridge.
 *
 * @author
 *   <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
class WebSSLCapableServerVerticle extends AbstractVerticle with DefaultBridgeOptions {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)

  /**
   * Start this [[io.vertx.core.Verticle]] asynchronously and notify the deploying verticle on
   * success or failure
   *
   * @param startFuture
   *   A [[java.util.concurrent.Future]] with which to notify the deploying
   *   [[io.vertx.core.Verticle]] about success or failure
   * @throws Exception
   *   If there is an uncaught error.
   */
  @throws[Exception]
  // scalastyle:off
  override def start(startFuture: Promise[Void]): Unit = {
    val config = context.config().getJsonObject("webserver")
    val authCfg = context.config().getJsonObject("authentication")
    Option(config) match {
      case Some(_) => logger.info("SSL Config is not null")
      case None =>
        val ex = new IllegalArgumentException(
          "Missing required SSL Configuration parameters in io.vertx.core.Context.config()")
        logger.error(
          "Authorization configuration was null, please supply a valid JsonObject via DeployOptions.setConfig when deploying this Verticle",
          ex)
        throw ex
    }
    val bindAddress = config.getString("bind-address", "0.0.0.0")
    val bindPort = config.getInteger("bind-port", 8080)
    val webRoot: String = config.getString("webroot", "webroot/")
    logger.info(s"Using webroot => $webRoot")
    val router = Router.router(vertx)
    val sockjs = SockJSHandler.create(vertx)
    val opts: SockJSBridgeOptions = bridgeOptions
    sockjs.bridge(opts)

    var authProvider: AuthProvider = null

    if (authCfg.containsKey("auth-provider")) {
      authCfg.getString("auth-provider") match {
        case "ldap" =>
          val opts = new ShiroAuthOptions().setType(ShiroAuthRealmType.LDAP).setConfig(authCfg)
          authProvider = ShiroAuth.create(vertx, opts);
        case "jdbc" =>

        case _ =>
      }
    }

    router.route("/eventbus/*").handler(sockjs)

    router.route().handler(StaticHandler.create(webRoot).setIndexPage("index.html"))
    //    import java.util.function._
    //    import scala.compat.java8.FunctionConverters._
    // If SSL is requested, prepare the SSL configuration off of the event bus to prevent blocking.
    if (config.containsKey("ssl") && config.getBoolean("ssl")) {
      val fut = new Handler[Promise[HttpServerOptions]] {
        override def handle(future: Promise[HttpServerOptions]): Unit = {
          val httpOpts = new HttpServerOptions()
          if (config.containsKey("certificate-path")) {
            val certPath = config.getString("certificate-path")
            // Use a Java Keystore File
            if (certPath.toLowerCase().endsWith("jks") && config.getString(
                "certificate-password") != null) {
              httpOpts.setKeyStoreOptions(
                new JksOptions()
                  .setPassword(config.getString("certificate-password"))
                  .setPath(certPath))
              httpOpts.setSsl(true)

              // Use a PKCS12 keystore
            } else if (config.getString("certificate-password") != null &&
              certPath.matches("^.*\\.(pfx|p12|PFX|P12)$")) {
              httpOpts.setPfxKeyCertOptions(
                new PfxOptions()
                  .setPassword(config.getString("certificate-password"))
                  .setPath(certPath))
              httpOpts.setSsl(true)

              // Use a PEM key/cert pair
            } else if (certPath.matches("^.*\\.(pem|PEM)$")) {
              httpOpts.setPemKeyCertOptions(
                new PemKeyCertOptions()
                  .setCertPath(certPath)
                  .setKeyPath(certPath))
              httpOpts.setSsl(true)
            } else {
              startFuture.fail(
                "A certificate file was provided, but a password for that file was not.")
            }
          } else
            try {
              // Generate a self-signed key pair and certificate
              logger.info("Attempting self-signed SSL")
              val store = KeyStore.getInstance("JKS")
              store.load(null, null)
              val keypair = new CertAndKeyGen("RSA", "SHA256WithRSA", null)
              val x500Name =
                new X500Name("localhost", "IT", "unknown", "unknown", "unknown", "unknown")
              keypair.generate(1024)
              val privKey = keypair.getPrivateKey
              val chain = new Array[Certificate](1) // ( 1) ;
              val cert: Certificate =
                keypair.getSelfCertificate(x500Name, new Date(), 365 * 24 * 60 * 60)
              chain(0) = cert
              store.setKeyEntry("selfsigned", privKey, "changeit".toCharArray, chain)
              store.store(new FileOutputStream(".keystore"), "changeit".toCharArray)
              httpOpts.setKeyStoreOptions(
                new JksOptions().setPath(".keystore").setPassword("changeit"))
              httpOpts.setSsl(true)
            } catch {
              case ex @ (_: KeyStoreException | _: IOException | _: NoSuchAlgorithmException |
                  _: CertificateException | _: NoSuchProviderException | _: InvalidKeyException |
                  _: SignatureException) =>
                logger.error(
                  "Failed to generate a self-signed cert and other SSL configuration methods failed.",
                  ex)
                startFuture.fail(ex)
            }
          future.complete(httpOpts)
        }
      }
      val result = new Handler[AsyncResult[HttpServerOptions]] {
        override def handle(event: AsyncResult[HttpServerOptions]): Unit = {
          if (event.succeeded()) {
            vertx
              .createHttpServer(event.result())
              .requestHandler(router)
              .listen(bindPort, bindAddress)
            logger.info(s"SSL Web server now listening on @ $bindAddress:$bindPort")
          }

        }
      }

      vertx.executeBlocking(fut, result)

    } else {
      // No SSL requested, start a non-SSL HTTP server.
      vertx.createHttpServer().requestHandler(router).listen(bindPort, bindAddress)
      logger.info("(Non-SSL) Web server now listening")
      startFuture.complete()
    }
  } // scalastyle:on
}
