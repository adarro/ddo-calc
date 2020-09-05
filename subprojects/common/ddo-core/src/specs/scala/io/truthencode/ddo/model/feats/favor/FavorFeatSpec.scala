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
package io.truthencode.ddo.model.feats.favor

import java.util

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.feats.{FeatDisplayHelper, EberronReligionBase, Feat, ReligionFeatBase}
import io.truthencode.ddo.support.requisite.RequiresAllOfPatron
import org.concordion.api.FullOGNL
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith
import scala.collection.JavaConverters._

@FullOGNL
@RunWith(classOf[ConcordionRunner])
class FavorFeatSpec extends FeatDisplayHelper with LazyLogging {

  override val enum: E = Feat
  // RequiresAllOfPatron
  private val filterFavor
  : PartialFunction[Entry, Entry with RequiresAllOfPatron] = {
    case x: RequiresAllOfPatron => x
  }

  override def verify(): util.List[String] = {
    enum.values.map(_.displayText).asJava
  }
}
