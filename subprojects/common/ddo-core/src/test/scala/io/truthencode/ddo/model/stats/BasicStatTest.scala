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
package io.truthencode.ddo.model.stats

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.stats.BasicStat.{extractCategory, values}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class BasicStatTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("Basic Stats") {
    they("should be enumerated") {
      noException shouldBe thrownBy {
        BasicStat.values
      }
      val vals = BasicStat.values
      vals should not be empty
      logger.error(s"Basic Stat size is ${vals.size}")
    }

    they("should include Granted Abilities") {
      val abilities = BasicStat.allGrantedAbilities
      abilities.foreach { a =>
        val ao = Option(a.entryName)
        ao shouldBe defined
        logger.info(s"ao $ao")
      }
    }

    they("should have named features") {
      val stats = BasicStat.values
      val missStats = stats.collect { case x: MissChance => x }
      missStats should not be empty
      missStats.foreach(ms => logger.info(ms.entryName))
    }

    they("should be bound by categories") {
      val cat = values.collect(extractCategory).groupBy(_.categoryId).map{
        c => c._2.head
      }.toList.sortBy(_.categoryId)
      logger.info(s"Categories: ${cat.size}")
      cat.foreach(c => logger.info(s"${c.categoryId} ${c.catName}"))

    }
  }
}
