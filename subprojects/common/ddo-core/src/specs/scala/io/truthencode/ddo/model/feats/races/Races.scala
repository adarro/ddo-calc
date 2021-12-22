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
package io.truthencode.ddo.model.feats.races

import io.truthencode.ddo.model.feats.{FeatDisplayHelper, RacialFeat}
import org.concordion.api.FullOGNL
import org.concordion.api.option.{ConcordionOptions, MarkdownExtensions}
//import org.concordion.ext.EmbedExtension
//import org.concordion.ext.collapse.CollapseOutputExtension
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@FullOGNL
//@Extensions(Array(classOf[EmbedExtension], classOf[CollapseOutputExtension]))
@RunWith(classOf[ConcordionRunner])
@ConcordionOptions(
  declareNamespaces = Array("ext", "urn:concordion-extensions:2010"),
  markdownExtensions = Array(
    MarkdownExtensions.WIKILINKS,
    MarkdownExtensions.AUTOLINKS,
    MarkdownExtensions.TASKLISTITEMS)
)
class Races extends FeatDisplayHelper {
  override val displayEnum: E = RacialFeat
}
