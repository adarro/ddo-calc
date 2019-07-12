package io.truthencode.odata

import java.util

import com.typesafe.scalalogging.LazyLogging
import io.vertx.core.{AbstractVerticle, AsyncResult, Future}
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import org.apache.olingo.commons.api.edmx.EdmxReference
import org.apache.olingo.server.api.{OData, ServiceMetadata}
import org.apache.olingo.server.sample.edmprovider.CarsEdmProvider
import org.springframework.stereotype.Component
import ro.trusted.web.VertxOdataHandlerImpl

@Component
class ODataLauncher extends AbstractVerticle with LazyLogging {
  val DefaultPort = 8080
  val DefaultBindAddress = "127.0.0.1"

  override def start(fut: Future[Void]): Unit = {
    // TODO: Move OData instance to bootstrap property (i.e. at vertx / db / camel context level
    val odata = OData.newInstance()
    val edm: ServiceMetadata = odata.createServiceMetadata(new CarsEdmProvider, new util.ArrayList[EdmxReference])

    val router = Router.router(vertx)
    router.route("/cars.svc*").handler(new VertxOdataHandlerImpl(odata, edm))
    logger.info(s"Starting verticle ${this.getClass.getSimpleName} on port $port")
    vertx.createHttpServer().requestHandler(router.accept _).listen(port, (result: AsyncResult[HttpServer]) => {
      def foo(result: AsyncResult[HttpServer]): Unit = if (result.succeeded) {
        logger.info("result succeeded, setting complete")
        fut.complete()
      }
      else {
        logger.info("result failed, returning cause")
        fut.fail(result.cause)
      }

      foo(result)
    })
  }

  private def port = config().getInteger("http.port", DefaultPort)
}
