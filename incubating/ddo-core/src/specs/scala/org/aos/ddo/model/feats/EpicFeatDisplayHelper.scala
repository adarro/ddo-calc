/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.model.feats
import java.util
import scala.collection.JavaConverters._

/**
  * Verifies basic creation of Epic Feats, categorized as per [[http://ddowiki.com/page/Epic_Feats ddo Epic Feats]].
  */
trait EpicFeatDisplayHelper extends DisplayHelper {


  final override val enum: E = Feat
  // val categoryFilter: EpicFeatCategory

  val filterByCategory: PartialFunction[Entry, Entry]

  override def verify(): util.List[String] = {
    val v = enum.values collect filterByCategory
    v.map(_.displayText).asJava
  }
}
