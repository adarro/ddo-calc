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
plugins {
    scala
    `java-library` // cross-compiler is incompatible with java-library ATM

    id("com.github.maiflai.scalatest") // version "0.25"
  //  id("org.scoverage") // version "3.1.5"
    // IDE Specific
    idea
    id("org.unbroken-dome.test-sets") // version "2.1.1"
}
