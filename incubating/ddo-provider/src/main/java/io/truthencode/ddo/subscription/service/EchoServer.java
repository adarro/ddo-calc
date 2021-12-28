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
package io.truthencode.ddo.subscription.service;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Echo Server example from https://www.baeldung.com/java-nio-selector
 * being used as a simple keep-alive ATM
 */
public class EchoServer {

    private static final String POISON_PILL = "WwiBblE";

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    answerWithEcho(buffer, key);
                }
                iter.remove();
            }
        }
    }

    private static DaemonStatus Status = DaemonStatus.STOPPED;
    private static ServerCommand cmd = ServerCommand.START;

    public static void ServerDaemon() throws IOException {
        Status = DaemonStatus.STARTING;
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);
        Status = DaemonStatus.RUNNING;
        cmd = ServerCommand.CONTINUE;
        while (cmd != ServerCommand.STOP) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    answerWithEcho(buffer, key);
                }
                iter.remove();
            }
        }
    }

    public static void Stop() {
        EchoClient.start().sendMessage(POISON_PILL);
        cmd = ServerCommand.STOP;
    }

    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key)
        throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);
        if (new String(buffer.array()).trim().equals(POISON_PILL)) {
            client.close();
            System.out.println("Not accepting client messages anymore");
        } else {
            buffer.flip();
            client.write(buffer);
            buffer.clear();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket)
        throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }


    public static Process start() throws IOException, InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = EchoServer.class.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        return builder.start();
    }
}