package io.truthencode.toad;


import org.apache.camel.test.testcontainers.junit5.ContainerAwareTestSupport;
import org.apache.camel.test.testcontainers.junit5.Wait;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelContainerTest extends ContainerAwareTestSupport {
    @Test
    @Tag("FunctionOnly")
    public void testPropertyPlaceholders() {
        GenericContainer<?> container = getContainer("myconsul");

        String host = context.resolvePropertyPlaceholders("{{container:host:myconsul}}");
        assertEquals(host, container.getContainerIpAddress());

        String port = context.resolvePropertyPlaceholders("{{container:port:8500@myconsul}}");
        assertEquals(port, "" + container.getMappedPort(8500));
    }

    @Override
    protected GenericContainer<?> createContainer() {
        return new GenericContainer<>("consul:1.0.7")
            .withNetworkAliases("myconsul")
            .withExposedPorts(8500)
            .waitingFor(Wait.forLogMessageContaining("Synced node info", 1))
            .withCommand(
                "agent",
                "-dev",
                "-server",
                "-bootstrap",
                "-client",
                "0.0.0.0",
                "-log-level",
                "trace"
            );
    }
}