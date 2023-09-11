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

import io.truthencode.toad.verticle.VertxService.{startVertXAsync, startVertx, startVertxSync}
import io.vertx.core.Future
import monix.eval.Task
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global
import monix.execution.schedulers.CanBlock
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.jdk.CollectionConverters.SetHasAsScala

class VertxServiceTest extends AnyFunSpec with Matchers {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  def asExecAsyncTaskScalaFuture[T](t: Future[T]): CancelableFuture[T] = {
    implicit val canBlock: CanBlock = CanBlock.permit
    Task.eval(t).map(_.result()).runToFuture
  }

  def blockingUnsafeToFuture[T](t: Future[T]): T = {
    implicit val canBlock: CanBlock = CanBlock.permit
    val taskToDo: CancelableFuture[T] = Task.eval(t).map(_.result()).runToFuture

    val timerTask: Task[Boolean] =
      for {
        _ <- Task.sleep(10.seconds)
        r <- Task {
          logger.info(s"Waiting... completed: ${taskToDo.isCompleted}"); taskToDo.isCompleted
        }
      } yield r
    val result = Await.result(taskToDo, 45.seconds)
    timerTask
      .restartUntil((completed) => {
        completed
      })
      .runSyncUnsafe(40.seconds)
    result

  }

  describe("Vertx") {
    ignore("might work in one generic function") {
      val vx = blockingUnsafeToFuture(startVertXAsync().future())
      Option(vx) shouldBe defined
    }

    it("has a chance to run synchronously") {
      val vx = startVertxSync().future().result()
      assert(Option(vx).isDefined)
      vx.deploymentIDs().asScala should not be empty
      vx.close()
    }

    ignore("might work via Task.runtoFuture ") {

      val server = asExecAsyncTaskScalaFuture(startVertXAsync().future())
      val task: Task[Boolean] =
        for {
          _ <- Task.sleep(3.seconds)
          r <- Task { logger.info("Waiting..."); server.isCompleted }
        } yield r

      task
        .restartUntil((completed) => {
          completed
        })
        .runSyncUnsafe(40.seconds)
      Option(server) shouldBe defined

    }
  }

}
