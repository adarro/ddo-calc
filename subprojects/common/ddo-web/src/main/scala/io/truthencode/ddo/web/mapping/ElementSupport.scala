/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: ElementSupport.scala
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
package io.truthencode.ddo.web.mapping

import java.util

import net.ruippeixotog.scalascraper.model.Element
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element => JElement, TextNode}

/**
 * Conversion from Scala-scraper Element to JSoup Elements.
 *
 * @note
 *   This was added as the original scala-scraper implementation used JSoup transparently, but has
 *   since internalized it to implement their own CSSQuery vs JSoups CSSQuery with XPath elements.
 *   Specifically, capturing TextNodes is not a CSSQuery function, which JSoup supported and is
 *   sometimes needed.
 */
object ElementSupport {

  implicit class ElementToElementOps(source: Element) {
    def textNodes: util.List[TextNode] = {
      Jsoup.parse(source.innerHtml).textNodes()
    }
  }

}
