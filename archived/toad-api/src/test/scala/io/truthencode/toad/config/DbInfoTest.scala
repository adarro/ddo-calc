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
package io.truthencode.toad.config

import com.typesafe.config.{Config, ConfigFactory}
import configs.Result.{Failure, Success}
import configs.syntax._
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps
import scala.util.Try

/**
 * Created by adarr on 7/15/2016.
 */
class DbInfoTest extends AnyFunSpec with Matchers {
  val fixture = new {
    logger.info("Forcing Logger Init")
  }
  describe("Database Configuration") {
    it("Should be backed by a valid config file by default") {
      val check = ConfigFactory.parseString("""
     db-host {
        |host=mysever.com
        |  port=8889
        |  uuid=default
        |  user-id=joe
        |  pwd=secret
        |  url=mongodb":"//myserver.com":"8989
        |}
        """.stripMargin)

      assumeTrue(check.isResolved, "safety check was not resolved")
      assumeTrue(cfg.isResolved, "Config was not resolved")
      noException should be thrownBy cfg.checkValid(check, "db-info")
    }

    it("should have sensible defaults") {
      //   val (host,port,uuid,userId,pwd,url) =

      val tCfg = Try(cfg.resolve) match {
        case x: Try[Config] => Some(x.get)
        case _ => None
      }
      tCfg should not be empty
      val myCfg = tCfg.get
      val result = cfg.get[DbInfo]("db-info") match {
        case Success(x) => Some(DbInfo(x.host, x.port, x.uuid, x.userId, x.pwd, x.url))
        case Failure(x) =>
          val msg = "Error reading Server configuration"
          logger.error(msg, x.configException)
          None
      }
      result should not be empty
    }

    it("Should be read from configuration files ") {
      val dbConfig = Try(DbInfo.apply) match {
        case x: Try[DbInfo] => x.get
      }

      (dbConfig.port should not).equal(0)
      dbConfig.host should not be null
      dbConfig.url should not be null
      dbConfig.userId should not be null
      dbConfig.pwd should not be null
      dbConfig.pwd.length should be > 0
    }
  }
}
