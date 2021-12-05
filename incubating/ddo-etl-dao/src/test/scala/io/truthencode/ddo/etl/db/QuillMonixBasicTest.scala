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
import io.getquill.context.monix.MonixJdbcContext.Runner
import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Consumer, Observer}
import org.scalatest.FutureOutcome
import org.scalatest.concurrent.ScalaFutures.whenReady
import org.scalatest.funspec.AsyncFunSpec
import org.scalatest.matchers.should.Matchers

import java.util.concurrent.Executors

class QuillMonixBasicTest extends AsyncFunSpec with Matchers with LazyLogging {

  lazy val ctx = new H2MonixJdbcContext(SnakeCase, "ctx", Runner.default)
  case class Circle(radius: Float)
  val dbTool = new DataSetupH2
  case class Address(city: String, state: String)

  override def withFixture(test: NoArgAsyncTest): FutureOutcome = {

    // Perform setup here
    dbTool.createTable()
    complete {
      super.withFixture(test) // Invoke the test function
    }.lastly {
      // Perform cleanup here
      dbTool.dropTable()
    }
  }

  // from https://monix.io/docs/current/reactive/consumer.html#consumerfromobserver
  def dumpConsumer[A](prefix: String, out: String => Unit): Consumer[A, Unit] = {

    Consumer.fromObserver[A] { implicit scheduler =>
      new Observer.Sync[A] {
        def onNext(elem: A): Ack = {
          out(s"O--->$elem")
          Continue
        }

        def onComplete() =
          out(s"O is complete")
        def onError(ex: Throwable) =
          out(s"O terminated with $ex")
      }
    }
  }

  def printSomething(out: String => Unit): String => Unit = {
    out
  }

  def p3(s: String): Unit = {
    logger.info(s)
  }

  describe("Quill streams and actions") {
    they("should be observable and taskable using Monix") {
      import ctx._

      // Query as Observable[Address]
      val oAddress = ctx.stream(query[Address].map(_.city))

      succeed
    }
    they("should be taskable") {
      import ctx._
      val oAddress = ctx.stream(query[Address].map(_.city))
      val consumer = dumpConsumer[Address]("address", p3)
      val result = oAddress
        .dump("source")
        .consumeWith(dumpConsumer("address:", p3))
        .runToFuture
      val exec = Executors.newSingleThreadExecutor
      whenReady(result) { _ => succeed }

    }
  }
}
