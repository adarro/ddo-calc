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
package io.truthencode.ddo.test.tags
import org.scalatest.Tag

/**
 * Tags for filtering Scalatests [[https://www.scalatest.org/user_guide/tagging_your_tests]] Tests
 * or accesses a Database
 */
object DBTest extends Tag("io.truthencode.tags.Database")

/**
 * An integration test
 */
object IntegrationTest extends Tag("io.truthencode.tags.Integration")

/**
 * A general Unit Test
 */
object UnitTest extends Tag("io.truthencode.tags.Unit")

/**
 * Tests Clustering (May be slow or require network connectivity etc)
 */
object ClusteringTest extends Tag("io.truthencode.tags.Clustering")

/**
 * Indicates a slow running or time consuming test
 */
object Slow extends Tag("io.truthencode.tags.Slow")

/**
 * Tests or uses an Effect / feature
 */
object FeatureTest extends Tag("io.truthencode.tags.Feature")

object FeatTest extends Tag("io.truthencode.tags.Feat")

object SkillTest extends Tag("io.truthencode.tags.Skill")

object FunctionalTest extends Tag("io.truthencode.tags.Function")

/**
 * Tests basic functionality of a given library or interaction between. Does not affect production
 * code but illustrates the use. i.e. verify running a Camel Context inside a Vertx instance using a
 * Monix execution context.
 */
object FunctionLOnly extends Tag("io.truthencode.tags.FunctionOnly")
