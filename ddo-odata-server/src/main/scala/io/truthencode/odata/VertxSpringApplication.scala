package io.truthencode.odata

import io.vertx.core.{DeploymentOptions, Verticle, Vertx}
import io.vertx.core.json.JsonObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import javax.annotation.PostConstruct
import java.io.IOException
import java.net.ServerSocket

import com.typesafe.scalalogging.LazyLogging
import org.springframework.context.annotation.ComponentScan

import scala.collection.JavaConverters._

object VertxSpringApplication extends App with LazyLogging {

  protected final val DEFAULT_PORT = 8080
  private def configurePort(): Int = {
    try {
      val socket = new ServerSocket(0)
      try {
        val pt = socket.getLocalPort
        val msg = s"Test port set to $pt"
        logger.info(msg)
        pt
      } catch {
        case e: IOException =>
          logger.error("Failed to set random port. Defaulting to %d", DEFAULT_PORT)
          e.printStackTrace()
          VertxSpringApplication.DEFAULT_PORT
      } finally if (socket != null) socket.close()
    }
  }
  override def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[VertxSpringApplication])
    //  SpringApplication.run(List(classOf[VertxSpringApplication]).toArray, args)
  }
}

@ComponentScan
class VertxSpringApplication extends LazyLogging {


  @Autowired private val myConfig: YAMLConfig = null

  @Autowired private val odataVerticle: ODataLauncher = null
  private val port = VertxSpringApplication.configurePort()

  @PostConstruct def deployVerticle(): Unit = {
    val vertx = Vertx.vertx
    val options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port))
    vertx.deployVerticle(odataVerticle, options)
  }


}