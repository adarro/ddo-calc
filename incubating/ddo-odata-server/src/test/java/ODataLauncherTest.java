import io.truthencode.odata.ODataLauncher;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

@RunWith(VertxUnitRunner.class)
public class ODataLauncherTest {
    private Vertx vertx;
    private int port;
    private static final int DEFAULT_PORT = 8080;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ODataLauncherTest.class);

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

    @Before
    public void setUp(TestContext context) {
        configurePort();
        vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(ODataLauncher.class.getName(), options,
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    @Ignore("Deferring Vertx launch test")
    public void testMyApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(port, "localhost", "/$metadata",
                response -> response.handler(body -> {
                    String b = body.toString();
                    LOG.info(String.format("body text => \t",b));
                    context.assertTrue(b.contains("Hello"));
                    async.complete();
                }));
    }
}