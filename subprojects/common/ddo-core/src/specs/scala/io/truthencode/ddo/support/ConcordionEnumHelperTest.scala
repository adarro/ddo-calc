/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.support

import com.typesafe.scalalogging.LazyLogging
import com.wix.accord.scalatest.ResultMatchers
import io.truthencode.ddo.model.alignment.MoralAxis
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import scala.language.reflectiveCalls

/**
  * Created by adarr on 8/14/2016.
  */
@RunWith(classOf[JUnitRunner])
class ConcordionEnumHelperTest  extends FunSpec with Matchers with ResultMatchers with LazyLogging {
  val fixture = new {
    val builder =  new ConcordionEnumBuilderSupport {
      override def actual = MoralAxis.values.map(_.toString)
    }
    val helper = new ConcordionEnumBuilderHelper(builder)
  }
  describe("An Enum Helper") {
    it("should be generated from a jade template") {
      val f = fixture
      val lst = f.helper.listValues()
     logger.info(lst)
    }
  }
}
