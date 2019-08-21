package org.aos.ddo

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document

/**
  * Created by adarr on 5/8/2017.
  */
package object etl {
  val DefaultEncoding = "UTF-8"
  val BaseUri = "http://www.ddowiki.com"
  private val browser = JsoupBrowser()

  def loadLocalSourceHtml(fileName: String,
                          charsetName: String = DefaultEncoding,
                          baseUri: String = BaseUri): Document = {
    val in = getClass.getResourceAsStream(fileName)
    browser.parseInputStream(in, charsetName)
  }
}
