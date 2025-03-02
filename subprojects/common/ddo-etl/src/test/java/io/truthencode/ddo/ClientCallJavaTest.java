package io.truthencode.ddo;


import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.context.SmallRyeThreadContext;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import io.truthencode.ddo.etl.internal.EtlWebClient;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ForkJoinPool;

@QuarkusTest
class ClientCallJavaTest {


    @Inject
    EtlWebClient client;

    @Inject
    SmallRyeThreadContext sharedConfiguredThreadContext;
    Duration timeoutMillis = Duration.ofSeconds(5);

    @Test
    void BasicCall() {
        String path = "http://localhost:8080/q/health";
        String expected = """
            {
                "status": "UP",
                "checks": [
                ]
            }""";

        try (AutoCloseable ctx = SmallRyeThreadContext.withThreadContext(sharedConfiguredThreadContext)) {
            Uni<String> uni = client.call(path); // .emitOn(Infrastructure)
            UniAssertSubscriber<String> resp = uni.subscribe().withSubscriber(UniAssertSubscriber.create());
            var theWait = resp.awaitItem(timeoutMillis);
            theWait.assertItem(expected);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test_retrieving_value_async() {
        Uni<String> uni = Uni.createFrom().item("Hello");
        var pool = ForkJoinPool.commonPool();
        var test = uni
            .runSubscriptionOn(pool)
            .subscribe().withSubscriber(UniAssertSubscriber.create());
        test.awaitItem(timeoutMillis).assertItem("Hello");
    }
}
