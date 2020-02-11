/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.enumeration

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.StringUtils.Extensions


/**
  * Created by adarr on 1/16/2017.
  */
object EnumExtensions {

  private def findEnum[E <: EnumEntry : Enum](v: E) = implicitly[Enum[E]]

  final implicit class EnumCompanionOps[A <: Enum[_ <: EnumEntry]](val comp: A) {
    def exists(id: String): Boolean = {
      comp.namesToValuesMap.contains(id)
    }

    /**
      * Attempts to locate a matching enumeration based on a list of potential values.
      *
      * @param names      List of string values to try. i.e. Red, blue, bLaCk for a color.
      * @param ignoreCase toggles case sensitivity in search.
      * @return Returns the first Enum value found matching any of the given supplied names.
      */
    def withNames(names: List[String],
                  ignoreCase: Boolean = false): Option[Seq[EnumEntry]] = {
      val sanitized: List[String] = names.map { x =>
        x.filterAlphaNumeric
      }
      for {
        sc <- Some(
          comp.values.filter { x =>
            (ignoreCase && sanitized.exists(n =>
              n.equalsIgnoreCase(x.toString))) || sanitized.contains(
              x.toString)
          }) if sc.nonEmpty
      } yield sc
    }

    def withName(name: String,
                 ignoreCase: Boolean = false): Option[EnumEntry] = {
      if (ignoreCase) {
        comp.withNameInsensitiveOption(name)
      } else {
        comp.withNameOption(name)
      }
    }

    /**
      * A list of enum values matching the BitMask
      *
      * @param flag Bit value to compare
      * @return All matching values
      */
    def fromMask(flag: Int): Option[Seq[EnumEntry]] = {
      //  comp.bitValues.filter {x => (x._2 & flag != 0)}
      for {
        sc <- Some(comp.bitValues.filter { x =>
          (x._2.toInt & flag) != 0
        }.keys) if sc.nonEmpty
      } yield sc.toSeq
    }

    def bitValues: Map[EnumEntry, Double] = comp.valuesToIndex.map { x =>
      x._1 -> Math.pow(2.0, x._2)
    }

    def fromWords(words: String): Option[EnumEntry] = {
      words.wordsToAcronym match {
        case Some(x) => comp.withNameOption(x.toPascalCase)
        case _ => None
      }
    }

  }

}
