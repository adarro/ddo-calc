/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aos.ddo.web.mapping

import scala.annotation.migration
import scala.language.reflectiveCalls

import org.aos.ddo.web.{ Warehouse, toWikiUrlString }
import org.junit.runner.RunWith
import org.scalatest.{ FunSpec, Matchers, Tag }
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar

import com.typesafe.scalalogging.slf4j.LazyLogging

object SlowTest extends Tag("org.aos.ddo.tags.SlowTest")

@RunWith(classOf[JUnitRunner])
class QueryItemIT extends FunSpec with Matchers with MockitoSugar with LazyLogging {
  val LblWeapon: String = "Weapon"
  val MsgMissing: String = "Missing!!!"
  protected def fixture = new {
    val typeList = {
      val IdWeapon = "Drow Scimitar of the Weapon Master"
      Map(LblWeapon -> IdWeapon)
    }
    val defaultItemId = "Epic Elyd Edge"
  }

  describe("A named weapon") {
    (
      it("should be retrievable from ddo wiki")({
        val f = fixture
        val id = {
          toWikiUrlString(f.typeList.getOrElse(LblWeapon, f.defaultItemId))
        }
        lazy val MsgDataFound = "weapon data found"
        lazy val MsgRetrieve = s"Attempting to retrieve item ${id}"
        lazy val extracted = s"No Data for ${id}"

        logger.info(MsgRetrieve)
        val weaponDoc = Warehouse.htmlToMappedValues(id)
        weaponDoc match {
          case Some(x) =>
            logger.info(MsgDataFound)
            x.keys.foreach {
              key =>
                {
                  val v = {
                    x.getOrElse(key, MsgMissing)
                  }
                  lazy val MsgKv = s"\n${key}:  ${v}"
                  logger.info(MsgKv)
                }
            }
          case _ => logger.warn(extracted)
        }
      }))
  }

}
