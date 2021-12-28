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

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class EchoClient {
    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static EchoClient instance;

    public static EchoClient start() {
        if (instance == null)
            instance = new EchoClient();

        return instance;
    }

    public static void stop() throws IOException {
        client.close();
        buffer = null;
    }

    private EchoClient() {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 5454));
            buffer = ByteBuffer.allocate(256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            System.out.println("response=" + response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    public static void main(String[] args) throws IOException {
       // EchoClient client = EchoClient.start();
        // create a BufferedReader using System.in
        //    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        // Create the console object
//        Console cnsl
//            = System.console();
//
//        if (cnsl == null) {
//            System.out.println(
//                "No console available");
//            return;
//        }

        // Read line

        System.out.println("Enter lines of text.");
        System.out.println("Enter 'stop' to quit.");
        do {
            str = readLine();
            if (str != null)
                switch (str) {
                    case "stop":
                        System.out.println("Sending stop command");
              //          client.sendMessage("WwiBblE");
                        break;
                    default:
                        System.out.println("Sending msg" + str);
              //          client.sendMessage(str);
                        break;

                }
            else
                System.out.println("in was null, hopefully this is just a one time");
        } while (!Objects.equals(str, "stop"));
    }
    private static String readLine() throws IOException {
        if (System.console() != null) {
            return System.console().readLine();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));
        return reader.readLine();
    }
    private static String readLine(String format, Object... args) throws IOException {
        if (System.console() != null) {
            return System.console().readLine(format, args);
        }
        System.out.printf(format, args);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));
        return reader.readLine();
    }
}