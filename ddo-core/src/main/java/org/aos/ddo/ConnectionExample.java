/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aos.ddo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionExample {
	public static void main(String[] args) throws Exception {
		System.out.println("running");
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
				.newInstance();
		System.out.println("driver loaded");
		String url = "jdbc:sqlserver://127.0.0.1:1453;DatabaseName=sonar";
		Connection con = null;
		if (args.length > 0) {
			url = args[0];
		} else {
			System.out.println("Connecting with url '" + url + "'");
			try {
				con = DriverManager.getConnection(url, "myuser", "mypass");
				System.out.println("Successful connection");
			} catch (Exception e) {
				System.out.println("Connection failed");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				if (con != null)
					con.close();
			}

		}
	}

}