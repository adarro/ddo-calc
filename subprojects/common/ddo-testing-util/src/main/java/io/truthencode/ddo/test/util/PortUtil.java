package io.truthencode.ddo.test.util;

import java.io.IOException;
import java.net.ServerSocket;

public class PortUtil {
    /**
     * Returns a free port number on localhost.
     *
     * Heavily inspired from org.eclipse.jdt.launching.SocketUtil (to avoid a dependency to JDT just because of this).
     * Slightly improved with close() missing in JDT. And throws exception instead of returning -1.
     * @see <a href="https://gist.githubusercontent.com/vorburger/3429822/raw/d82ed1ad5ef885c1ee5087f9a0afc506ff4550a7/gistfile1.java">How to find an available (free) TCP port in Java</a>
     * @return a free port number on localhost
     * @throws IllegalStateException if unable to find a free port
     */
    public static synchronized int findFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore IOException on close()
            }
            return port;
        } catch (IOException ignored) {
        }
        throw new IllegalStateException("Could not find a free TCP/IP port");
    }
}