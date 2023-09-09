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
package io.truthencode.ddo.support

/**
 * Created by adarr on 8/14/2016.
 */
class ConcordionEnumBuilderHelper(helper: ConcordionEnumBuilderSupport) {
  def actual: Seq[String] = helper.actual

  implicit private val myStringOrdering: scala.math.Ordering[String] =
    Ordering.fromLessThan[String](_ > _)

  def listValues(heading: String): String = helper.listValues(heading)

  /**
   * Needed for Concordion / Java compatability as it does not recognize optional parameters.
   *
   * @return
   */
  def listValues(): String = helper.listValues()

  def getValidSingleValue: String = helper.getValidSingleValue

  def resultCount(searchString: String, ignoreCase: String): Int =
    helper.resultCount(searchString, ignoreCase)

  def withNames(searchString: String, ignoreCase: Boolean): Seq[String] = {
    helper.withNames(searchString, ignoreCase = ignoreCase)
  }

}
