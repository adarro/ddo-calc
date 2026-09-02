/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ProofOfLifeSuite.scala
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
package io.truthencode.ddo.etl

import io.truthencode.ddo.etl.internal.SizSeven
import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit5.JUnitSuiteLike

/**
 * This test suite is a simple test to verify ScalaTest is running. This test is ignored by default
 * but can be enabled by removing the @Ignore annotation. It will intentionally fail.
 */
@Ignore
class ProofOfLifeSuite extends AnyFunSuite {
  test("someLibraryMethod is always true") {
    def fortyOne: SizSeven = new SizSeven()
    assert(!fortyOne.someLibraryMethod())
  }
}
