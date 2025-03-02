/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Searchable.scala
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
package io.truthencode.ddo.model.effect

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum, EnumEntry}

import scala.util.{Failure, Success, Try}

// scalastyle:off
trait Searchable[T <: EnumEntry & SearchPattern] extends Enum[T] with LazyLogging {

  def findByPattern(name: String): Option[T] = {
    values.filter(p => p.searchPattern() == p.searchPattern(name)).lastOption
  }

  def tryFindByPattern(name: String, usePattern: Option[String] = None): Try[T] = {
    if usePattern.isEmpty then logger.warn(s"Using default pattern matching for $name")
    val cls = getClass.getSimpleName
    val sp = usePattern.getOrElse("")
    val targetPattern = usePattern match {
      case Some(x) => x
      case None => name
    }
//    logger.whenDebugEnabled{
//        val msg = s"\nSearchable:-> $cls for $name ${usePattern.getOrElse(sp)} target  $targetPattern"
//        logger.debug(msg)
//    }

    val rslt = values.filter { v =>
      // DEBUG   logger.debug(s"sp => tp ${v.searchPattern()} => $targetPattern")
      v.searchPattern() == targetPattern
    }
//    val rslt = values.flatMap { v =>
//      val txt = "ssome stopping point"
//      v match {
//        case p: T if p.searchPattern() == targetPattern =>
//          logger.info(s"type searchPattern => targetPattern matched!! $targetPattern")
//          Some(p)
//        case p: T if p.searchPattern(name) == p.entryName =>
//          logger.info(s"type (Search pattern) matched $p with ${p.entryName}")
//          Some(p)
//        case p: T if p.entryName == name =>
//          logger.info(s"type (Direct) matched $p with ${p.entryName}")
//          Some(p)
//        case x: T =>
//          logger.info(s"$name not found \npattern(name):\t${x.searchPattern(name)}\npattern:\t${x
//            .searchPattern()}\ntarget:\t$targetPattern\nentryName:\t${x.entryName}\nname:\t$name")
//          None
//        case _ =>
//          logger.warn(s"No match of type T found for $name")
//          None
//      }
//    }
    if rslt.nonEmpty then Success(rslt.head)
    else Failure(new NoSuchElementException(s"Could not find element with name $name"))

//        .flatten match {
//      case Some(x) =>
//        logger.debug(s"${x.entryName}")
//        Success(x)
//      case _ =>
//        val ex = new NoSuchElementException(s"Could not find element with name $name")
//        logger.error("Failed to locate element by pattern", ex)
//        Failure(ex)
//    }

  }
}
// scalastyle:on
object Searchable {
  def stripParentheses(string: String): String = string.replaceAll("[()]", "")
}
