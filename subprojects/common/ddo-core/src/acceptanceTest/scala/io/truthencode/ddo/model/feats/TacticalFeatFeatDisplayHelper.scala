/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TacticalFeatFeatDisplayHelper.scala
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

import com.typesafe.scalalogging.LazyLogging

import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava

trait TacticalFeatFeatDisplayHelper extends FeatDisplayHelper with LazyLogging {
  override val displayEnum: E = Feat
}

abstract class TacticalFeatFeatDisplayHelperJava extends TacticalFeatFeatDisplayHelper {
  type S = Feat & Tactical

  val filterByTactical: PartialFunction[Entry, Entry] = { case x: Tactical =>
    x
  }

  def tacticalFeats: util.List[Entry] = { displayEnum.values.collect(filterByTactical) }
    .sortBy(_.entryName)
    .asJava
}
