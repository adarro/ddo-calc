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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 *
 * @author Ramesh Fadatare
 */
public class DataSetupH2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSetupH2.class);

    public static void main(String[] argv) {
        DataSetupH2 createTableExample = new DataSetupH2();
        createTableExample.createTable();
    }

    protected final String createTableSQL = H2JDBCUtils.SqlCreateTable;
    protected final String dropTableSql = H2JDBCUtils.SqlDropTable;

    public void createTable() {
        try {
            boolean success = executeSql(createTableSQL);
            if (success)
                LOGGER.info("Table created");
            else
                LOGGER.error("failed to create table");
        } catch (SQLException e) {
            LOGGER.error("error Creating table", e);
        }


    }

    public void dropTable() {
        try {
            executeSql(dropTableSql);
            LOGGER.info("Dropped table");
        } catch (SQLException e) {
            LOGGER.error("error Dropping table", e);
        }
    }

    protected boolean executeSql(String sql) throws SQLException {
        LOGGER.info("executing sql {}", sql);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement()) {

            // Step 3: Execute the query or update query
            return statement.execute(sql);

        } catch (SQLException e) {
            // print SQL exception information
            String msg = String.format("Failed to execute SQL\n %s\n %s",sql,e.getMessage());
            LOGGER.error(msg, e);
            H2JDBCUtils.printSQLException(e);
            throw e;
        }
    }

    protected void executeSql(Connection connection, String... sql) throws SQLException {

        // Step 1: Establishing a Connection
        // Step 2:Create a statement using connection object
        try (
            Statement statement = connection.createStatement()) {
            for (String stmt : sql) {
                LOGGER.info("executing sql {}", stmt);
                // Step 3: Execute the query or update query
                statement.execute(stmt);
            }

        } catch (SQLException e) {
            // print SQL exception information
            LOGGER.error("failed to execute Sql", e);
            H2JDBCUtils.printSQLException(e);
            throw e;
        }
    }
}