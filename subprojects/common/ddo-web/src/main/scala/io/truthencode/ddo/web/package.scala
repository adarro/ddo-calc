/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: package.scala
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
package io.truthencode.ddo

import java.net.URLEncoder

import io.truthencode.ddo.support.StringUtils.{Space, UnderScore}

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

package object web {

  /**
   * Takes an ID string and translates it to URL escaped and wiki specific format.
   *
   * @param s
   *   \- the input string
   * @return
   *   Url Escaped string
   * @example
   *   {{{
   * val key = "Brigand's Cutlass"
   * val escaped = ToWikiUrlString(key)
   *
   * /res: Brigand'_Cutlass
   *   }}}
   * @note
   *   some items are actually URL escaped, but may appear at both. i.e. Anger's Wrath is linked to
   *   Anger%27s_Step AND Anger's_Step
   * @todo
   *   may need to see if any are on one but not the other. It appears the un-escaped apostrophe is
   *   more common.
   */
  def toWikiUrlString(s: String, escapeApostrophe: Boolean = false): String = {
    val x = s.replace(Space, UnderScore).trim()
    if escapeApostrophe then URLEncoder.encode(x, "UTF-8") else x
  }

  // Whenever you write a library, allow people to supply a Config but
  // also default to ConfigFactory.load if they don't supply one.
  // Libraries generally have some kind of Context or other object
  // where it's convenient to place the configuration.

  // we have a constructor allowing the app to provide a custom Config
  /**
   * WebContext Convenience class to read formatted URI's
   *
   * @param config
   *   \- Configuration Resource
   * @param lib
   *   \- Resource Group (defaults to ddowiki-lib)
   * @param id
   *   \- key within the resource group containing the URL defaults to "item"
   */
  class WebContext(config: Config, lib: String = "ddowiki-lib", id: String = "item")
    extends LazyLogging {

    // This verifies that the Config is sane and has our
    // reference config. Importantly, we specify the "simple-lib"
    // path so we only validate settings that belong to this
    // library. Otherwise, we might throw mistaken errors about
    // settings we know nothing about.
    config.checkValid(ConfigFactory.defaultReference(), id)

    // This uses the standard default Config, if none is provided,
    // which simplifies apps willing to use the defaults
    def this() = {
      this(ConfigFactory.load())
    }

    val source: String = config.getString(s"$lib.$id")

    def url(id: String): String = "%s%s".format(source, id)

    // this is the amazing functionality provided by simple-lib
    def printSetting(path: String): Unit = {
      logger.info(s"The setting '$path' is: ${config.getString(path)}")
    }
  }

}
