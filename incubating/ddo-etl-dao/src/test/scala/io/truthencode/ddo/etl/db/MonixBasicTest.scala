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
import org.scalatest.funspec.{AnyFunSpec, AsyncFunSpec}
import org.scalatest.matchers.should.Matchers
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

import concurrent.duration._

class MonixBasicTest extends AsyncFunSpec with Matchers with LazyLogging {
  val hardStop = 10L
  // We first build an observable that emits a tick per second,
  // the series of elements being an auto-incremented long
  def source: Observable[Long] = Observable
    .interval(1.second)
    // Filtering out odd numbers, making it emit every 2 seconds
    .filter(_ % 2 == 0)
    // We then make it emit the same element twice
    .flatMap(x => Observable(x, x))
    // This stream would be infinite, so we limit it to 10 items
    .take(hardStop)
  val consumer: Consumer[Long, Long] =
    Consumer.foldLeft(0L)(_ + _)

  describe("A simple observer") {
    they("Can be consumed and ran to a future") {

      // Observables are lazy, nothing happens until you subscribe...
      source
        // On consuming it, we want to dump the contents to stdout
        // for debugging purposes
        .dump("source")
        .consumeWith(Consumer.toList)
        .runToFuture
        .map { l => assert(l.nonEmpty) }
    }
  }
}
