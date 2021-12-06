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

import com.typesafe.scalalogging.LazyLogging
import io.getquill._
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class QuillBasicTest extends AnyFunSpec with Matchers with LazyLogging {

  val ctx: SqlMirrorContext[MirrorSqlDialect.type, Literal.type] =
    new SqlMirrorContext(MirrorSqlDialect, Literal)

  case class Circle(radius: Float)

  describe("Quill Quotes") {
    they("are implicit when writing a query in a run statement.") {
      import ctx._
      case class Address(city: String, state: String)
      val r = ctx.run(query[Circle].map(_.radius))
      r.string shouldEqual "SELECT x1.radius FROM Circle x1"

    }
    they("are support nested objects.") {
      import ctx._
      case class AddressBook(name: String, address: Address)
      case class Address(city: String, state: String) extends Embedded
      val r = ctx.run(query[AddressBook].filter(_.name == "bob").map(_.address))
      r.string shouldEqual "SELECT x2.city, x2.state FROM AddressBook x2 WHERE x2.name = 'bob'"

    }
    they("can use embedded objects in isolation") {
      import ctx._
      case class AddressBook(name: String, address: Address)
      case class Address(city: String, state: String, zip: ZipCode) extends Embedded
      case class ZipCode(zipCode: String) extends Embedded
      val r = ctx.run(query[Address])
      r.string shouldEqual ("SELECT x.city, x.state, x.zipCode FROM Address x")
    }
  }

}
