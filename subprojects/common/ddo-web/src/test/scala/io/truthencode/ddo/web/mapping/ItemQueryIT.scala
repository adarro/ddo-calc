/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ItemQueryIT.scala
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
import io.truthencode.ddo.web.{toWikiUrlString, Warehouse}
import org.scalatest.Tag
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

import scala.language.reflectiveCalls

object SlowTest extends Tag("io.truthencode.ddo.tags.SlowTest")

class QueryPermanentItemIT extends AnyFunSpec with Matchers with MockitoSugar with LazyLogging {
  val lblWeapon: String = "Weapon"
  val msgMissing: String = "Missing!!!"
  protected def fixture: Object { val typeList: Map[String, String]; val defaultItemId: String } =
    new {
      val typeList = {
        val idWeapon = "Drow Scimitar of the Weapon Master"
        Map(lblWeapon -> idWeapon)
      }
      val defaultItemId = "Epic Elyd Edge"
    }

  describe("A named weapon") {

    it("should be retrievable from ddo wiki")({
      val f = fixture
      val id = toWikiUrlString(f.typeList.getOrElse(lblWeapon, f.defaultItemId))
      lazy val msgDataFound = "weapon data found"
      lazy val msgRetrieve = s"Attempting to retrieve item $id"
      lazy val extracted = s"No Data for $id"

      logger.info(msgRetrieve)
      val doc = Warehouse.loadDoc(id)
      val weaponDoc = Warehouse.htmlToMappedValues(doc)
      weaponDoc match {
        case Some(x) =>
          logger.info(msgDataFound)
          x.keys.foreach { key =>
            {
              val v = {
                x.getOrElse(key, msgMissing)
              }
              lazy val msgKv = s"\n$key:  $v"
              logger.info(msgKv)
            }
          }
        case _ => logger.warn(extracted)
      }
    })
  }

}
