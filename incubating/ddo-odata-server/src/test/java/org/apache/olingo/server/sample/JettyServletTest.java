package org.apache.olingo.server.sample;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.vertx.core.Vertx;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

import static io.restassured.RestAssured.get;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

// @RunWith(VertxUnitRunner.class)
public class JettyServletTest {
    private Vertx vertx;
    private int port;
    private static final int DEFAULT_PORT = 8080;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JettyServletTest.class);

    private void configurePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
            String msg = String.format("Test port set to %d", port);
            LOG.info(msg);
        } catch (IOException e) {
            LOG.error("Failed to set random port. Defaulting to %d", DEFAULT_PORT);
            port = DEFAULT_PORT;
            e.printStackTrace();
        }
    }


    Server server = null;

    @Before
    public void doBefore() {
        configurePort();
    }

    @Test
    public void CheckServlet() throws Exception {
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 // org.eclipse.jetty.io.WriteFlusher
        context.addServlet(new ServletHolder(new CarsServlet()), "/*");
        server.start();
        //   context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        //   context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");
        String host = "localhost";
        String path = "/$metadata";

        String uri = String.format("http://%s:%d%s",host,port,path);
        Response resp =get(uri).then().contentType(ContentType.XML)
                .extract().response();
        LOG.info(resp.asString());

//        HttpClient client = Vertx.vertx().createHttpClient();
//        final HttpClientRequest req = client.get(port, "localhost", "/$metadata");



                stopishServer();
        //server.join();

    }

    void stopishServer() {
        if (server != null && server.isRunning()) {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
