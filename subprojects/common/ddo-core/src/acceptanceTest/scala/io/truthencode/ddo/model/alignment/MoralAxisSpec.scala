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
package io.truthencode.ddo.model.alignment

import io.truthencode.ddo.support.ConcordionEnumBuilderSupport
import org.concordion.api.FullOGNL
//import org.concordion.ext.EmbedExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(classOf[ConcordionRunner])
@FullOGNL
//@Extensions(Array(classOf[EmbedExtension]))
class MoralAxisSpec {

  val helper: ConcordionEnumBuilderSupport = new ConcordionEnumBuilderSupport {
    override def actual: Seq[String] = MoralAxis.values.map(_.toString)
  }

  val rows: scala.collection.mutable.SortedSet[String] = scala.collection.mutable.TreeSet.empty

  def setUpRow(value: String): Unit = {
    rows += value
  }

  def getCaseSensitive: String = "Values can be located / limited in a case-sensitive manner"

  def getCaseInSensitive: String = "Values can be located / limited in a case-insensitive manner"

  def getInvalidValues: String = "Invalid values should be handled gracefully"

}
