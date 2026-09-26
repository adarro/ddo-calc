/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EnumSupport.scala
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
package io.truthencode.ddo.support

import enumeratum.{Enum, EnumEntry}

import scala.util.{Failure, Success, Try}

object EnumSupport {

  implicit class EnumOpts[U <: enumeratum.Enum[? <: EnumEntry]](source: U) {
    def tryCast(s: String): Try[EnumEntry] = {
      val g: Any = source.withName(s)
      source.withNameOption(s) match {
        case Some(x) => Success(x)
        case _ => Failure(new NoSuchElementException(s))
      }
    }
  }

  val anyToEnum: PartialFunction[AnyRef, enumeratum.Enum[EnumEntry]] = {
    case x: enumeratum.Enum[EnumEntry] =>
      x
  }

  /**
   * Attempts to extract a named [[enumeratum.EnumEntry]] from a fully qualified class name
   * @param fqn
   *   fully qualified class name of the enumeration
   * @param id
   *   string name of the value to extract. (Case Sensitive)
   * @return
   *   The EnumEntry value or [[scala.None]] if value can not be found.
   */
  def tryEntryFromString(fqn: String, id: String): Option[EnumEntry] = {
    tryEnumFromString(fqn).flatMap(_.withNameOption(id))
  }

  /**
   * Attempts to locate a valid Singleton given a valid [[enumeratum.Enum]] name as string.
   * @param fqn
   *   fully qualified class name
   * @return
   *   The Enum Companion object or None
   *
   * @note
   *   Internally uses a [[scala.util.Try]] and will safely return [[None]] for any cast exceptions.
   *   Also allows base trait name to invoke Companion. i.e. org.example.baseTrait vs Companion
   *   org.example.baseTrait$
   */
  def tryEnumFromString(fqn: String): Option[Enum[EnumEntry]] = {
    // Testing naming pattern for companion object
    val mangle = if fqn.endsWith("$") then { fqn }
    else { "%s$".format(fqn) }
    val y: Try[AnyRef] = for
      c <- Try(Class.forName(mangle))
      c2 <- Try(c.getField("MODULE$"))
      c3 <- Try(c2.get(c2))
    yield c3
    y match {
      case Success(x) =>
        Some(x).collect(anyToEnum)
      case _ => None
    }
  }

}
