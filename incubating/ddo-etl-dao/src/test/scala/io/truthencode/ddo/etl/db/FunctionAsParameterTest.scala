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

import com.typesafe.scalalogging.{LazyLogging, LoggerMacro}
import org.scalatest.funspec.AnyFunSpec

class FunctionAsParameterTest extends AnyFunSpec with LazyLogging {
  // three args are passed in:
  // 1) 'f' - a function that takes an Int and returns an Int
  // 2) 'a' - an Int
  // 3) 'b' - an Int
  def sum(f: Int => Int, a: Int, b: Int): Int = if (a > b) 0 else f(a) + sum(f, a + 1, b)

  // these three functions use the sum() function
  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def sumSquares(a: Int, b: Int): Int = sum(square, a, b)
  def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)

  // three functions that are passed into the sum() function
  def id(x: Int): Int = x
  def square(x: Int): Int = x * x
  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)

  def stringToUnit(s: String): Unit = {
    logger.info(s)
  }

  def printSomething(out: String => Unit, thing: String): Unit = {
    out(s"modified $thing")
  }
  describe("a working example") {
    it("should um... work") {
      sumInts(1, 4)
    }
  }
  describe("Something") {
    it("does it with my function") {
      printSomething(stringToUnit, "My String")
    }

  }

}
