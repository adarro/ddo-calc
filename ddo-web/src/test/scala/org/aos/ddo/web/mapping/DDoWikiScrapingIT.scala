/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo.web.mapping

import java.io.File

import org.aos.ddo.web.{ Tree, Warehouse }
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.runner.RunWith
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar
import scala.language.reflectiveCalls
import com.typesafe.scalalogging.slf4j.LazyLogging

@RunWith(classOf[JUnitRunner])
class DDoWikiScrapingIT extends FunSpec with Matchers with MockitoSugar with LazyLogging {
  lazy val defaultEncoding = "UTF-8"
  lazy val baseUri = "http://www.ddowiki.com"
  lazy val nestedPath = "Drow_Scimitar_of_the_Weapon_Master.html"
  lazy val simplePath = "Elyd_Edge.html"
  lazy val multiLevelPath = ""
  protected def loadLocalTestHtml(fileName: String, charsetName: String = defaultEncoding, baseUri: String = baseUri): Document = {
    val url = Thread.currentThread().getContextClassLoader().getResource(fileName)
    logger.info(s"Local file: ${url.getPath}")
    val in = new File(url.getPath)
    if (!in.exists()) logger.warn("can not load file!!")
    Jsoup.parse(in, charsetName, baseUri)
  }

  private def fixture = new {

    // Drow X of the Weapon Master
    val nestedListDoc = loadLocalTestHtml(nestedPath)
    // Eyld Edge
    val simpleListDoc = loadLocalTestHtml(simplePath)
  }
  describe("Effect / enhancement List") {
    it("Should read a simple list") {
      // This example has 6 items in a simple list, so we should have one branch with 6 leaves

      val fragment = fixture.simpleListDoc
      val result = Tree(Warehouse.readHtmlList(fragment))
      result.isStump should be(false)
      result.leaves should be('empty)
      result.branches should not be ('empty)
      result.branches.getOrElse(Nil) should have length 1
      val branches = result.branches.getOrElse(Nil)
      val leaves = branches(0).leaves.getOrElse(Nil).toSeq
      leaves should have length 6
    }

    it("Should read a nested UL list") {
      // This example has 2 items in a simple list, plus 5 nested ones

      val fragment = fixture.nestedListDoc
      val result = Tree(Warehouse.readHtmlList(fragment))
      result.isStump should be(false)
      result.leaves should be('empty)
      result.branches should not be ('empty)
      result.branches.getOrElse(Nil) should have length 1
      val branches = result.branches.getOrElse(Nil)
      val leaves = branches(0).leaves.getOrElse(Nil).toSeq
      leaves should have length 2
      val nestedBranches = branches(0).branches.getOrElse(Nil).toList
      nestedBranches should have length 1
      nestedBranches(0).leaves should not be ('empty)
      nestedBranches(0).branches should be('empty)
      val branchedLeaves = nestedBranches(0).leaves.getOrElse(Nil).toSeq
      branchedLeaves should have length 5

    }

    it("Should read a Multi level item")(pending)
  }
}
