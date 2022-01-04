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
package io.truthencode.ddo.etl.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2JDBCUtils {

    private static final Config conf = ConfigFactory.load();

    private static final String jdbcURL = conf.getString("ctx.dataSource.url"); //"jdbc:h2:~/test";
    private static final String jdbcUsername = conf.getString("ctx.dataSource.user"); // "sa";
    private static final String jdbcPassword = "";
    private static final String createTableSql = conf.getString("db.dataSource.createSql");
    private static final String dropTableSql = conf.getString("db.dataSource.dropSql");
    private static final Logger LOGGER = LoggerFactory.getLogger(H2JDBCUtils.class);

    private static InputStream exampleTableSql(String sqlTemplate) {
        InputStream inputStream = H2JDBCUtils.class.getClassLoader().getResourceAsStream(sqlTemplate);
        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + sqlTemplate);
        } else {
            return inputStream;
        }
    }


    // print input stream
    private static String streamToString(InputStream is) {
        StringBuilder buff = new StringBuilder();
        try (InputStreamReader streamReader =
                 new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {


            String line;
            while ((line = reader.readLine()) != null) {
                buff.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();

    }

    public static final String SqlCreateTable = streamToString(exampleTableSql(createTableSql));

    public static final String SqlDropTable = streamToString(exampleTableSql(dropTableSql));

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not load / register H2 driver",e);
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            LOGGER.error(String.format("failed to connect to database %s", jdbcURL), e);
            printSQLException(e);
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
