/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DDoWikiScrapingIT.scala
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

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.web.{Tree, Warehouse}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document
import org.scalatest.OptionValues._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

import java.io.File
import scala.language.reflectiveCalls

class DDoWikiScrapingIT extends AnyFunSpec with Matchers with MockitoSugar with LazyLogging {
  lazy val defaultEncoding = "UTF-8"
  lazy val baseUri = "http://www.ddowiki.com"
  lazy val nestedPath = "Drow_Scimitar_of_the_Weapon_Master.html"
  lazy val simplePath = "Elyd_Edge.html"
  lazy val multiLevelPath = ""
  val browser = JsoupBrowser()

  private def fixture: Object { val nestedListDoc: Document; val simpleListDoc: Document } = new {

    // Drow X of the Weapon Master
    val nestedListDoc: Document = loadLocalTestHtml(nestedPath)
    // Eyld Edge
    val simpleListDoc: Document = loadLocalTestHtml(simplePath)
  }

  protected def loadLocalTestHtml(
    fileName: String,
    charsetName: String = defaultEncoding,
    baseUri: String = baseUri): Document = {
    val url = Thread.currentThread().getContextClassLoader.getResource(fileName)
    logger.info(s"Local file: ${url.getPath}")
    val in = new File(url.getPath)
    if !in.exists() then logger.warn("can not load file!!")
    browser.parseFile(in, charsetName)

  }

  describe("Effect / enhancement List") {
    it("Should read a Simple list") {
      // This example has 6 items in a Simple list, so we should have one branch with 6 leaves
      val fragment = fixture.simpleListDoc.root
      val result = Tree(Warehouse.readHtmlList(fragment))
      result.isStump should be(false)
      result.leaves shouldBe empty
      result.branches.value should have size 1
      result.branches.value.head.leaves.value should have size 6

    }

    it("Should read a nested UL list") {
      // This example has 2 items in a Simple list, plus 5 nested ones

      val fragment = fixture.nestedListDoc.root
      val result = Tree(Warehouse.readHtmlList(fragment))
      result.isStump should be(false)
      result.leaves shouldBe empty
      result.branches shouldBe defined
      result.branches.value should have size 1
      val branches = result.branches.getOrElse(Nil)
      val leaves = branches.head.leaves.value should have size 2
      result.branches.value.head.leaves.value should have size 2
      val nestedBranches = branches.head.branches.value.toList
      (nestedBranches should have).length(1)
      nestedBranches.head.leaves shouldBe defined
      nestedBranches.head.branches shouldBe empty
      nestedBranches.head.leaves.value should have size 5

    }

    it("Should read a Multi level item")(pending)
  }
}
