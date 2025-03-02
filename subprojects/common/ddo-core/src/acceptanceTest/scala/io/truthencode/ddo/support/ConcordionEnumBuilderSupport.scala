/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ConcordionEnumBuilderSupport.scala
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

import java.lang
import scala.jdk.CollectionConverters.IterableHasAsJava
import scala.util.Try

/**
 * Created by adarr on 1/25/2017.
 */
trait ConcordionEnumBuilderSupport {
  implicit protected val myStringOrdering: Ordering[String] =
    Ordering.fromLessThan[String](_ > _)

  def actual: Seq[String]

  /**
   * Needed for Concordion / Java compatibility as it does not recognize optional parameters.
   *
   * @return
   */
  def listValues(): String = {
    listValues("Expected Values")
  }

  def listValues(heading: String): String = {
    val data = actual.map { a => s"<tr><td>$a</td></tr>" }.mkString
    val header = s"<table><tr><th>$heading</th></tr>"
    val footer = "</table>"
    s"$header$data$footer"
  }

  def getValidSingleValue: String =
    actual.headOption.getOrElse("Please specify at least one value for this enum")

  def resultCount(searchString: String, ignoreCase: String): Int = {
    resultCount(searchString, strToBool(ignoreCase))
  }

  protected def strToBool(s: String): Boolean = Try(s.toBoolean).getOrElse(false)

  protected def resultCount(searchString: String, ignoreCase: Boolean): Int = {
    withNames(searchString, ignoreCase = ignoreCase).size
  }

  def withNames(searchString: String, ignoreCase: Boolean): Seq[String] = {
    val ss = searchString.split(',').toSet.toSeq
    if ignoreCase then {
      for
        a <- actual
        s <- ss
        if a.equalsIgnoreCase(s.trim)
      yield s
    } else {
      ss.intersect(actual)
    }
  }

  def withNames(searchString: String, ignoreCase: String): lang.Iterable[String] = {
    withNames(searchString, strToBool(ignoreCase))
  }.sortWith(_ < _).asJava
}
