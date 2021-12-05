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
package io.truthencode.toad.verticle

import io.truthencode.toad.verticle.VertxService.startVertXAsync
import io.vertx.core.{Future, Vertx}
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.execution.Scheduler.{global => scheduler}
import org.scalatest.funspec.AsyncFunSpec
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.DurationInt

class VertxServiceAsyncTest extends AsyncFunSpec {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  def asExecAsyncTaskScalaFuture[T](t: Future[T]): scala.concurrent.Future[T] = {
    t.onComplete(ar => {
      if (ar.succeeded()) {
        logger.info("Started Vertx Service")
        ar.result()
      } else {
        val ex = ar.cause()
        logger.error("Failed to start Vertx", ex)
        throw ex
      }
    })
    Task.eval(t).executeAsync.map(_.result()).runToFuture
  }

  // scalastyle:off
  def asTaskRunScalaFuture[T](t: Future[T]) = {
//        t.onComplete(ar => {
//            if (ar.succeeded()) {
//                logger.info("Started Vertx Service")
//                ar.result()
//            } else {
//                val ex = ar.cause()
//                logger.error("Failed to start Vertx", ex)
//                throw ex
//            }
//        })
    val task = Task(t)
    task.runAsync {
      case Right(value) => scala.concurrent.Future { value.result() }
      case Left(e) => e
    }
  }

  def foo[T](t: Future[T]) = {
    val tt = Task(t)
    var completed = false
    var vx: Option[T] = None
    var er: Throwable = null
    val ra = tt.runAsync {
      case Right(value) =>
        value.onComplete { ar =>
          {
            if (ar.succeeded()) {
              completed = true
              vx = Some(ar.result())
            } else {
              completed = true
              er = ar.cause()
            }
          }
        }
      case Left(e) => er = e
    }

    val pendingWait = scheduler.scheduleWithFixedDelay(1.seconds, 10.seconds) {
      logger.info("awaiting Vertx startup")
      logger.info(s"defined: ${vx.isDefined} completed: $completed")

    }
    // Nothing executes yet

    ra
  }
  // scalastyle:on
  describe("Async stuff") {
    ignore("should play nice") {
      val v = foo(startVertXAsync().future())
      val task: Task[String] =
        for {
          _ <- Task.sleep(3.seconds)
          ru <- Task { println("Executing..."); "blah" }
        } yield ru
      // Triggering the task's execution:
      val f = task.runAsync {
        case Right(str: String) =>
          println(s"Received: $str")
        case Left(e) =>
          global.reportFailure(e)
      }
      task.runToFuture.map(r => assert(r === "Hello"))
    }
  }
  describe("Vertx") {
    they("might work via Task.runtoFuture ") {
      val server = asExecAsyncTaskScalaFuture(startVertXAsync().future())
      server.onComplete { c =>
        {
          assert(c.isSuccess)
        }
      }
      succeed

    }
    ignore("might work via eval exec Async") {
      val server: concurrent.Future[Vertx] = asExecAsyncTaskScalaFuture(startVertXAsync().future())
      server.map(v => assert(Option(v).isDefined))
    }
  }
}
