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
