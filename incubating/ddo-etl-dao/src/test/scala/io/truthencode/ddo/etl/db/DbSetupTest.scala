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
package io.truthencode.ddo.etl.db

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Using
import scala.reflect.internal.util.Collections

class DbSetupTest extends AnyFunSpec with Matchers {

  describe("An example database") {
    it("should be creatable in memory") {
      val dbTool = new DataSetupH2
      noException shouldBe thrownBy(dbTool.createTable())
      // verify table exists
      noException shouldBe thrownBy(dbTool.executeSql("SELECT city, state FROM Address"));

    }

    it("should be able to drop that table") {
      val dbTool = new DataSetupH2
      noException shouldBe thrownBy(dbTool.dropTable())
    }

    it("Should execute multiple statements") {
      val dbTool = new DataSetupH2
      val sql = List(dbTool.createTableSQL, "SELECT city, state FROM Address")
      Using(H2JDBCUtils.getConnection) { con =>
        noException shouldBe thrownBy(dbTool.executeSql(con, sql: _*))
      }

    }
  }

}
