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
package io.truthencode.ddo.model.feats
import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava

/**
 * Verifies basic creation of Epic Feats, categorized as per
 * [[http://ddowiki.com/page/Epic_Feats ddo Epic Feats]].
 */
trait EpicFeatFeatDisplayHelper extends FeatDisplayHelper {

  final override val displayEnum: E = Feat
  // val categoryFilter: EpicFeatCategory

  val filterByCategory: PartialFunction[Entry, Entry]

  override def verify(): util.List[String] = {
    val v = displayEnum.values.collect(filterByCategory)
    v.map(_.displayText).asJava
  }
}
