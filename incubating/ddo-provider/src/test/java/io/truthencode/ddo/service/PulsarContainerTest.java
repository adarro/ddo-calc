package io.truthencode.ddo.service;


import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.testcontainers.containers.PulsarContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Functional "Am I using this correctly" pulsar test container.
 * Modified from https://raw.githubusercontent.com/testcontainers/testcontainers-java/master/modules/pulsar/src/test/java/org/testcontainers/containers/PulsarContainerTest.java
 * from  Junit4 + assertJ -> JUnit5
 */
@Tag("FunctionOnly")
@Isolated
public class PulsarContainerTest {

    public static final String TEST_TOPIC = "test_topic";
    private static final DockerImageName PULSAR_IMAGE = DockerImageName.parse("apachepulsar/pulsar:2.9.1");

    @Test
    public void testUsage() throws Exception {
        try (PulsarContainer pulsar = new PulsarContainer(PULSAR_IMAGE)) {
            pulsar.start();
            testPulsarFunctionality(pulsar.getPulsarBrokerUrl());
        }
    }

    @Test
    public void shouldNotEnableFunctionsWorkerByDefault() throws Exception {
        try (PulsarContainer pulsar = new PulsarContainer(PULSAR_IMAGE)) {
            pulsar.start();

            PulsarAdmin pulsarAdmin = PulsarAdmin.builder()
                .serviceHttpUrl(pulsar.getHttpServiceUrl())
                .build();
            assertThrows(PulsarAdminException.class, () -> pulsarAdmin.functions().getFunctions("public", "default"));

        }
    }

    @Test
    public void shouldWaitForFunctionsWorkerStarted() throws Exception {
        try (PulsarContainer pulsar = new PulsarContainer(PULSAR_IMAGE).withFunctionsWorker()) {
            pulsar.start();

            PulsarAdmin pulsarAdmin = PulsarAdmin.builder()
                .serviceHttpUrl(pulsar.getHttpServiceUrl())
                .build();

            int s = pulsarAdmin.functions().getFunctions("public", "default").size();
            assertEquals(s, 0);

        }
    }

    protected void testPulsarFunctionality(String pulsarBrokerUrl) throws Exception {

        try (
            PulsarClient client = PulsarClient.builder()
                .serviceUrl(pulsarBrokerUrl)
                .build();
            Consumer consumer = client.newConsumer()
                .topic(TEST_TOPIC)
                .subscriptionName("test-subs")
                .subscribe();
            Producer<byte[]> producer = client.newProducer()
                .topic(TEST_TOPIC)
                .create()
        ) {

            producer.send("test containers".getBytes());
            CompletableFuture<Message> future = consumer.receiveAsync();
            Message message = future.get(5, TimeUnit.SECONDS);

            assertEquals(new String(message.getData()), "test containers");

        }
    }

}