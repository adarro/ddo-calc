/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

trait Searchable[T <: EnumEntry with SearchPattern] extends Enum[T] with LazyLogging {

  def findByPattern(name: String): Option[T] = {
    values.filter(p => p.searchPattern() == (p.searchPattern(name))).lastOption
  }

  def tryFindByPattern(name: String): Try[T] = {
    logger.debug("entry\t=>\tnamed")

    values collectFirst {
      case p: T if p.searchPattern(name) == p.entryName => p
    } match {
      case Some(x) => Success(x)
      case _       => Failure(new NoSuchElementException(s"Could not find element with name $name"))
    }

  }
}
